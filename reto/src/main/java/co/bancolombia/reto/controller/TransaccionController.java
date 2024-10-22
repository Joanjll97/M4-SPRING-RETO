package co.bancolombia.reto.controller;

import co.bancolombia.reto.model.Transaccion;
import co.bancolombia.reto.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    @Autowired
    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<List<Transaccion>> consultarHistorial(@PathVariable String numeroCuenta) {
        List<Transaccion> historial = transaccionService.consultarHistorial(numeroCuenta);
        return ResponseEntity.ok(historial);
    }
}