package br.com.alura.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

    private String descricao;
    private List<Lance> lances;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<Lance>();
    }

    public void propoe(Lance lance) {
    	// Regra: usuario nao deve dar dois lances seguidos
    	if (lances.isEmpty() || !ultimoLanceDado().getUsuario().equals(lance.getUsuario())) {
    		lances.add(lance);
    	}
    }

	private Lance ultimoLanceDado() {
		var listSize = lances.size();
		return lances.get(listSize - 1);
	}

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }

}
