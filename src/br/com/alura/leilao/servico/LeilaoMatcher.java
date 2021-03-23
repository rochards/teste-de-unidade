package br.com.alura.leilao.servico;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.com.alura.leilao.dominio.Lance;
import br.com.alura.leilao.dominio.Leilao;

public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {
	
	private final Lance lance;

	public LeilaoMatcher(Lance lance) {
		super();
		this.lance = lance;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("leilão com lance" + lance.getValor());		
	}

	@Override
	protected boolean matchesSafely(Leilao item) {
		return item.getLances().contains(lance);
	}
	
	public static Matcher<Leilao> temUmLance(Lance lance) {
		return new LeilaoMatcher(lance);
	}

}
