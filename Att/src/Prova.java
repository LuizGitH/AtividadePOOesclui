// Prova.java (Concrete Class)
import java.time.LocalDate;

/**
 * Classe concreta que representa uma prova, estendendo Atividade.
 * Provas não implementam Agendavel diretamente, mas possuem sua própria lógica de agendamento implícita pela data.
 */
public class Prova extends Atividade {
    private String materia;
    private String local;

    public Prova(String titulo, String descricao, LocalDate data, int prioridade, String materia, String local) throws DataInvalidaException, PrioridadeInvalidaException {
        super(titulo, descricao, data, prioridade);
        this.materia = materia;
        this.local = local;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--- Detalhes da Prova ---");
        System.out.println("Título: " + getTitulo());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Data: " + getData());
        System.out.println("Prioridade: " + getPrioridade());
        System.out.println("Matéria: " + materia);
        System.out.println("Local: " + local);
        System.out.println("-------------------------");
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}