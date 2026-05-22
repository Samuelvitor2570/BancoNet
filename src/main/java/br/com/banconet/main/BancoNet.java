
package br.com.banconet.main;

import br.com.banconet.model.Conta;
import br.com.banconet.service.BSTConta;
import br.com.banconet.service.BuscaBinariaContas;
import br.com.banconet.service.GrafoTransacoes;

import java.util.Scanner;

public class BancoNet {

    private static BuscaBinariaContas busca = new BuscaBinariaContas();
    private static BSTConta bst = new BSTConta();
    private static GrafoTransacoes grafo = new GrafoTransacoes();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== BANCONET ===");
            System.out.println("1 - Cadastrar Conta");
            System.out.println("2 - Buscar Conta");
            System.out.println("3 - Transferência");
            System.out.println("4 - Relatório BST");
            System.out.println("5 - Detectar Fraudes");
            System.out.println("6 - Mostrar Grafo");
            System.out.println("0 - Sair");

            int op = scanner.nextInt();

            switch (op) {

                case 1:

                    System.out.print("Número: ");
                    long numero = scanner.nextLong();

                    scanner.nextLine();

                    System.out.print("Titular: ");
                    String titular = scanner.nextLine();

                    System.out.print("Agência: ");
                    String agencia = scanner.nextLine();

                    System.out.print("Saldo: ");
                    double saldo = scanner.nextDouble();

                    Conta conta = new Conta(numero, titular, agencia, saldo);

                    busca.inserirConta(conta);
                    bst.inserir(conta);
                    grafo.adicionarConta(conta);

                    System.out.println("Conta cadastrada.");
                    break;

                case 2:

                    System.out.print("Número da conta: ");
                    long buscaNumero = scanner.nextLong();

                    Conta encontrada = busca.buscarConta(buscaNumero);

                    if (encontrada != null) {
                        System.out.println(encontrada);
                    } else {
                        System.out.println("Conta não encontrada.");
                    }

                    break;

                case 3:

                    System.out.print("Origem: ");
                    long origem = scanner.nextLong();

                    System.out.print("Destino: ");
                    long destino = scanner.nextLong();

                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();

                    grafo.transferir(origem, destino, valor);

                    break;

                case 4:
                    bst.inOrder();
                    break;

                case 5:
                    grafo.detectarFraudes();
                    break;

                case 6:
                    grafo.mostrarGrafo();
                    break;

                case 0:
                    return;
            }
        }
    }
}
