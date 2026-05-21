package br.com.banconet.model;

/**
 * Representa uma conta bancária no BancoNet.
 */
public class Conta {

    private long numero;
    private double saldo;
    private String agencia;

    /**
     * Cria uma nova conta.
     *
     * @param numero  número identificador da conta
     * @param saldo   saldo inicial
     * @param agencia agência vinculada à conta
     */
    public Conta(long numero, double saldo, String agencia) {
        this.numero = numero;
        this.saldo = saldo;
        this.agencia = agencia;
    }

    /**
     * Retorna o número da conta.
     *
     * @return número da conta
     */
    public long getNumero() {
        return numero;
    }

    /**
     * Atualiza o número da conta.
     *
     * @param numero novo número da conta
     */
    public void setNumero(long numero) {
        this.numero = numero;
    }

    /**
     * Retorna o saldo da conta.
     *
     * @return saldo da conta
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Atualiza o saldo da conta.
     *
     * @param saldo novo saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Retorna a agência da conta.
     *
     * @return agência
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * Atualiza a agência da conta.
     *
     * @param agencia nova agência
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
}
