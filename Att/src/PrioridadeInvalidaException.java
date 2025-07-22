// PrioridadeInvalidaException.java (Custom Exception)
/**
 * Exceção personalizada para quando a prioridade de uma atividade é inválida.
 */
public class PrioridadeInvalidaException extends Exception {
    public PrioridadeInvalidaException(String message) {
        super(message);
    }
}