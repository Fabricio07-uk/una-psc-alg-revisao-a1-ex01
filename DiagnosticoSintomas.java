import java.util.Scanner;

public class DiagnosticoSintomas {

       public static String fazerPergunta(Scanner scanner, String pergunta) {
        for (int i = 0; i < 3; i++) {
            System.out.print(pergunta + " (SIM/NAO): ");
            String resposta = scanner.nextLine().trim();

            if (resposta.equalsIgnoreCase("SIM") || resposta.equalsIgnoreCase("NAO")) {
                return resposta.toUpperCase(); 
            } else {
                if (i < 2) { 
                    System.out.println("-> Resposta inválida. Por favor, digite SIM ou NAO. Você tem mais " + (2 - i) + " tentativa(s).");
                }
            }
        }
        return null; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Sistema de Triagem de Sintomas ---");
        
        System.out.print("Informe o seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Informe a sua idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("\nPor favor, responda às seguintes perguntas com 'SIM' ou 'NAO'.");

        
        String respostaVacina = fazerPergunta(scanner, "Seu cartão de vacina está em dia?");
        if (respostaVacina == null) {
            System.out.println("\nNão foi possível realizar o diagnóstico.\nGentileza procurar ajuda médica caso apareça algum sintoma.");
            scanner.close();
            return; 
        }

        String respostaSintomas = fazerPergunta(scanner, "Teve algum dos sintomas recentemente (dor de cabeça, febre, náusea, dor articular, gripe)?");
        if (respostaSintomas == null) {
            System.out.println("\nNão foi possível realizar o diagnóstico.\nGentileza procurar ajuda médica caso apareça algum sintoma.");
            scanner.close();
            return;
        }

        String respostaContato = fazerPergunta(scanner, "Teve contato com pessoas com sintomas gripais nos últimos dias?");
        if (respostaContato == null) {
            System.out.println("\nNão foi possível realizar o diagnóstico.\nGentileza procurar ajuda médica caso apareça algum sintoma.");
            scanner.close();
            return;
        }

        String respostaViagem = fazerPergunta(scanner, "Está retornando de viagem realizada no exterior?");
        if (respostaViagem == null) {
            System.out.println("\nNão foi possível realizar o diagnóstico.\nGentileza procurar ajuda médica caso apareça algum sintoma.");
            scanner.close();
            return;
        }

        int porcentagemRisco = 0;
        if (respostaVacina.equals("NAO")) {
            porcentagemRisco += 10;
        }
        if (respostaSintomas.equals("SIM")) {
            porcentagemRisco += 30;
        }
        if (respostaContato.equals("SIM")) {
            porcentagemRisco += 30;
        }
        if (respostaViagem.equals("SIM")) {
            porcentagemRisco += 30;
        }
        
        String orientacaoFinal;
        if (porcentagemRisco <= 30) {
            if (respostaViagem.equals("SIM")) {
                orientacaoFinal = "Você ficará sob observação por 05 dias.";
            } else {
                orientacaoFinal = "Paciente sob observação. Caso apareça algum sintoma, gentileza buscar assistência médica.";
            }
        } else if (porcentagemRisco <= 60) {
            orientacaoFinal = "Paciente com risco de estar infectado. Gentileza aguardar em lockdown por 02 dias para ser acompanhado.";
        } else if (porcentagemRisco < 90) {
            orientacaoFinal = "Paciente com alto risco de estar infectado. Gentileza aguardar em lockdown por 05 dias para ser acompanhado.";
        } else { 
            orientacaoFinal = "Paciente crítico! Gentileza aguardar em lockdown por 10 dias para ser acompanhado.";
        }
        
        System.out.println("\n-------------------------------------------");
        System.out.println("        RELATÓRIO FINAL DA TRIAGEM         ");
        System.out.println("-------------------------------------------");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("\n--- Respostas ---");
        System.out.println("Cartão de vacina em dia? " + respostaVacina);
        System.out.println("Teve sintomas recentemente? " + respostaSintomas);
        System.out.println("Teve contato com pessoas infectadas? " + respostaContato);
        System.out.println("Está retornando de viagem? " + respostaViagem);
        System.out.println("\n--- Diagnóstico ---");
        System.out.println("Probabilidade de infecção: " + porcentagemRisco + "%");
        System.out.println("Orientação Final: " + orientacaoFinal);
        System.out.println("-------------------------------------------");

        scanner.close();
    }
}