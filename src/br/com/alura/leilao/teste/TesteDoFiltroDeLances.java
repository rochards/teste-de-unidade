package br.com.alura.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.alura.leilao.dominio.Lance;
import br.com.alura.leilao.dominio.Usuario;
import br.com.alura.leilao.servico.FiltroDeLances;

public class TesteDoFiltroDeLances {

	@Test
	public void deveSelecionarLancesEntre1000E3000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,2000), 
                new Lance(joao,1000), 
                new Lance(joao,3000), 
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(2000, resultado.get(0).getValor(), 0.00001);
    }
	
	@Test
	public void deveSelecionarLancesEntre500E700() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,600), 
                new Lance(joao,500), 
                new Lance(joao,700), 
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(600, resultado.get(0).getValor(), 0.00001);
    }
	
	@Test
	public void deveSelecionarLancesMaioresQue5000() {
		Usuario joao = new Usuario("Joao");
		
		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao,5000),
				new Lance(joao,5001),
				new Lance(joao,5003)));
		
		assertEquals(2, resultado.size());
		assertEquals(5001, resultado.get(0).getValor(), 0.00001);
		assertEquals(5003, resultado.get(1).getValor(), 0.00001);
	}
	
	@Test
	public void deveSelecionarLancesEntre3000E5000() {
		Usuario joao = new Usuario("Joao");
		
		FiltroDeLances filtro = new FiltroDeLances();
		List<Lance> resultado = filtro.filtra(Arrays.asList(
				new Lance(joao,4000),
				new Lance(joao,3050),
				new Lance(joao,5000)));
		
		assertEquals(0, resultado.size());
	}
}
