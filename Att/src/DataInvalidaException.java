// DataInvalidaException.java (Custom Exception)
/**
 * Exceção personalizada para quando uma data é inválida (nula ou no passado).
 */
public class DataInvalidaException extends Exception {
    public DataInvalidaException(String message) {
        super(message);
    }
}