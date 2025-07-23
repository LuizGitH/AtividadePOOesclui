// Prova.java
class Prova extends Atividade implements Interface {
    private String disciplina;
    private double nota; // Initialized to 0 or -1

    public Prova(String titulo, String descricao, String data, int prioridade, String disciplina) throws TratamentoDataInvalida, TratamentoQntPrioridade {
        super(titulo, descricao, data, prioridade);
        this.disciplina = disciplina;
        this.nota = -1.0; // Indicates no grade yet
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--- Detalhes da Prova ---");
        System.out.println("Título: " + getTitulo());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Data: " + getData());
        System.out.println("Prioridade: " + getPrioridade());
        System.out.println("Disciplina: " + disciplina);
        System.out.println("Nota: " + (nota == -1.0 ? "Não lançada" : nota));
        System.out.println("Status: " + getStatus());
    }

    @Override
    public void agendar() {
        System.out.println("Prova de '" + disciplina + "' agendada para " + getData() + ".");
    }

    @Override
    public void cancelarAgendamento() {
        System.out.println("Agendamento da prova de '" + disciplina + "' cancelado.");
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        if (nota < 0 || nota > 10) { // Example grade range
            System.err.println("Erro: A nota deve ser entre 0 e 10.");
            return;
        }
        this.nota = nota;
    }
}