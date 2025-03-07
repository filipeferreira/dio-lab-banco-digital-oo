package dio.me.labbancodigital.model;

import dio.me.labbancodigital.exception.SaldoInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaPoupancaTest {

    private Cliente cliente;
    private ContaPoupanca contaPoupanca;

    @BeforeEach
    void setUp() {
        this.cliente = new Cliente();
        cliente.setNome("Fulano");

        this.contaPoupanca = new ContaPoupanca(cliente);
        this.contaPoupanca.depositar(100);


    }

    @Test
    void sacar() {
        assertThrows(SaldoInsuficienteException.class, () -> contaPoupanca.sacar(500));
        assertThrows(IllegalArgumentException.class, () -> contaPoupanca.sacar(-50));
        assertDoesNotThrow(() -> contaPoupanca.sacar(50));
        assertEquals(50, contaPoupanca.getSaldo());
        assertDoesNotThrow(() -> contaPoupanca.sacar(50));
        assertEquals(0, contaPoupanca.getSaldo());
        assertThrows(SaldoInsuficienteException.class, () -> contaPoupanca.sacar(50));
    }

    @Test
    void depositar() {
        assertDoesNotThrow(() -> contaPoupanca.depositar(50));
        assertEquals(150, contaPoupanca.getSaldo());
        assertDoesNotThrow(() -> contaPoupanca.depositar(50));
        assertEquals(200, contaPoupanca.getSaldo());
        assertThrows(IllegalArgumentException.class, () -> contaPoupanca.depositar(-50));
    }

    @Test
    void transferir() {
        ContaCorrente contaPoupanca = new ContaCorrente(cliente, 100);
        contaPoupanca.depositar(100);
        assertDoesNotThrow(() -> this.contaPoupanca.transferir(50, contaPoupanca));
        assertEquals(50, this.contaPoupanca.getSaldo());
        assertEquals(150, contaPoupanca.getSaldo());
        assertDoesNotThrow(() -> this.contaPoupanca.transferir(50, contaPoupanca));
        assertEquals(0, this.contaPoupanca.getSaldo());
        assertEquals(200, contaPoupanca.getSaldo());
        assertThrows(IllegalArgumentException.class, () -> this.contaPoupanca.transferir(-50, contaPoupanca));
        assertThrows(SaldoInsuficienteException.class, () -> this.contaPoupanca.transferir(100, contaPoupanca));
    }
}
