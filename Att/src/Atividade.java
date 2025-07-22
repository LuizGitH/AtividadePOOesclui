// Atividade.java (Abstract Class)
import java.time.LocalDate;

/**
 * Classe abstrata que representa uma atividade acadêmica genérica.
 * Contém atributos e métodos comuns a todas as atividades.
 */
public abstract class Atividade {
    private String titulo;
    private String descricao;
    private LocalDate data;
    private int prioridade; // 1-5, onde 1 é a mais alta

    public Atividade(String titulo, String descricao, LocalDate data, int prioridade) throws DataInvalidaException, PrioridadeInvalidaException {
        if (data == null || data.isBefore(LocalDate.now())) {
            throw new DataInvalidaException("A data da atividade não pode ser nula ou no passado.");
        }
        if (prioridade < 1 || prioridade > 5) {
            throw new PrioridadeInvalidaException("A prioridade deve ser entre 1 e 5.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.prioridade = prioridade;
    }

    // Métodos abstratos que devem ser implementados pelas subclasses
    public abstract void exibirDetalhes();

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public int getPrioridade() {
        return prioridade;
    }

    // Setters (com validação se necessário)
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(LocalDate data) throws DataInvalidaException {
        if (data == null || data.isBefore(LocalDate.now())) {
            throw new DataInvalidaException("A data da atividade não pode ser nula ou no passado.");
        }
        this.data = data;
    }

    public void setPrioridade(int prioridade) throws PrioridadeInvalidaException {
        if (prioridade < 1 || prioridade > 5) {
            throw new PrioridadeInvalidaException("A prioridade deve ser entre 1 e 5.");
        }
        this.prioridade = prioridade;
    }
}