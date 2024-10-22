package co.bancolombia.reto.exception;

public class TransaccionNoPermitidaException extends RuntimeException {
  public TransaccionNoPermitidaException(String message) {
    super(message);
  }
}