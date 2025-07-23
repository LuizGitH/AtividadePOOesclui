// Agendavel.java (Interface)
/**
 * Interface que define métodos para atividades que podem ser agendadas.
 * Permite tratar diferentes tipos de atividades agendáveis de forma polimórfica.
 */
public interface Interface {
    void agendar();
    void cancelarAgendamento();
    boolean isAgendada();
}