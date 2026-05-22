
package br.com.banconet.model;

import java.time.LocalDateTime;

public class Transacao {

    private Conta destino;
    private double valor;
    private LocalDateTime data;

    public Transacao(Conta destino, double valor) {
        this.destino = destino;
        this.valor = valor;
        this.data = LocalDateTime.now();
    }

    public Conta getDestino() {
        return destino;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }
}
