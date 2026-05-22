
package br.com.banconet.service;

import br.com.banconet.model.Conta;
import br.com.banconet.model.Transacao;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class GrafoTransacoes {

    private Map<Long, Conta> contas = new HashMap<>();
    private Map<Long, List<Transacao>> grafo = new HashMap<>();

    public void adicionarConta(Conta conta) {
        contas.put(conta.getNumero(), conta);
        grafo.put(conta.getNumero(), new ArrayList<>());
    }

    public void transferir(long origem, long destino, double valor) {

        Conta contaOrigem = contas.get(origem);
        Conta contaDestino = contas.get(destino);

        if (contaOrigem == null || contaDestino == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        if (contaOrigem.getSaldo() < valor) {
            System.out.println("Saldo insuficiente.");
            return;
        }

        contaOrigem.sacar(valor);
        contaDestino.depositar(valor);

        grafo.get(origem).add(new Transacao(contaDestino, valor));

        System.out.println("Transferência realizada.");
    }

    public void detectarFraudes() {

        System.out.println("\n=== DETECÇÃO DE FRAUDES ===");

        for (Long conta : grafo.keySet()) {

            List<Transacao> transacoes = grafo.get(conta);

            if (transacoes.size() >= 5) {

                LocalDateTime primeira = transacoes.get(0).getData();
                LocalDateTime ultima = transacoes.get(transacoes.size() - 1).getData();

                long minutos = Duration.between(primeira, ultima).toMinutes();

                if (minutos <= 2) {
                    System.out.println("Conta suspeita: " + conta);
                }
            }
        }
    }

    public void mostrarGrafo() {

        for (Long conta : grafo.keySet()) {

            System.out.println("\nConta " + conta);

            for (Transacao t : grafo.get(conta)) {
                System.out.println(" -> " + t.getDestino().getTitular() + " R$" + t.getValor());
            }
        }
    }
}
