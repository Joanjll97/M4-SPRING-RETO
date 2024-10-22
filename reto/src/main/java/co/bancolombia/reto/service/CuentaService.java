package co.bancolombia.reto.service;

import co.bancolombia.reto.exception.CuentaNoEncontradaException;
import co.bancolombia.reto.exception.TransaccionNoPermitidaException;
import co.bancolombia.reto.model.Cuenta;
import co.bancolombia.reto.model.Transaccion;
import co.bancolombia.reto.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    @Autowired
    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public void depositar(String numeroCuenta, BigDecimal monto, String tipoDeposito) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta no encontrada"));

        switch (tipoDeposito) {
            case "sucursal":
                cuenta.depositarDesdeSucursal(monto);
                break;
            case "cajero":
                cuenta.depositarDesdeCajero(monto);
                break;
            case "otraCuenta":
                cuenta.depositarDesdeOtraCuenta(monto);
                break;
            default:
                throw new TransaccionNoPermitidaException("Tipo de depósito no permitido");
        }
        cuentaRepository.save(cuenta);
    }

    public void retirar(String numeroCuenta, BigDecimal monto) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta no encontrada"));
        cuenta.retirar(monto);
        cuentaRepository.save(cuenta);
    }

    public BigDecimal consultarSaldo(String numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta no encontrada"));
        return cuenta.getSaldo();
    }

    public List<Transaccion> consultarHistorial(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .map(Cuenta::getListaTransacciones)
                .orElseThrow(() -> new CuentaNoEncontradaException("Cuenta no encontrada"));
    }
}