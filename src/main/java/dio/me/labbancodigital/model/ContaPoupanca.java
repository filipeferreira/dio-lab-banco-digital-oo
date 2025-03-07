package dio.me.labbancodigital.model;

public final class ContaPoupanca extends Conta {

	public ContaPoupanca(Cliente cliente) {
		super(cliente);
	}

	@Override
	protected boolean possuiSaldoSuficiente(double valor) {
		return super.saldo >= valor;
	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Poupança ===");
		super.imprimirInfosComuns();
	}
}
