
package br.com.banconet.model;

public class Conta {

    private long numero;
    private String titular;
    private String agencia;
    private double saldo;

    public Conta(long numero, String titular, String agencia, double saldo) {
        this.numero = numero;
        this.titular = titular;
        this.agencia = agencia;
        this.saldo = saldo;
    }

    public long getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public String getAgencia() {
        return agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero=" + numero +
                ", titular='" + titular + '\'' +
                ", agencia='" + agencia + '\'' +
                ", saldo=R$" + saldo +
                '}';
    }
}
