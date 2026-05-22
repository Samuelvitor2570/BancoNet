
package br.com.banconet.model;

public class BSTNode {

    private Conta conta;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(Conta conta) {
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
}
