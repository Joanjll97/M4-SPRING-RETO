package co.bancolombia.reto.model.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class TransaccionDTO {

    @NotNull(message = "Se debe ingresar un monto.")
    private BigDecimal monto;

    @NotNull(message = "Se debe ingresar el tipo de transaccion.")
    private String TipoDeposito;



    public @NotNull(message = "Se debe ingresar un monto.") BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(@NotNull(message = "Se debe ingresar un monto.") BigDecimal monto) {
        this.monto = monto;
    }

    public @NotNull(message = "Se debe ingresar el tipo de transaccion.") String getTipoDeposito() {
        return TipoDeposito;
    }

    public void setTipoDeposito(@NotNull(message = "Se debe ingresar el tipo de transaccion.") String tipoDeposito) {
        TipoDeposito = tipoDeposito;
    }
}