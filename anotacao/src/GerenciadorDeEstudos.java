// GerenciadorDeEstudos.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorDeEstudos {
    private static List<Atividade> atividades = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Adicionar algumas atividades de exemplo
        try {
            atividades.add(new Tarefa("Estudar POO", "Revisar conceitos de POO para a prova", "20/07/2025", 5));
            atividades.add(new Revisao("Revisão de Cálculo I", "Resolver exercícios da lista 3", "22/07/2025", 3, "Integrais"));
            atividades.add(new Prova("Prova de Estruturas de Dados", "Conteúdo: Árvores e Grafos", "25/07/2025", 4, "Estruturas de Dados"));
            atividades.add(new Tarefa("Apresentar Projeto", "Preparar slides", "28/07/2025", 2));
            atividades.add(new Prova("Prova de Física", "Leis de Newton", "23/07/2025", 5, "Física"));
        } catch (TratamentoDataInvalida | TratamentoQntPrioridade e) {
            System.err.println("Erro ao inicializar atividades: " + e.getMessage());
        }

        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    adicionarAtividade();
                    break;
                case 2:
                    listarAtividades();
                    break;
                case 3:
                    exibirDetalhesAtividade();
                    break;
                case 4:
                    atualizarAtividade();
                    break;
                case 5:
                    marcarTarefaComoConcluida(); // Opção antiga para tarefas, mantida para demonstração
                    break;
                case 6:
                    cancelarAgendamentoAtividade(); // Opção antiga para agendáveis, mantida para demonstração
                    break;
                case 7:
                    concluirAtividadeGenerica(); // Nova funcionalidade
                    break;
                case 8:
                    cancelarAtividadeGenerica(); // Nova funcionalidade
                    break;
                case 0:
                    System.out.println("Saindo do gerenciador. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("--- Gerenciador de Atividades de Estudos ---");
        System.out.println("1. Adicionar nova atividade");
        System.out.println("2. Listar todas as atividades");
        System.out.println("3. Exibir detalhes de uma atividade");
        System.out.println("4. Atualizar atividade");
        System.out.println("5. Marcar Tarefa como Concluída (específico para Tarefas)"); // Mantido, mas 7 é mais genérico
        System.out.println("6. Cancelar Agendamento (para Tarefas e Provas)"); // Mantido, mas 8 é mais genérico
        System.out.println("7. Concluir Atividade (Qualquer tipo)"); // Nova funcionalidade
        System.out.println("8. Cancelar Atividade (Qualquer tipo)"); // Nova funcionalidade
        System.out.println("0. Sair");
    }

    private static void adicionarAtividade() {
        System.out.println("--- Adicionar Nova Atividade ---");
        System.out.print("Tipo de atividade (Tarefa, Revisao, Prova): ");
        String tipo = scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = scanner.nextLine();
        System.out.print("Prioridade (1-5): ");
        int prioridade = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        try {
            switch (tipo.toLowerCase()) {
                case "tarefa":
                    atividades.add(new Tarefa(titulo, descricao, data, prioridade));
                    System.out.println("Tarefa adicionada com sucesso!");
                    break;
                case "revisao":
                    System.out.print("Tópicos Chave: ");
                    String topicosChave = scanner.nextLine();
                    atividades.add(new Revisao(titulo, descricao, data, prioridade, topicosChave));
                    System.out.println("Revisão adicionada com sucesso!");
                    break;
                case "prova":
                    System.out.print("Disciplina: ");
                    String disciplina = scanner.nextLine();
                    atividades.add(new Prova(titulo, descricao, data, prioridade, disciplina));
                    System.out.println("Prova adicionada com sucesso!");
                    break;
                default:
                    System.out.println("Tipo de atividade inválido.");
            }
        } catch (TratamentoDataInvalida | TratamentoQntPrioridade e) {
            System.err.println("Erro ao adicionar atividade: " + e.getMessage());
        }
    }

    private static void listarAtividades() {
        if (atividades.isEmpty()) {
            System.out.println("Nenhuma atividade cadastrada.");
            return;
        }
        System.out.println("--- Lista de Atividades ---");
        for (int i = 0; i < atividades.size(); i++) {
            Atividade atividade = atividades.get(i);
            System.out.println((i + 1) + ". " + atividade.getTitulo() + " (Status: " + atividade.getStatus() + ")");
        }
    }

    private static void exibirDetalhesAtividade() {
        System.out.print("Digite o número da atividade para ver detalhes: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice > 0 && indice <= atividades.size()) {
            atividades.get(indice - 1).exibirDetalhes();
        } else {
            System.out.println("Índice de atividade inválido.");
        }
    }

    private static void atualizarAtividade() {
        listarAtividades();
        if (atividades.isEmpty()) {
            return;
        }
        System.out.print("Digite o número da atividade que deseja atualizar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice > 0 && indice <= atividades.size()) {
            Atividade atividade = atividades.get(indice - 1);
            System.out.println("Atualizando: " + atividade.getTitulo());

            System.out.print("Novo título (deixe em branco para manter): ");
            String novoTitulo = scanner.nextLine();
            if (!novoTitulo.isEmpty()) {
                atividade.setTitulo(novoTitulo);
            }

            System.out.print("Nova descrição (deixe em branco para manter): ");
            String novaDescricao = scanner.nextLine();
            if (!novaDescricao.isEmpty()) {
                atividade.setDescricao(novaDescricao);
            }

            System.out.print("Nova data (DD/MM/AAAA - deixe em branco para manter): ");
            String novaData = scanner.nextLine();
            if (!novaData.isEmpty()) {
                try {
                    atividade.setData(novaData);
                } catch (TratamentoDataInvalida e) {
                    System.err.println("Erro ao atualizar data: " + e.getMessage());
                }
            }

            System.out.print("Nova prioridade (1-5, -1 para manter): ");
            int novaPrioridade = scanner.nextInt();
            scanner.nextLine();
            if (novaPrioridade != -1) {
                try {
                    atividade.setPrioridade(novaPrioridade);
                } catch (TratamentoQntPrioridade e) {
                    System.err.println("Erro ao atualizar prioridade: " + e.getMessage());
                }
            }

            // Opções específicas para cada tipo de atividade
            if (atividade instanceof Revisao) {
                Revisao revisao = (Revisao) atividade;
                System.out.print("Novos Tópicos Chave (deixe em branco para manter): ");
                String novosTopicos = scanner.nextLine();
                if (!novosTopicos.isEmpty()) {
                    revisao.setTopicosChave(novosTopicos);
                }
            } else if (atividade instanceof Prova) {
                Prova prova = (Prova) atividade;
                System.out.print("Nova Disciplina (deixe em branco para manter): ");
                String novaDisciplina = scanner.nextLine();
                if (!novaDisciplina.isEmpty()) {
                    prova.setDisciplina(novaDisciplina);
                }
                System.out.print("Nova Nota (-1 para não lançar, 0-10): ");
                double novaNota = scanner.nextDouble();
                scanner.nextLine();
                if (novaNota != -1) {
                    prova.setNota(novaNota);
                }
            }
            System.out.println("Atividade atualizada com sucesso!");
        } else {
            System.out.println("Índice de atividade inválido.");
        }
    }

    private static void marcarTarefaComoConcluida() {
        listarAtividades();
        if (atividades.isEmpty()) {
            return;
        }
        System.out.print("Digite o número da tarefa a ser marcada como concluída: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice > 0 && indice <= atividades.size()) {
            Atividade atividade = atividades.get(indice - 1);
            if (atividade instanceof Tarefa) {
                atividade.concluir(); // Usa o método genérico de concluir
            } else {
                System.out.println("A atividade selecionada não é uma Tarefa.");
            }
        } else {
            System.out.println("Índice de atividade inválido.");
        }
    }

    private static void cancelarAgendamentoAtividade() {
        listarAtividades();
        if (atividades.isEmpty()) {
            return;
        }
        System.out.print("Digite o número da atividade para cancelar o agendamento: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice > 0 && indice <= atividades.size()) {
            Atividade atividade = atividades.get(indice - 1);
            if (atividade instanceof Interface) {
                ((Interface) atividade).cancelarAgendamento();
            } else {
                System.out.println("A atividade selecionada não pode ser agendada/cancelada.");
            }
        } else {
            System.out.println("Índice de atividade inválido.");
        }
    }

    private static void concluirAtividadeGenerica() {
        listarAtividades();
        if (atividades.isEmpty()) {
            return;
        }
        System.out.print("Digite o número da atividade a ser marcada como concluída: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice > 0 && indice <= atividades.size()) {
            Atividade atividade = atividades.get(indice - 1);
            atividade.concluir();
        } else {
            System.out.println("Índice de atividade inválido.");
        }
    }

    private static void cancelarAtividadeGenerica() {
        listarAtividades();
        if (atividades.isEmpty()) {
            return;
        }
        System.out.print("Digite o número da atividade a ser marcada como cancelada: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice > 0 && indice <= atividades.size()) {
            Atividade atividade = atividades.get(indice - 1);
            atividade.cancelar();
        } else {
            System.out.println("Índice de atividade inválido.");
        }
    }
}