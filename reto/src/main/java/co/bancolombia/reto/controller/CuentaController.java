package co.bancolombia.reto.controller;

import co.bancolombia.reto.model.DTO.TransaccionDTO;
import co.bancolombia.reto.model.Transaccion;
import co.bancolombia.reto.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;


    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping("/{numeroCuenta}/depositar")
    public ResponseEntity<String> depositar(@PathVariable String numeroCuenta,
                                            @RequestBody TransaccionDTO TransaccionDTO) {
        cuentaService.depositar(numeroCuenta, TransaccionDTO.getMonto(), TransaccionDTO.getTipoDeposito());
        return ResponseEntity.ok("Depósito realizado exitosamente.");
    }

    @PostMapping("/{numeroCuenta}/retirar")
    public ResponseEntity<String> retirar(@PathVariable String numeroCuenta,
                                          @RequestBody @Valid TransaccionDTO transaccionDTO) {
        cuentaService.retirar(numeroCuenta, transaccionDTO.getMonto());
        return ResponseEntity.ok("Retiro realizado exitosamente.");
    }

    @GetMapping("/{numeroCuenta}/saldo")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable String numeroCuenta) {
        BigDecimal saldo = cuentaService.consultarSaldo(numeroCuenta);
        return ResponseEntity.ok(saldo);
    }

    @GetMapping("/{numeroCuenta}/historial")
    public ResponseEntity<List<Transaccion>> consultarHistorial(@PathVariable String numeroCuenta) {
        List<Transaccion> historial = cuentaService.consultarHistorial(numeroCuenta);
        return ResponseEntity.ok(historial);
    }
}