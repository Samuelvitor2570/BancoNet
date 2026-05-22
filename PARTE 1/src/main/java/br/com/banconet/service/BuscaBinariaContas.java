package br.com.banconet.service;

import br.com.banconet.model.Conta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Gerencia contas em um array ordenado por número e oferece busca binária.
 */
public class BuscaBinariaContas {

    private Conta[] contas;
    private int quantidade;

    /**
     * Cria o gerenciador com capacidade inicial padrão.
     */
    public BuscaBinariaContas() {
        this(16);
    }

    /**
     * Cria o gerenciador com capacidade inicial definida.
     *
     * @param capacidadeInicial capacidade mínima do array interno
     */
    public BuscaBinariaContas(int capacidadeInicial) {
        if (capacidadeInicial < 0) {
            throw new IllegalArgumentException("Capacidade inicial não pode ser negativa.");
        }
        this.contas = new Conta[Math.max(1, capacidadeInicial)];
        this.quantidade = 0;
    }

    /**
     * Insere uma conta preservando a ordenação crescente pelo número.
     *
     * @param conta conta a ser inserida
     */
    public void inserirConta(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula.");
        }

        ensureCapacity(quantidade + 1);

        int posicao = localizarIndiceInsercao(conta.getNumero());
        if (quantidade - posicao >= 0) {
            System.arraycopy(contas, posicao, contas, posicao + 1, quantidade - posicao);
        }

        contas[posicao] = conta;
        quantidade++;
    }

    /**
     * Busca uma conta pelo número usando busca binária.
     *
     * @param numero número da conta
     * @return conta encontrada ou null
     */
    public Conta buscarConta(long numero) {
        int esquerda = 0;
        int direita = quantidade - 1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            long numeroMeio = contas[meio].getNumero();

            if (numeroMeio == numero) {
                return contas[meio];
            }

            if (numeroMeio < numero) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return null;
    }

    /**
     * Lista as contas dentro de uma faixa inclusiva de números.
     *
     * @param inicio número inicial da faixa
     * @param fim    número final da faixa
     * @return lista com as contas na faixa informada
     */
    public List<Conta> listarContasFaixa(long inicio, long fim) {
        List<Conta> resultado = new ArrayList<>();
        if (quantidade == 0 || inicio > fim) {
            return resultado;
        }

        int indiceInicial = localizarPrimeiroIndiceMaiorOuIgual(inicio);
        for (int i = indiceInicial; i < quantidade; i++) {
            long numeroAtual = contas[i].getNumero();
            if (numeroAtual > fim) {
                break;
            }
            resultado.add(contas[i]);
        }

        return resultado;
    }

    /**
     * Retorna a quantidade atual de contas armazenadas.
     *
     * @return quantidade de contas
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Retorna uma cópia das contas cadastradas até o momento.
     *
     * @return array ordenado de contas
     */
    public Conta[] getContas() {
        return Arrays.copyOf(contas, quantidade);
    }

    private void ensureCapacity(int capacidadeMinima) {
        if (capacidadeMinima <= contas.length) {
            return;
        }

        int novaCapacidade = contas.length + (contas.length / 2);
        if (novaCapacidade < capacidadeMinima) {
            novaCapacidade = capacidadeMinima;
        }

        contas = Arrays.copyOf(contas, novaCapacidade);
    }

    private int localizarIndiceInsercao(long numero) {
        int esquerda = 0;
        int direita = quantidade - 1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            long numeroMeio = contas[meio].getNumero();

            if (numeroMeio < numero) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return esquerda;
    }

    private int localizarPrimeiroIndiceMaiorOuIgual(long numero) {
        int esquerda = 0;
        int direita = quantidade - 1;
        int resultado = quantidade;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;

            if (contas[meio].getNumero() >= numero) {
                resultado = meio;
                direita = meio - 1;
            } else {
                esquerda = meio + 1;
            }
        }

        return resultado;
    }
}
