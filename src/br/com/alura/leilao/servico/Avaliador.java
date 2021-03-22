package br.com.alura.leilao.servico;

import br.com.alura.leilao.dominio.Leilao;

// funcionalidade para pegar qual foi o maior lance dado
public class Avaliador {

    private double maiorLance = Double.NEGATIVE_INFINITY;
    private double menorLance = Double.POSITIVE_INFINITY;

    public void avalia(Leilao leilao) {
        for (var lance : leilao.getLances()) {
            if (lance.getValor() > maiorLance) {
                this.maiorLance = lance.getValor();
            } 
            if (lance.getValor() < menorLance) {
                this.menorLance = lance.getValor();
            }
        }
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public double getMenorLance() {
        return menorLance;
    }
}
