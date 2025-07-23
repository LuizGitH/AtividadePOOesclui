// Tarefa.java
class Tarefa extends Atividade implements Interface {

    public Tarefa(String titulo, String descricao, String data, int prioridade) throws TratamentoDataInvalida,  TratamentoQntPrioridade{
        super(titulo, descricao, data, prioridade);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--- Detalhes da Tarefa ---");
        System.out.println("Título: " + getTitulo());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Data: " + getData());
        System.out.println("Prioridade: " + getPrioridade());
        System.out.println("Status: " + getStatus());
    }

    @Override
    public void agendar() {
        System.out.println("Tarefa '" + getTitulo() + "' agendada para " + getData() + ".");
    }

    @Override
    public void cancelarAgendamento() {
        System.out.println("Agendamento da tarefa '" + getTitulo() + "' cancelado.");
    }
}