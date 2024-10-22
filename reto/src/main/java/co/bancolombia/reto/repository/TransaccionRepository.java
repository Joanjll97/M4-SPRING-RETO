package co.bancolombia.reto.repository;

import co.bancolombia.reto.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findTop5ByCuentaNumeroCuentaOrderByFechaDesc(String numeroCuenta);
}
