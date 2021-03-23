package br.com.alura.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.builder.CriadorDeLeilao;
import br.com.alura.leilao.dominio.Lance;
import br.com.alura.leilao.dominio.Leilao;
import br.com.alura.leilao.dominio.Usuario;
import br.com.alura.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@BeforeEach
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		
		this.joao = new Usuario("joao");
	    this.jose = new Usuario("jose");
	    this.maria = new Usuario("maria");
	    
	    System.out.println("--- executa avaliador --- ");
	}
	
	@Test
	public void testeEmOrdemCrescente() {
		// parte 1: cenario
	    Leilao leilao = new Leilao("Playstation 5");

	    // esses valores vao gerar um bug
	    leilao.propoe(new Lance(joao, 250));
	    leilao.propoe(new Lance(jose, 300));
	    leilao.propoe(new Lance(maria, 400));

	    // parte 2: acao
	    leiloeiro.avalia(leilao);

	    // parte 3: validacao
	    assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
	    assertEquals(250, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void testeEmOrdemDecrescente() {
		// parte 1: cenario
	    Leilao leilao = new Leilao("Playstation 5");

	    // esses valores vao gerar um bug
	    leilao.propoe(new Lance(joao, 1000));
	    leilao.propoe(new Lance(jose, 750));
	    leilao.propoe(new Lance(maria, 400));

	    // parte 2: acao
	    leiloeiro.avalia(leilao);

	    // parte 3: validacao
	    assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
	    assertEquals(400, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void testeLielaoComUmLance() {
		
		// parte 1: cenario
	    Leilao leilao = new Leilao("Playstation 5");

	    // esses valores vao gerar um bug
	    leilao.propoe(new Lance(joao, 250));

	    // parte 2: acao
	    leiloeiro.avalia(leilao);

	    // parte 3: validacao
	    assertEquals(250, leiloeiro.getMaiorLance(), 0.00001);
	    assertEquals(250, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		// parte 1: cenario
	    Leilao leilao = new CriadorDeLeilao().para("Playstation 5 Novo")
	    		.lance(joao, 100)
	    		.lance(maria, 200)
	    		.lance(joao, 300)
	    		.lance(maria, 400)
	    		.constroi();
	   
	    // parte 2: acao
	    leiloeiro.avalia(leilao);
	    
	    var maioresLances = leiloeiro.getTresMaioresLances();

	    // parte 3: validacao
	    assertEquals(3, maioresLances.size());
	    assertEquals(400, maioresLances.get(0).getValor(), 0.00001);
	    assertEquals(300, maioresLances.get(1).getValor(), 0.00001);
	    assertEquals(200, maioresLances.get(2).getValor(), 0.00001);
	    
	}
	
	@Test
	public void deveEncontrarOsDoisMaioresLances() {
		// parte 1: cenario
	    Leilao leilao = new Leilao("Playstation 5");

	    // esses valores vao gerar um bug
	    leilao.propoe(new Lance(joao, 100));
	    leilao.propoe(new Lance(jose, 200));
	   
	    // parte 2: acao
	    leiloeiro.avalia(leilao);
	    
	    var maioresLances = leiloeiro.getTresMaioresLances();

	    // parte 3: validacao
	    assertEquals(2, maioresLances.size());
	    assertEquals(200, maioresLances.get(0).getValor(), 0.00001);
	    assertEquals(100, maioresLances.get(1).getValor(), 0.00001); 
	}
	
	@Test
	public void deveRetornarListaVazia() {
		// parte 1: cenario

	    Leilao leilao = new Leilao("Playstation 5");

	   
	    // parte 2: acao
	    leiloeiro.avalia(leilao);
	    
	    var maioresLances = leiloeiro.getTresMaioresLances();

	    // parte 3: validacao
	    assertEquals(0, maioresLances.size());
	}
}
