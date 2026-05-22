package br.com.banconet.service;

import br.com.banconet.model.BSTNode;
import br.com.banconet.model.Conta;

import java.util.ArrayList;
import java.util.List;

/**
 * Gerencia contas bancárias utilizando uma estrutura de Árvore Binária de Busca (BST).
 * As contas são ordenadas na árvore tendo o saldo como chave de ordenação.
 * 
 * Princípios de POO: Encapsulamento, ocultação de informação e delegação de comportamento.
 */
public class BSTConta {

    private BSTNode<Conta> raiz;
    private int quantidade;

    /**
     * Construtor padrão da árvore.
     */
    public BSTConta() {
        this.raiz = null;
        this.quantidade = 0;
    }

    /**
     * Insere uma nova conta na BST usando o saldo como chave de ordenação.
     * 
     * Complexidade Algorítmica:
     * - Tempo (Caso Médio): O(log n) - A cada comparação, metade da subárvore é descartada.
     * - Tempo (Pior Caso): O(n) - Ocorre se as contas forem inseridas em ordem já ordenada (árvore degenerada).
     * - Espaço: O(h) da pilha de recursão, onde h é a altura da árvore (log n no caso médio, n no pior caso).
     *
     * @param conta conta a ser inserida. Não pode ser nula.
     */
    public void inserir(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula.");
        }
        this.raiz = inserirRec(this.raiz, conta);
        this.quantidade++;
    }

    private BSTNode<Conta> inserirRec(BSTNode<Conta> noAtual, Conta conta) {
        if (noAtual == null) {
            return new BSTNode<>(conta);
        }

        // Chave de ordenação: Saldo
        double saldoNovo = conta.getSaldo();
        double saldoNo = noAtual.getValor().getSaldo();

        if (saldoNovo < saldoNo) {
            noAtual.setEsquerdo(inserirRec(noAtual.getEsquerdo(), conta));
        } else {
            // Valores maiores ou iguais vão para a subárvore direita.
            // Isso permite lidar com contas que possuem o mesmo saldo (saldos duplicados).
            noAtual.setDireito(inserirRec(noAtual.getDireito(), conta));
        }

        return noAtual;
    }

    /**
     * Busca contas que possuem exatamente o saldo informado.
     * 
     * Complexidade Algorítmica:
     * - Tempo (Caso Médio): O(log n + k) - onde k é a quantidade de contas com o mesmo saldo.
     *   A busca caminha O(log n) até encontrar o saldo, e depois explora apenas o ramo direito
     *   para coletar duplicados de forma eficiente.
     * - Tempo (Pior Caso): O(n) - árvore degenerada.
     * - Espaço: O(h) devido ao stack de recursão.
     *
     * @param saldo saldo a ser pesquisado.
     * @return lista de contas com o saldo especificado (pode ser vazia).
     */
    public List<Conta> buscarSaldo(double saldo) {
        List<Conta> resultado = new ArrayList<>();
        buscarSaldoRec(this.raiz, saldo, resultado);
        return resultado;
    }

    private void buscarSaldoRec(BSTNode<Conta> noAtual, double saldo, List<Conta> resultado) {
        if (noAtual == null) {
            return;
        }

        double saldoNo = noAtual.getValor().getSaldo();

        if (saldo == saldoNo) {
            resultado.add(noAtual.getValor());
            // Como duplicados são inseridos à direita, outros saldos iguais só podem estar na subárvore direita.
            buscarSaldoRec(noAtual.getDireito(), saldo, resultado);
        } else if (saldo < saldoNo) {
            buscarSaldoRec(noAtual.getEsquerdo(), saldo, resultado);
        } else {
            buscarSaldoRec(noAtual.getDireito(), saldo, resultado);
        }
    }

    /**
     * Retorna a lista das N contas com os maiores saldos, ordenada do maior para o menor.
     * Relatório de clientes VIP.
     * 
     * Complexidade Algorítmica:
     * - Tempo: O(log n + n) no caso geral, mas otimizada para O(log n + N) onde N é o parâmetro informado.
     *   Utiliza um percurso em-ordem reverso (Direita-Raiz-Esquerda) e interrompe a execução
     *   assim que preenche os N elementos, evitando percorrer toda a árvore de forma desnecessária.
     * - Espaço: O(h) pilha de recursão.
     *
     * @param n quantidade de contas a retornar.
     * @return lista das N contas com maiores saldos.
     */
    public List<Conta> topNMaioresSaldos(int n) {
        List<Conta> resultado = new ArrayList<>();
        if (n <= 0) {
            return resultado;
        }
        topNMaioresSaldosRec(this.raiz, n, resultado);
        return resultado;
    }

    private void topNMaioresSaldosRec(BSTNode<Conta> noAtual, int n, List<Conta> resultado) {
        // Poda: se o nó for nulo ou se já coletamos os N elementos, paramos a recursão.
        if (noAtual == null || resultado.size() >= n) {
            return;
        }

        // 1. Visitar subárvore direita (valores maiores)
        topNMaioresSaldosRec(noAtual.getDireito(), n, resultado);

        // 2. Visitar nó atual
        if (resultado.size() < n) {
            resultado.add(noAtual.getValor());
        }

        // 3. Visitar subárvore esquerda (valores menores)
        topNMaioresSaldosRec(noAtual.getEsquerdo(), n, resultado);
    }

    /**
     * Retorna uma lista de clientes inadimplentes (contas com saldo menor que R$ 100,00).
     * 
     * Complexidade Algorítmica:
     * - Tempo (Caso Médio): O(log n + k) - onde k é o número de contas inadimplentes.
     *   Realiza uma poda (pruning): se o nó atual tiver saldo >= 100, sabemos que toda a sua subárvore
     *   direita também terá saldos >= 100. Portanto, evitamos visitar a subárvore direita nesse caso.
     * - Espaço: O(h) pilha de recursão.
     *
     * @return lista de contas inadimplentes em ordem crescente de saldo.
     */
    public List<Conta> listarInadimplentes() {
        List<Conta> resultado = new ArrayList<>();
        listarInadimplentesRec(this.raiz, resultado);
        return resultado;
    }

    private void listarInadimplentesRec(BSTNode<Conta> noAtual, List<Conta> resultado) {
        if (noAtual == null) {
            return;
        }

        double saldoNo = noAtual.getValor().getSaldo();

        // 1. Sempre explora a subárvore esquerda (contém saldos menores, que podem ser < 100)
        listarInadimplentesRec(noAtual.getEsquerdo(), resultado);

        // 2. Processa o nó atual se for inadimplente
        if (saldoNo < 100.0) {
            resultado.add(noAtual.getValor());
            
            // 3. Só explora a subárvore direita se o saldo atual for < 100.
            // Se o saldo atual for >= 100, a subárvore direita só tem saldos maiores ou iguais, logo não há inadimplentes.
            listarInadimplentesRec(noAtual.getDireito(), resultado);
        }
    }

    /**
     * Retorna a lista de todas as contas em ordem crescente de saldo.
     * Realiza um percurso in-order (Esquerda-Raiz-Direita).
     * 
     * Complexidade Algorítmica:
     * - Tempo: O(n) - Visita obrigatoriamente todos os n nós da árvore exatamente uma vez.
     * - Espaço: O(h) da pilha de recursão para controlar a travessia.
     *
     * @return lista ordenada de contas.
     */
    public List<Conta> listarInOrder() {
        List<Conta> resultado = new ArrayList<>();
        listarInOrderRec(this.raiz, resultado);
        return resultado;
    }

    private void listarInOrderRec(BSTNode<Conta> noAtual, List<Conta> resultado) {
        if (noAtual == null) {
            return;
        }

        listarInOrderRec(noAtual.getEsquerdo(), resultado);
        resultado.add(noAtual.getValor());
        listarInOrderRec(noAtual.getDireito(), resultado);
    }

    /**
     * Retorna a raiz da BST.
     *
     * @return raiz da árvore
     */
    public BSTNode<Conta> getRaiz() {
        return raiz;
    }

    /**
     * Retorna a quantidade total de contas cadastradas na árvore.
     * 
     * Complexidade Algorítmica:
     * - Tempo: O(1) - Mantido via contador atualizado a cada inserção.
     *
     * @return quantidade de contas
     */
    public int getQuantidade() {
        return quantidade;
    }
}
