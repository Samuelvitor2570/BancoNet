package br.com.banconet.model;

/**
 * Representa um nó genérico em uma Árvore Binária de Busca (BST).
 * Segue estritamente os princípios de Programação Orientada a Objetos (POO).
 *
 * Complexidade algorítmica: Cada nó mantém referências para os seus filhos esquerdo e direito.
 * O encadeamento de referências permite que operações de inserção, busca e deleção ocorram
 * em complexidade de tempo de O(log n) no caso médio (quando a árvore está balanceada), 
 * e O(n) no pior caso (quando a árvore se degenera em uma lista encadeada).
 *
 * @param <T> o tipo do objeto armazenado no nó.
 */
public class BSTNode<T> {

    private T valor;
    private BSTNode<T> esquerdo;
    private BSTNode<T> direito;

    /**
     * Construtor para criar um novo nó com o valor fornecido.
     *
     * @param valor o objeto a ser encapsulado pelo nó. Não pode ser nulo.
     */
    public BSTNode(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O valor contido no nó não pode ser nulo.");
        }
        this.valor = valor;
        this.esquerdo = null;
        this.direito = null;
    }

    /**
     * Retorna o valor contido no nó.
     *
     * @return o valor do nó
     */
    public T getValor() {
        return valor;
    }

    /**
     * Define o valor contido no nó.
     *
     * @param valor novo valor
     */
    public void setValor(T valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O valor contido no nó não pode ser nulo.");
        }
        this.valor = valor;
    }

    /**
     * Retorna a referência para o nó da subárvore esquerda (saldos menores).
     *
     * @return o nó esquerdo
     */
    public BSTNode<T> getEsquerdo() {
        return esquerdo;
    }

    /**
     * Define o nó da subárvore esquerda.
     *
     * @param esquerdo o novo nó esquerdo
     */
    public void setEsquerdo(BSTNode<T> esquerdo) {
        this.esquerdo = esquerdo;
    }

    /**
     * Retorna a referência para o nó da subárvore direita (saldos maiores/iguais).
     *
     * @return o nó direito
     */
    public BSTNode<T> getDireito() {
        return direito;
    }

    /**
     * Define o nó da subárvore direita.
     *
     * @param direito o novo nó direito
     */
    public void setDireito(BSTNode<T> direito) {
        this.direito = direito;
    }
}
