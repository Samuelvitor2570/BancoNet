package br.com.banconet.service;

import br.com.banconet.model.Conta;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuscaBinariaContasTest {

    @Test
    void deveInserirContasMantendoOrdenacao() {
        BuscaBinariaContas gerenciador = new BuscaBinariaContas();

        gerenciador.inserirConta(new Conta(300, 10.0, "0001"));
        gerenciador.inserirConta(new Conta(100, 20.0, "0002"));
        gerenciador.inserirConta(new Conta(200, 30.0, "0003"));

        Conta[] contas = gerenciador.getContas();

        assertEquals(3, contas.length);
        assertEquals(100, contas[0].getNumero());
        assertEquals(200, contas[1].getNumero());
        assertEquals(300, contas[2].getNumero());
    }

    @Test
    void deveBuscarContaExistente() {
        BuscaBinariaContas gerenciador = new BuscaBinariaContas();
        Conta contaEsperada = new Conta(200, 150.0, "0001");

        gerenciador.inserirConta(new Conta(100, 10.0, "0001"));
        gerenciador.inserirConta(contaEsperada);
        gerenciador.inserirConta(new Conta(300, 30.0, "0001"));

        Conta contaEncontrada = gerenciador.buscarConta(200);

        assertNotNull(contaEncontrada);
        assertSame(contaEsperada, contaEncontrada);
        assertEquals(150.0, contaEncontrada.getSaldo());
    }

    @Test
    void deveRetornarNullQuandoContaNaoExistir() {
        BuscaBinariaContas gerenciador = new BuscaBinariaContas();

        gerenciador.inserirConta(new Conta(100, 10.0, "0001"));
        gerenciador.inserirConta(new Conta(200, 20.0, "0001"));

        assertNull(gerenciador.buscarConta(999));
    }

    @Test
    void deveListarContasPorFaixa() {
        BuscaBinariaContas gerenciador = new BuscaBinariaContas();
        gerenciador.inserirConta(new Conta(10, 1.0, "0001"));
        gerenciador.inserirConta(new Conta(20, 2.0, "0001"));
        gerenciador.inserirConta(new Conta(30, 3.0, "0001"));
        gerenciador.inserirConta(new Conta(40, 4.0, "0001"));

        List<Conta> contas = gerenciador.listarContasFaixa(15, 35);

        assertEquals(2, contas.size());
        assertEquals(20, contas.get(0).getNumero());
        assertEquals(30, contas.get(1).getNumero());
    }

    @Test
    void deveLidarComArrayVazio() {
        BuscaBinariaContas gerenciador = new BuscaBinariaContas();

        assertNull(gerenciador.buscarConta(1));
        assertTrue(gerenciador.listarContasFaixa(1, 10).isEmpty());
        assertEquals(0, gerenciador.getContas().length);
    }

    @Test
    void deveRetornarListaVaziaQuandoFaixaForInvalida() {
        BuscaBinariaContas gerenciador = new BuscaBinariaContas();
        gerenciador.inserirConta(new Conta(10, 1.0, "0001"));

        assertTrue(gerenciador.listarContasFaixa(50, 10).isEmpty());
    }
}
