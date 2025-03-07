package dio.me.labbancodigital.model;

import dio.me.labbancodigital.exception.SaldoInsuficienteException;

public sealed interface IConta permits Conta {
	
	void sacar(double valor) throws SaldoInsuficienteException;
	
	void depositar(double valor);
	
	void transferir(double valor, IConta contaDestino) throws SaldoInsuficienteException;
	
	void imprimirExtrato();
}
