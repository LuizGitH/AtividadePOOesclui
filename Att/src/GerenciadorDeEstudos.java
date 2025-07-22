// GerenciadorDeEstudos.java (Main Application Class)
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe principal do sistema Gerenciador de Estudos.
 * Contém a lógica para gerenciar as atividades e demonstra o uso de polimorfismo e tratamento de exceções.
 */
public class GerenciadorDeEstudos {
    private List<Atividade> atividades;
    private Scanner scanner;

    public GerenciadorDeEstudos() {
        this.atividades = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        GerenciadorDeEstudos app = new GerenciadorDeEstudos();
        app.menuPrincipal();
    }

    public void menuPrincipal() {
        int opcao;
        do {
            System.out.println("\n--- Gerenciador de Estudos ---");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Adicionar Revisão");
            System.out.println("3. Adicionar Prova");
            System.out.println("4. Listar Atividades");
            System.out.println("5. Agendar Atividade (Tarefa/Revisão)");
            System.out.println("6. Cancelar Agendamento (Tarefa/Revisão)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                switch (opcao) {
                    case 1:
                        adicionarTarefa();
                        break;
                    case 2:
                        adicionarRevisao();
                        break;
                    case 3:
                        adicionarProva();
                        break;
                    case 4:
                        listarAtividades();
                        break;
                    case 5:
                        agendarAtividade();
                        break;
                    case 6:
                        cancelarAgendamento();
                        break;
                    case 0:
                        System.out.println("Saindo do Gerenciador de Estudos. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); // Limpar o buffer do scanner
                opcao = -1; // Para continuar no loop
            }
        } while (opcao != 0);
    }

    private void adicionarTarefa() {
        System.out.println("\n--- Adicionar Tarefa ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data (AAAA-MM-DD): ");
        String dataStr = scanner.nextLine();
        System.out.print("Prioridade (1-5): ");
        int prioridade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        try {
            LocalDate data = LocalDate.parse(dataStr);
            Tarefa novaTarefa = new Tarefa(titulo, descricao, data, prioridade);
            atividades.add(novaTarefa);
            System.out.println("Tarefa adicionada com sucesso!");
        } catch (DataInvalidaException | PrioridadeInvalidaException e) {
            System.err.println("Erro ao adicionar tarefa: " + e.getMessage());
        } catch (java.time.format.DateTimeParseException e) {
            System.err.println("Erro de formato de data. Use AAAA-MM-DD.");
        }
    }

    private void adicionarRevisao() {
        System.out.println("\n--- Adicionar Revisão ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data (AAAA-MM-DD): ");
        String dataStr = scanner.nextLine();
        System.out.print("Prioridade (1-5): ");
        int prioridade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Disciplina: ");
        String disciplina = scanner.nextLine();

        try {
            LocalDate data = LocalDate.parse(dataStr);
            Revisao novaRevisao = new Revisao(titulo, descricao, data, prioridade, disciplina);
            atividades.add(novaRevisao);
            System.out.println("Revisão adicionada com sucesso!");
        } catch (DataInvalidaException | PrioridadeInvalidaException e) {
            System.err.println("Erro ao adicionar revisão: " + e.getMessage());
        } catch (java.time.format.DateTimeParseException e) {
            System.err.println("Erro de formato de data. Use AAAA-MM-DD.");
        }
    }

    private void adicionarProva() {
        System.out.println("\n--- Adicionar Prova ---");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data (AAAA-MM-DD): ");
        String dataStr = scanner.nextLine();
        System.out.print("Prioridade (1-5): ");
        int prioridade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Matéria: ");
        String materia = scanner.nextLine();
        System.out.print("Local: ");
        String local = scanner.nextLine();

        try {
            LocalDate data = LocalDate.parse(dataStr);
            Prova novaProva = new Prova(titulo, descricao, data, prioridade, materia, local);
            atividades.add(novaProva);
            System.out.println("Prova adicionada com sucesso!");
        } catch (DataInvalidaException | PrioridadeInvalidaException e) {
            System.err.println("Erro ao adicionar prova: " + e.getMessage());
        } catch (java.time.format.DateTimeParseException e) {
            System.err.println("Erro de formato de data. Use AAAA-MM-DD.");
        }
    }

    private void listarAtividades() {
        if (atividades.isEmpty()) {
            System.out.println("\nNenhuma atividade cadastrada.");
            return;
        }
        System.out.println("\n--- Lista de Atividades ---");
        for (int i = 0; i < atividades.size(); i++) {
            System.out.println((i + 1) + ". " + atividades.get(i).getTitulo() + " (Tipo: " + atividades.get(i).getClass().getSimpleName() + ")");
            atividades.get(i).exibirDetalhes(); // Polimorfismo em ação
        }
    }

    private void agendarAtividade() {
        System.out.println("\n--- Agendar Atividade ---");
        if (atividades.isEmpty()) {
            System.out.println("Nenhuma atividade cadastrada para agendar.");
            return;
        }

        System.out.println("Atividades disponíveis para agendamento (Tarefas e Revisões):");
        List<Agendavel> agendaveis = new ArrayList<>();
        for (Atividade atividade : atividades) {
            if (atividade instanceof Agendavel) {
                agendaveis.add((Agendavel) atividade);
            }
        }

        if (agendaveis.isEmpty()) {
            System.out.println("Não há tarefas ou revisões para agendar.");
            return;
        }

        for (int i = 0; i < agendaveis.size(); i++) {
            Atividade a = (Atividade) agendaveis.get(i);
            System.out.println((i + 1) + ". " + a.getTitulo() + " (Tipo: " + a.getClass().getSimpleName() + ")");
        }

        System.out.print("Digite o número da atividade que deseja agendar: ");
        try {
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (escolha > 0 && escolha <= agendaveis.size()) {
                Agendavel atividadeSelecionada = agendaveis.get(escolha - 1);
                atividadeSelecionada.agendar();
            } else {
                System.out.println("Número de atividade inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }

    private void cancelarAgendamento() {
        System.out.println("\n--- Cancelar Agendamento ---");
        if (atividades.isEmpty()) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }

        System.out.println("Atividades agendadas (Tarefas e Revisões):");
        List<Agendavel> agendadas = new ArrayList<>();
        for (Atividade atividade : atividades) {
            if (atividade instanceof Agendavel && ((Agendavel) atividade).isAgendada()) {
                agendadas.add((Agendavel) atividade);
            }
        }

        if (agendadas.isEmpty()) {
            System.out.println("Não há tarefas ou revisões agendadas para cancelar.");
            return;
        }

        for (int i = 0; i < agendadas.size(); i++) {
            Atividade a = (Atividade) agendadas.get(i);
            System.out.println((i + 1) + ". " + a.getTitulo() + " (Tipo: " + a.getClass().getSimpleName() + ")");
        }

        System.out.print("Digite o número da atividade que deseja cancelar o agendamento: ");
        try {
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            if (escolha > 0 && escolha <= agendadas.size()) {
                Agendavel atividadeSelecionada = agendadas.get(escolha - 1);
                atividadeSelecionada.cancelarAgendamento();
            } else {
                System.out.println("Número de atividade inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.nextLine(); // Limpar o buffer do scanner
        }
    }
}