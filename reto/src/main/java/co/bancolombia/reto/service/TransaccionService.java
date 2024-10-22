package co.bancolombia.reto.service;

import co.bancolombia.reto.model.Transaccion;
import co.bancolombia.reto.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;

    @Autowired
    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public List<Transaccion> consultarHistorial(String numeroCuenta) {
        return transaccionRepository.findTop5ByCuentaNumeroCuentaOrderByFechaDesc(numeroCuenta);
    }
}