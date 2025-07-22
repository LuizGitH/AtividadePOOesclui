// Agendavel.java (Interface)
/**
 * Interface que define métodos para atividades que podem ser agendadas.
 * Permite tratar diferentes tipos de atividades agendáveis de forma polimórfica.
 */
public interface Agendavel {
    void agendar();
    void cancelarAgendamento();
    boolean isAgendada();
}