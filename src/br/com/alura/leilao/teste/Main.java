package br.com.alura.leilao.teste;

import br.com.alura.leilao.dominio.Lance;
import br.com.alura.leilao.dominio.Leilao;
import br.com.alura.leilao.dominio.Usuario;
import br.com.alura.leilao.servico.Avaliador;

public class Main {

	public static void main(String[] args) {
		
		Usuario usuario = new Usuario("Jose");
		Lance lance = new Lance(usuario, 2000);
		Leilao leilao = new Leilao("Viagem a NY");
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		System.out.println("It works!");

	}

}
