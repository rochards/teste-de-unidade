package br.com.alura.leilao.teste;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.dominio.Lance;
import br.com.alura.leilao.dominio.Leilao;
import br.com.alura.leilao.dominio.Usuario;
import br.com.alura.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	
	@Test
	public void teste() {
		// parte 1: cenario
	    Usuario joao = new Usuario("joao");
	    Usuario jose = new Usuario("jose");
	    Usuario maria = new Usuario("maria");

	    Leilao leilao = new Leilao("Playstation 5");

	    // esses valores vao gerar um bug
	    leilao.propoe(new Lance(joao, 250));
	    leilao.propoe(new Lance(jose, 300));
	    leilao.propoe(new Lance(maria, 400));

	    // parte 2: acao
	    Avaliador leiloeiro = new Avaliador();
	    leiloeiro.avalia(leilao);

	    // parte 3: validacao
	    Assert.assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
	    Assert.assertEquals(250, leiloeiro.getMenorLance(), 0.00001);
	}
}
