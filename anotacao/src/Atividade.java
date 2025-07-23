// Atividade.java
abstract class Atividade {
    protected String titulo;
    protected String descricao;
    protected String data; // Consider using java.time.LocalDate for better date handling
    protected int prioridade;
    protected StatusAtividade status;

    public Atividade(String titulo, String descricao, String data, int prioridade) throws TratamentoDataInvalida, TratamentoQntPrioridade {
        if (data == null || data.isEmpty()) { // Simple validation for illustration
            throw new TratamentoDataInvalida("A data da atividade não pode ser vazia.");
        }
        if (prioridade < 1 || prioridade > 5) { // Example priority range
            throw new TratamentoQntPrioridade("A prioridade deve ser entre 1 e 5.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.prioridade = prioridade;
        this.status = StatusAtividade.PENDENTE; // Atividade começa como pendente
    }

    public abstract void exibirDetalhes(); // Abstract method to be implemented by subclasses

    // Getters for common attributes
    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public StatusAtividade getStatus() {
        return status;
    }

    // Setters (optional, depending on requirements)
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(String data) throws TratamentoDataInvalida {
        if (data == null || data.isEmpty()) {
            throw new TratamentoDataInvalida("A data da atividade não pode ser vazia.");
        }
        this.data = data;
    }

    public void setPrioridade(int prioridade) throws TratamentoQntPrioridade {
        if (prioridade < 1 || prioridade > 5) {
            throw new TratamentoQntPrioridade("A prioridade deve ser entre 1 e 5.");
        }
        this.prioridade = prioridade;
    }

    // Métodos para mudar o status da atividade
    public void concluir() {
        if (this.status == StatusAtividade.PENDENTE) {
            this.status = StatusAtividade.CONCLUIDA;
            System.out.println("Atividade '" + getTitulo() + "' marcada como concluída.");
        } else {
            System.out.println("Atividade '" + getTitulo() + "' já está " + this.status.name().toLowerCase() + ".");
        }
    }

    public void cancelar() {
        if (this.status == StatusAtividade.PENDENTE) {
            this.status = StatusAtividade.CANCELADA;
            System.out.println("Atividade '" + getTitulo() + "' marcada como cancelada.");
        } else {
            System.out.println("Atividade '" + getTitulo() + "' já está " + this.status.name().toLowerCase() + " e não pode ser cancelada novamente.");
        }
    }
}