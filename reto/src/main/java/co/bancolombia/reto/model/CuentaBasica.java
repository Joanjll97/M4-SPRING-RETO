package co.bancolombia.reto.model;

import co.bancolombia.reto.exception.SaldoInsuficienteException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("BASICA")
public class CuentaBasica extends Cuenta {

    private static final BigDecimal COSTO_DEPOSITO_CAJERO = BigDecimal.valueOf(2);
    private static final BigDecimal COSTO_DEPOSITO_OTRA_CUENTA = BigDecimal.valueOf(1.5);
    private static final BigDecimal COSTO_RETIRO_CAJERO = BigDecimal.valueOf(1);
    private static final BigDecimal COSTO_COMPRA_WEB = BigDecimal.valueOf(5);

    @Override
    public void depositarDesdeSucursal(BigDecimal monto) {
        setSaldo(getSaldo().add(monto));
        registrarTransaccion("depósito desde sucursal", monto);
    }

    @Override
    public void depositarDesdeCajero(BigDecimal monto) {
        setSaldo(getSaldo().add(monto).subtract(COSTO_DEPOSITO_CAJERO));
        registrarTransaccion("depósito desde cajero", monto);
    }

    @Override
    public void depositarDesdeOtraCuenta(BigDecimal monto) {
        setSaldo(getSaldo().add(monto).subtract(COSTO_DEPOSITO_OTRA_CUENTA));
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
        BigDecimal total = monto.add(COSTO_RETIRO_CAJERO);
        if (getSaldo().compareTo(total) >= 0) {
            setSaldo(getSaldo().subtract(total));
            registrarTransaccion("retiro en cajero", monto);
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar el retiro.");
        }
    }
}