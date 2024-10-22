package co.bancolombia.reto.exception;

public class CuentaNoEncontradaException extends RuntimeException {
  public CuentaNoEncontradaException(String message) {
    super(message);
  }
}