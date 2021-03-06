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
    	// Regra: usuario nao deve dar mais de 5 lances
    	int total = qtdLancesDoUsuario(lance.getUsuario());
    	
    	// Regra: usuario nao deve dar dois lances seguidos
    	if (lances.isEmpty() || (!ultimoLanceDado().getUsuario().equals(lance.getUsuario()) && total < 5)) {
    		lances.add(lance);
    	}
    }
    

	public void dobraLance(Usuario usuario) {
		Lance ultimoLance = null;
		for (var l : lances) {
			if (l.getUsuario().equals(usuario)) {
				ultimoLance = l;
				break;
			}
		}
		if (ultimoLance == null) {
			return;
		}
		
		var valorDobrado = ultimoLance.getValor() * 2;
		Lance lanceDobrado = new Lance(usuario, valorDobrado);
		
		propoe(lanceDobrado);
	}

	private int qtdLancesDoUsuario(Usuario usuario) {
		int total = 0;
    	for (var l : lances) {
    		if (l.getUsuario().equals(usuario)) {
    			total++;
    		}
    	}
		return total;
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
