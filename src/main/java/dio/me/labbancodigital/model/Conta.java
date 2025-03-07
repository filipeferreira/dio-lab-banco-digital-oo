package dio.me.labbancodigital.model;

import dio.me.labbancodigital.exception.SaldoInsuficienteException;
import lombok.Getter;

@Getter
public sealed abstract class Conta implements IConta permits ContaCorrente, ContaPoupanca {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	private double saldo;
	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) throws SaldoInsuficienteException {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor de saque inválido");
		}
		if (!this.possuiSaldoSuficiente(valor)) {
			throw new SaldoInsuficienteException();
		}
		saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor de depósito inválido");
		}
		saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) throws SaldoInsuficienteException {
		if (valor <= 0) {
			throw new IllegalArgumentException("Valor de transferência inválido");
		}
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	protected abstract boolean possuiSaldoSuficiente(double valor);

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
}
