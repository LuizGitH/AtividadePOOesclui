// Revisao.java
class Revisao extends Atividade {
    private String topicosChave;

    public Revisao(String titulo, String descricao, String data, int prioridade, String topicosChave) throws TratamentoDataInvalida, TratamentoQntPrioridade {
        super(titulo, descricao, data, prioridade);
        this.topicosChave = topicosChave;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--- Detalhes da Revisão ---");
        System.out.println("Título: " + getTitulo());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Data: " + getData());
        System.out.println("Prioridade: " + getPrioridade());
        System.out.println("Tópicos Chave: " + topicosChave);
        System.out.println("Status: " + getStatus());
    }

    public String getTopicosChave() {
        return topicosChave;
    }

    public void setTopicosChave(String topicosChave) {
        this.topicosChave = topicosChave;
    }
}