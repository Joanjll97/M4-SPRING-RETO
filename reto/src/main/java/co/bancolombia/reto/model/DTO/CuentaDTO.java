package co.bancolombia.reto.model.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CuentaDTO {

    @NotEmpty(message = "Por favor ingresar el numero de la cuenta.")
    private String numeroCuenta;

    @NotNull(message = "Se debe ingresar un monto.")
    private BigDecimal monto;

    @NotNull(message = "Se debe ingresar un monto.")
    private String getTipoDeposito;

    // Getters y Setters


    public @NotEmpty(message = "Por favor ingresar el numero de la cuenta.") String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(@NotEmpty(message = "Por favor ingresar el numero de la cuenta.") String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public @NotNull(message = "Se debe ingresar un monto.") BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(@NotNull(message = "Se debe ingresar un monto.") BigDecimal monto) {
        this.monto = monto;
    }

    public @NotNull(message = "Se debe ingresar un monto.") String getTipoDeposito() {
        return getTipoDeposito;
    }

    public void setGetTipoDeposito(@NotNull(message = "Se debe ingresar un monto.") String getTipoDeposito) {
        this.getTipoDeposito = getTipoDeposito;
    }
}