package br.com.alura.leilao.domain;

import static br.com.alura.leilao.servico.LeilaoMatcher.temUmLance;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import br.com.alura.leilao.builder.CriadorDeLeilao;
import br.com.alura.leilao.dominio.Lance;
import br.com.alura.leilao.dominio.Leilao;
import br.com.alura.leilao.dominio.Usuario;

public class LeilaoTest {
	
	@Test
	public void deveReceberUmLance() {
		
		Leilao leilao = new Leilao("Mackbook Pro");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Marcio"), 2000));
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Mackbook Pro");
		
		leilao.propoe(new Lance(new Usuario("Marcio"), 2000));
		leilao.propoe(new Lance(new Usuario("José"), 4000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(4000.0, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro");
		
		leilao.propoe(new Lance(new Usuario("Marcio"), 2000));
		leilao.propoe(new Lance(new Usuario("Marcio"), 3000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro");
		var marcio = new Usuario("Marcio");
		var roberto = new Usuario("Roberto");
		
		leilao.propoe(new Lance(marcio, 2000));
		leilao.propoe(new Lance(roberto, 350));
		
		leilao.propoe(new Lance(marcio, 3000));
		leilao.propoe(new Lance(roberto, 3000));
		
		leilao.propoe(new Lance(marcio, 3971));
		leilao.propoe(new Lance(roberto, 3000));
		
		leilao.propoe(new Lance(marcio, 4671)); 
		leilao.propoe(new Lance(roberto, 687));
		
		leilao.propoe(new Lance(marcio, 4777));
		leilao.propoe(new Lance(roberto, 687));

		leilao.propoe(new Lance(marcio, 4777)); // deve ser ignorado
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(687.0, leilao.getLances().get(9).getValor(), 0.00001);
	}
	
	@Test
	public void deveDobrarOLance() {
		Leilao leilao = new Leilao("Macbook Pro");
		var marcio = new Usuario("Marcio");
		var roberto = new Usuario("Roberto");
		
		leilao.propoe(new Lance(marcio, 3000));
		leilao.propoe(new Lance(roberto, 2300));
		
		leilao.dobraLance(marcio);
		
		assertEquals(3, leilao.getLances().size());
		assertEquals(6000.0, leilao.getLances().get(2).getValor());
		
	}
	
	@Test
	public void naoDeveDobrarOLanceParaOUltimoUsuario() {
		Leilao leilao = new Leilao("Macbook Pro");
		var marcio = new Usuario("Marcio");
		var roberto = new Usuario("Roberto");
		
		leilao.propoe(new Lance(marcio, 3000));
		leilao.propoe(new Lance(roberto, 2300));
		
		leilao.dobraLance(roberto);
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2300.0, leilao.getLances().get(1).getValor());
	}
	
	// deve criar o teste para o caso de 5 lances do mesmo usuario
	
	
	@Test
	public void deveReceberUmLance2() {
		
		Leilao leilao = new CriadorDeLeilao().para("PS5").constroi();
		assertEquals(0, leilao.getLances().size());
		
		Lance lance = new Lance(new Usuario("Steve"), 2500);
		leilao.propoe(lance);
		
		assertThat(leilao.getLances().size(), equalTo(1));
		assertThat(leilao, temUmLance(lance));
	}
}
