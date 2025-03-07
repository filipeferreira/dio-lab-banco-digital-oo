package dio.me.labbancodigital.model;

import dio.me.labbancodigital.exception.SaldoInsuficienteException;

public final class ContaCorrente extends Conta {

	private double limite;

	public ContaCorrente(Cliente cliente, double limite) {
		super(cliente);
		this.limite = limite;
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
	}

	@Override
	public void sacar(double valor) throws SaldoInsuficienteException {
		super.sacar(valor);
	}

	@Override
	protected boolean possuiSaldoSuficiente(double valor) {
		return super.getSaldo() + this.limite >= valor;
	}
}
