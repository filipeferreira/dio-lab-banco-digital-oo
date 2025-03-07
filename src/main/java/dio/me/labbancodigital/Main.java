package dio.me.labbancodigital;

import dio.me.labbancodigital.exception.SaldoInsuficienteException;
import dio.me.labbancodigital.model.Cliente;
import dio.me.labbancodigital.model.Conta;
import dio.me.labbancodigital.model.ContaCorrente;
import dio.me.labbancodigital.model.ContaPoupanca;

public class Main {

	public static void main(String[] args) throws SaldoInsuficienteException {
		Cliente venilton = new Cliente();
		venilton.setNome("Venilton");
		
		Conta cc = new ContaCorrente(venilton, 100);
		Conta poupanca = new ContaPoupanca(venilton);

		cc.depositar(100);
		cc.transferir(100, poupanca);
		
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
	}

}
