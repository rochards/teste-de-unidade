package br.com.alura.leilao.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.builder.CriadorDeLeilao;
import br.com.alura.leilao.dominio.Leilao;
import br.com.alura.leilao.servico.Avaliador;

public class AvaliadorTest {

	private Avaliador leiloeiro;

	@BeforeEach
	public void setUp() {
		this.leiloeiro = new Avaliador();
	}
	
	@Test
	public void naoDeveAvaliarUmLeilaoSemLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("PS5")
				.constroi();
		
		assertThrows(RuntimeException.class, () -> leiloeiro.avalia(leilao));
	}
}
