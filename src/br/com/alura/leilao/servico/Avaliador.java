package br.com.alura.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.alura.leilao.dominio.Lance;
import br.com.alura.leilao.dominio.Leilao;

// funcionalidade para pegar qual foi o maior lance dado
public class Avaliador {

    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;
	private List<Lance> maioresLances;

    public void avalia(Leilao leilao) {
        for (var lance : leilao.getLances()) {
            if (lance.getValor() > maiorLance) {
                this.maiorLance = lance.getValor();
            } 
            if (lance.getValor() < menorLance) {
                this.menorLance = lance.getValor();
            }
        }
        
        maioresLances = new ArrayList<>(leilao.getLances());
        Collections.sort(maioresLances, new Comparator<Lance>() {

			@Override
			public int compare(Lance o1, Lance o2) {
				if (o1.getValor() < o2.getValor()) return 1;
				if (o1.getValor() > o2.getValor()) return -1;
				return 0;
			}
        	
		});
        
        maioresLances = maioresLances.subList(0, maioresLances.size() > 3 ? 3 : maioresLances.size());
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }
    
    public List<Lance> getTresMaioresLances() {
		return maioresLances;
	}
}
