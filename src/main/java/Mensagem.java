import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensagem {

    String idMedico;

    Connect connect = new Connect();

    Scanner sc = new Scanner(System.in);

    public void visualizarMensagens(ArrayList<String[]> mensagens, ArrayList<String[]> cadastros, String identificador, String tipo) throws IOException, SQLException {
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
        else {
            mensagens.forEach(mensagem -> {
                if (mensagem[0].equals(identificador)) {
                    System.out.println("Mensagem enviada para: " + mensagem[1]);
                    System.out.println("Data: " + mensagem[2] + "\n");
                    System.out.println(mensagem[3] + "\n");
                    System.out.println("----------------------------------------");
                }
                else {
                    System.out.println(mensagem[1] + mensagem[2] + mensagem[3]);
                }
            });

            System.out.print("Digite 'm' para enviar uma mensagem ou 'v' para voltar[m/v]: ");
            char optMensagem = (char) System.in.read();
            if (optMensagem == 'm') {
                enviarMensagem(mensagens, cadastros, identificador);
            }
            else {
                System.out.println("Voltando...");
            }
        }
    }

    public void enviarMensagem(ArrayList<String[]> mensagens,ArrayList<String[]> cadastros, String idMedico) throws IOException, SQLException {
        char tryAgain = 'n';
        final boolean[] found = {false};
        String CPFAlvo;
        this.idMedico = idMedico;
        
        do {
            sc.nextLine();
            System.out.println("\n\nDigite o CPF do paciente que você deseja enviar a mensagem: ");
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
            LocalDateTime myDate = LocalDateTime.now();
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String formattedDate = myDate.format(formatDate);

            System.out.println("Digite abaixo a mensagem que deseja enviar: ");
            String texto = sc.nextLine();

            System.out.println(this.idMedico);
            connect.enviarMensagem(idMedico, CPFAlvo, formattedDate, texto);

            mensagens.add(new String[]{idMedico, CPFAlvo, formattedDate, texto});
        }
    }
}

