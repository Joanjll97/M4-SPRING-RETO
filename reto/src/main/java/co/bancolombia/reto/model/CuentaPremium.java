package co.bancolombia.reto.model;

import co.bancolombia.reto.exception.SaldoInsuficienteException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("PREMIUM")
public class CuentaPremium extends Cuenta {

    private static final BigDecimal COSTO_COMPRA_WEB = BigDecimal.valueOf(5);

    @Override
    public void depositarDesdeSucursal(BigDecimal monto) {
        setSaldo(getSaldo().add(monto));
        registrarTransaccion("depósito desde sucursal", monto);
    }

    @Override
    public void depositarDesdeCajero(BigDecimal monto) {
        setSaldo(getSaldo().add(monto));
        registrarTransaccion("depósito desde cajero", monto);
    }

    @Override
    public void depositarDesdeOtraCuenta(BigDecimal monto) {
        setSaldo(getSaldo().add(monto));
        registrarTransaccion("depósito desde otra cuenta", monto);
    }

    @Override
    public void comprarEnEstablecimiento(BigDecimal monto) {
        setSaldo(getSaldo().subtract(monto));
        registrarTransaccion("compra en establecimiento", monto);
    }

    @Override
    public void comprarEnWeb(BigDecimal monto) {
        setSaldo(getSaldo().subtract(monto).subtract(COSTO_COMPRA_WEB));
        registrarTransaccion("compra en web", monto);
    }

    @Override
    public void retirar(BigDecimal monto) {
        if (getSaldo().compareTo(monto) >= 0) {
            setSaldo(getSaldo().subtract(monto));
            registrarTransaccion("retiro", monto);
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro.");
        }
    }
}