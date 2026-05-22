
package br.com.banconet.service;

import br.com.banconet.model.BSTNode;
import br.com.banconet.model.Conta;

public class BSTConta {

    private BSTNode root;

    public void inserir(Conta conta) {
        root = inserirRec(root, conta);
    }

    private BSTNode inserirRec(BSTNode node, Conta conta) {

        if (node == null) {
            return new BSTNode(conta);
        }

        if (conta.getSaldo() < node.getConta().getSaldo()) {
            node.setLeft(inserirRec(node.getLeft(), conta));
        } else {
            node.setRight(inserirRec(node.getRight(), conta));
        }

        return node;
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(BSTNode node) {

        if (node != null) {

            inOrderRec(node.getLeft());

            System.out.println(node.getConta());

            inOrderRec(node.getRight());
        }
    }
}
