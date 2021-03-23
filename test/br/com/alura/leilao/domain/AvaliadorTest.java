package br.com.alura.leilao.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.builder.CriadorDeLeilao;
import br.com.alura.leilao.dominio.Lance;
import br.com.alura.leilao.dominio.Leilao;
import br.com.alura.leilao.dominio.Usuario;
import br.com.alura.leilao.servico.Avaliador;

/** ATENÇÃO: para rodar o hamcrest nesses testes, é precico baixar o jar dele. Tbm
 * é necessário adicioná-lo ao build path e em configurações de build path, colocá-lo acima
 * do JUnit*/
public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario jose;
	private Usuario mary;
	private Usuario french;

	@BeforeEach
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.jose = new Usuario("jose");
		this.mary = new Usuario("mary");
		this.french = new Usuario("french");
	}
	
	@Test
	public void naoDeveAvaliarUmLeilaoSemLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("PS5")
				.constroi();
		
		assertThrows(RuntimeException.class, () -> leiloeiro.avalia(leilao));
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		Leilao leilao = new CriadorDeLeilao().para("PS5")
				.lance(jose, 250)
				.lance(mary, 350.48)
				.lance(french, 400)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getMaiorLance(), equalTo(400.0)); // hamcrest
		assertThat(leiloeiro.getMenorLance(), equalTo(250.0)); // hamcrest
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("PS5")
				.lance(jose, 250.0)
				.lance(mary, 3504.0)
				.lance(french, 400.0)
				.lance(jose, 2500.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaioresLances();
		
		assertThat(3, equalTo(maiores.size()));
		assertThat(maiores, hasItems(
				new Lance(mary, 3504.0),
				new Lance(jose, 2500.0),
				new Lance(french, 400.0)));
	}
}
