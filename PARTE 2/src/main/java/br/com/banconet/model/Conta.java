package br.com.banconet.model;

/**
 * Entidade que representa uma conta bancária no BancoNet.
 * Contém os atributos básicos exigidos para a Semana 2: id, saldo e agencia.
 */
public class Conta {

    private long id;
    private double saldo;
    private String agencia;

    /**
     * Construtor padrão (sem argumentos) para conformidade com padrões POO.
     */
    public Conta() {
    }

    /**
     * Construtor completo da conta.
     *
     * @param id      identificador único da conta
     * @param saldo   saldo inicial da conta
     * @param agencia agência à qual a conta pertence
     */
    public Conta(long id, double saldo, String agencia) {
        this.id = id;
        this.saldo = saldo;
        this.agencia = agencia;
    }

    /**
     * Obtém o identificador único da conta.
     *
     * @return id da conta
     */
    public long getId() {
        return id;
    }

    /**
     * Define o identificador da conta.
     *
     * @param id novo id da conta
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtém o saldo atual da conta.
     *
     * @return saldo da conta
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Define o saldo da conta.
     *
     * @param saldo novo saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtém a agência vinculada à conta.
     *
     * @return agência da conta
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * Define a agência vinculada à conta.
     *
     * @param agencia nova agência
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    @Override
    public String toString() {
        return String.format("Conta{id=%d, saldo=R$ %.2f, agencia='%s'}", id, saldo, agencia);
    }
}
