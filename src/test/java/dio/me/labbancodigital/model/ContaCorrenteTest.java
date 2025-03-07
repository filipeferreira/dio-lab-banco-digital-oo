package dio.me.labbancodigital.model;

import dio.me.labbancodigital.exception.SaldoInsuficienteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContaCorrenteTest {

    private Cliente cliente;
    private ContaCorrente contaCorrente;

    @BeforeEach
    void setUp() {
        this.cliente = new Cliente();
        cliente.setNome("Fulano");

        this.contaCorrente = new ContaCorrente(cliente, 100);
        this.contaCorrente.depositar(100);


    }

    @Test
    void sacar() {
        assertThrows(IllegalArgumentException.class, () -> contaCorrente.sacar(-50));
        assertDoesNotThrow(() -> contaCorrente.sacar(50));
        assertEquals(50, contaCorrente.getSaldo());
        assertDoesNotThrow(() -> contaCorrente.sacar(50));
        assertEquals(0, contaCorrente.getSaldo());
        assertDoesNotThrow(() -> contaCorrente.sacar(50));
        assertEquals(-50, contaCorrente.getSaldo());
        assertDoesNotThrow(() -> contaCorrente.sacar(50));
        assertEquals(-100, contaCorrente.getSaldo());
        assertThrows(SaldoInsuficienteException.class, () -> contaCorrente.sacar(50));
    }

    @Test
    void depositar() {
        assertDoesNotThrow(() -> contaCorrente.depositar(50));
        assertEquals(150, contaCorrente.getSaldo());
        assertDoesNotThrow(() -> contaCorrente.depositar(50));
        assertEquals(200, contaCorrente.getSaldo());
        assertThrows(IllegalArgumentException.class, () -> contaCorrente.depositar(-50));
    }

    @Test
    void transferir() {
        ContaPoupanca contaPoupanca = new ContaPoupanca(cliente);
        contaPoupanca.depositar(100);
        assertDoesNotThrow(() -> contaCorrente.transferir(50, contaPoupanca));
        assertEquals(50, contaCorrente.getSaldo());
        assertEquals(150, contaPoupanca.getSaldo());
        assertDoesNotThrow(() -> contaCorrente.transferir(150, contaPoupanca));
        assertEquals(-100, contaCorrente.getSaldo());
        assertEquals(300, contaPoupanca.getSaldo());
        assertThrows(IllegalArgumentException.class, () -> contaCorrente.transferir(-50, contaPoupanca));
        assertThrows(SaldoInsuficienteException.class, () -> contaCorrente.transferir(100, contaPoupanca));
    }
}
