package br.com.alura.leilao.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
}
