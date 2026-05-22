package br.com.banconet.service;

import br.com.banconet.model.Conta;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários utilizando JUnit 5 para validar a implementação da BSTConta.
 * Garante o correto funcionamento de todas as regras de negócio e limites de complexidade.
 */
class BSTContaTest {

    @Test
    void deveIniciarArvoreVazia() {
        BSTConta bst = new BSTConta();
        assertNull(bst.getRaiz());
        assertEquals(0, bst.getQuantidade());
        assertTrue(bst.listarInOrder().isEmpty());
    }

    @Test
    void deveInserirContasAumentandoQuantidade() {
        BSTConta bst = new BSTConta();

        bst.inserir(new Conta(1L, 150.0, "0001"));
        bst.inserir(new Conta(2L, 50.0, "0001"));
        bst.inserir(new Conta(3L, 250.0, "0002"));

        assertEquals(3, bst.getQuantidade());
        assertNotNull(bst.getRaiz());
        assertEquals(150.0, bst.getRaiz().getValor().getSaldo());
        assertEquals(50.0, bst.getRaiz().getEsquerdo().getValor().getSaldo());
        assertEquals(250.0, bst.getRaiz().getDireito().getValor().getSaldo());
    }

    @Test
    void deveLancarExcecaoAoInserirContaNula() {
        BSTConta bst = new BSTConta();
        assertThrows(IllegalArgumentException.class, () -> bst.inserir(null));
    }

    @Test
    void deveListarContasEmOrdemInOrder() {
        BSTConta bst = new BSTConta();
        
        // Inserção em ordem não classificada
        bst.inserir(new Conta(1L, 500.0, "0001"));
        bst.inserir(new Conta(2L, 100.0, "0001"));
        bst.inserir(new Conta(3L, 300.0, "0002"));
        bst.inserir(new Conta(4L, 700.0, "0003"));
        bst.inserir(new Conta(5L, 200.0, "0002"));

        List<Conta> contasOrdenadas = bst.listarInOrder();

        assertEquals(5, contasOrdenadas.size());
        assertEquals(100.0, contasOrdenadas.get(0).getSaldo());
        assertEquals(200.0, contasOrdenadas.get(1).getSaldo());
        assertEquals(300.0, contasOrdenadas.get(2).getSaldo());
        assertEquals(500.0, contasOrdenadas.get(3).getSaldo());
        assertEquals(700.0, contasOrdenadas.get(4).getSaldo());
    }

    @Test
    void deveBuscarContasPorSaldoUnicoEDuplicado() {
        BSTConta bst = new BSTConta();

        bst.inserir(new Conta(1L, 150.0, "0001"));
        bst.inserir(new Conta(2L, 200.0, "0001"));
        bst.inserir(new Conta(3L, 150.0, "0002")); // Saldo duplicado
        bst.inserir(new Conta(4L, 50.0, "0001"));
        bst.inserir(new Conta(5L, 150.0, "0003")); // Saldo duplicado

        // Caso 1: Saldo não existente
        List<Conta> resultadoVazio = bst.buscarSaldo(999.0);
        assertTrue(resultadoVazio.isEmpty());

        // Caso 2: Saldo com único correspondente
        List<Conta> resultadoUnico = bst.buscarSaldo(200.0);
        assertEquals(1, resultadoUnico.size());
        assertEquals(2L, resultadoUnico.get(0).getId());

        // Caso 3: Saldo com múltiplos correspondentes (duplicados)
        List<Conta> resultadoMultiplo = bst.buscarSaldo(150.0);
        assertEquals(3, resultadoMultiplo.size());
        assertEquals(1L, resultadoMultiplo.get(0).getId());
        assertEquals(3L, resultadoMultiplo.get(1).getId());
        assertEquals(5L, resultadoMultiplo.get(2).getId());
    }

    @Test
    void deveListarClientesInadimplentesComPoda() {
        BSTConta bst = new BSTConta();

        bst.inserir(new Conta(1L, 150.0, "0001")); // Regular
        bst.inserir(new Conta(2L, 80.0, "0001"));  // Inadimplente
        bst.inserir(new Conta(3L, 99.9, "0002"));  // Inadimplente
        bst.inserir(new Conta(4L, 50.0, "0003"));  // Inadimplente
        bst.inserir(new Conta(5L, 200.0, "0001")); // Regular
        bst.inserir(new Conta(6L, 100.0, "0002")); // Limiar (Regular)

        List<Conta> inadimplentes = bst.listarInadimplentes();

        // Espera-se: 50.0, 80.0, 99.9
        assertEquals(3, inadimplentes.size());
        assertEquals(50.0, inadimplentes.get(0).getSaldo());
        assertEquals(80.0, inadimplentes.get(1).getSaldo());
        assertEquals(99.9, inadimplentes.get(2).getSaldo());
    }

    @Test
    void deveListarInadimplentesVazioSeTodosForemRegulares() {
        BSTConta bst = new BSTConta();
        bst.inserir(new Conta(1L, 100.0, "0001"));
        bst.inserir(new Conta(2L, 150.0, "0001"));
        bst.inserir(new Conta(3L, 500.0, "0001"));

        List<Conta> inadimplentes = bst.listarInadimplentes();
        assertTrue(inadimplentes.isEmpty());
    }

    @Test
    void deveRetornarTopNMaioresSaldos() {
        BSTConta bst = new BSTConta();

        bst.inserir(new Conta(1L, 100.0, "0001"));
        bst.inserir(new Conta(2L, 500.0, "0001"));
        bst.inserir(new Conta(3L, 250.0, "0002"));
        bst.inserir(new Conta(4L, 900.0, "0003"));
        bst.inserir(new Conta(5L, 50.0, "0001"));
        bst.inserir(new Conta(6L, 500.0, "0002")); // Duplicado de 500.0

        // Caso 1: N <= 0
        assertTrue(bst.topNMaioresSaldos(0).isEmpty());
        assertTrue(bst.topNMaioresSaldos(-5).isEmpty());

        // Caso 2: Top 3 maiores saldos
        // Espera-se: 900.0, depois os dois de 500.0
        List<Conta> top3 = bst.topNMaioresSaldos(3);
        assertEquals(3, top3.size());
        assertEquals(900.0, top3.get(0).getSaldo());
        assertEquals(500.0, top3.get(1).getSaldo());
        assertEquals(500.0, top3.get(2).getSaldo());

        // Caso 3: N maior do que o tamanho da árvore
        // Deve retornar todos os elementos em ordem decrescente (tamanho 6)
        List<Conta> top10 = bst.topNMaioresSaldos(10);
        assertEquals(6, top10.size());
        assertEquals(900.0, top10.get(0).getSaldo());
        assertEquals(500.0, top10.get(1).getSaldo());
        assertEquals(500.0, top10.get(2).getSaldo());
        assertEquals(250.0, top10.get(3).getSaldo());
        assertEquals(100.0, top10.get(4).getSaldo());
        assertEquals(50.0, top10.get(5).getSaldo());
    }
}
