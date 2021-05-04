import java.io.*;
import java.util.*;

public class Mensagem {
    /* Atributos para possível uso futuro dos setters
    String remetente;
    String destinatario;
    String data;
    String texto;
    */

    Scanner sc = new Scanner(System.in);

    public void visualizarMensagens(ArrayList<String[]> mensagens,ArrayList<String[]> cadastros, String identificador, String tipo) throws IOException {
        if (tipo.equals("paciente")) {
            mensagens.forEach(mensagem -> {
                if (mensagem[1].equals(identificador)) {
                    System.out.println("Mensagem de " + mensagem[0]);
                    System.out.println("Data: " + mensagem[2] + "\n");
                    System.out.println(mensagem[3] + "\n");
                    System.out.println("----------------------------------------");
                }
            });
        }
        else{
            mensagens.forEach(mensagem -> {
                if (mensagem[0].equals(identificador)) {
                    System.out.println("Mensagem enviada para: " + mensagem[1]);
                    System.out.println("Data: " + mensagem[2] + "\n");
                    System.out.println(mensagem[3] + "\n");
                    System.out.println("----------------------------------------");
                }
            });

            System.out.println("Digite 'm' para enviar uma mensagem ou 'v' para voltar[m/v]: ");
            char optMensagem = (char) System.in.read();
            if (optMensagem == 'm') {
                this.enviarMensagem(mensagens, cadastros, identificador);
            }
            else {
                System.out.println("Voltando...");
            }
        }
    }

    public void enviarMensagem(ArrayList<String[]> mensagens,ArrayList<String[]> cadastros, String idMedico) throws IOException {
        char tryAgain = 'n';
        final boolean[] found = {false};
        String CPFAlvo;
        
        do {
            sc.nextLine();
            System.out.println("\n\nDigite o cpf do paciente que você deseja enviar a mensagem: ");
            CPFAlvo = sc.nextLine();

            String finalCPFAlvo = CPFAlvo;
            cadastros.forEach(cadastro -> {
                if (cadastro[4].equals(finalCPFAlvo)) {
                    found[0] = true;
                }
            });

            if (!found[0]) {
                System.out.print("Usuário não encontrado! Deseja tentar novamente? [y/n] ");
                tryAgain = (char) System.in.read();
                
            }

        } while(!found[0] && tryAgain == 'y');

        if (found[0]) {
            System.out.println("Digite a data de hoje:");
            String data = sc.nextLine();

            System.out.println("Digite abaixo a mensagem que deseja enviar: ");
            String texto = sc.nextLine();

            String[] mensagem = {idMedico, CPFAlvo, data, texto};
            mensagens.add(mensagem);
        }
    }
}

