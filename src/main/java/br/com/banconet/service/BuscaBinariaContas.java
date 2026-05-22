
package br.com.banconet.service;

import br.com.banconet.model.Conta;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BuscaBinariaContas {

    private List<Conta> contas = new ArrayList<>();

    public void inserirConta(Conta conta) {
        contas.add(conta);
        contas.sort(Comparator.comparingLong(Conta::getNumero));
    }

    public Conta buscarConta(long numero) {

        int esquerda = 0;
        int direita = contas.size() - 1;

        while (esquerda <= direita) {

            int meio = (esquerda + direita) / 2;

            long atual = contas.get(meio).getNumero();

            if (atual == numero) {
                return contas.get(meio);
            }

            if (atual < numero) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return null;
    }
}
