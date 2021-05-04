import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Medicos extends Pessoa {
    //Attributes
    private String id;
    private String especialidade;
    private String localTrabalho;

    //IMPLEMENT A METHOD TO GET PACIENT'S PHONE NUMBER
    Scanner sc = new Scanner(System.in);

    @Override
    public void cadastrar(ArrayList<String[]> cadastros) {
        System.out.print("Digite seu nome: ");
        super.name = sc.nextLine();

        System.out.print("Digite seu username: ");
        super.username = sc.nextLine();

        System.out.print("Digite sua senha: ");
        super.password = sc.nextLine();

        System.out.print("Digite seu ID: ");
        id = sc.nextLine();

        System.out.print("Digite sua espcialidade: ");
        especialidade = sc.nextLine();

        System.out.print("Digite seu CPF: ");
        super.cpf = sc.nextLine();

        System.out.print("Digite seu telefone: ");
        super.phone = sc.nextLine();

        System.out.print("Digite seu Estado: ");
        super.state = sc.nextLine();

        System.out.print("Digite sua cidade: ");
        super.city = sc.nextLine();

        System.out.print("Digite seu CEP: ");
        super.cep = sc.nextLine();

        System.out.print("Digite o numero de sua casa: ");
        super.houseNumber = sc.nextLine();

        System.out.print("Digite seu local de trabalho: ");
        localTrabalho = sc.nextLine();

        System.out.println("Cadastro do médico efetuado!");

        String[] info = {name, username, password, id, especialidade, cpf, phone, state, city, cep, houseNumber, localTrabalho};
        cadastros.add(info);
    }

    @Override
    public String[] login(ArrayList<String[]> cadastros) throws IOException {
        String allow = "0";
        boolean found = false;
        char tryAgain = 'n';

        do {
            System.out.print("Insira seu ID de Profissional da Saúde: ");
            String id = sc.nextLine();

            System.out.print("Insira seu username: ");
            String username = sc.nextLine();

            System.out.print("Insira sua password: ");
            String password = sc.nextLine();

            for (String[] cadastro : cadastros) {
                found = cadastro[3].equals(id) && cadastro[1].equals(username) && cadastro[2].equals(password);
            }
            if(found == false) {
                System.out.print("Usuário não encontrado! Deseja tentar novamente? [y/n] ");
                tryAgain = (char) System.in.read();
                sc.nextLine();
            }
            else {
                allow = "1";
            }
        } while(!found && tryAgain == 'y');

        return new String[]{allow, id, username, password, "medico"};
    }

    public void visualizarConsulta(ArrayList<String[]> consulta) {
        System.out.print("Digite o nome do paciente: ");
        String nome = sc.nextLine();

        System.out.print("Digite o CPF do paciente: ");
        String cpf = sc.nextLine();
        int control = 0;

        for(String[] consultas : consulta) {
            if(consultas[0].equals(nome) && consultas[1].equals(cpf)) {
                System.out.println(consultas[0] + " " + consultas[1] + " " + consultas[2]);
                break;
            }
        }
    }


    public String[] registrarConsulta(String id, ArrayList<String[]> cadastros, ArrayList<String> listData, ArrayList<String[]> consultas) {
        System.out.print("Digite o nome da pessoa que deseja cadastrar: ");
        String nomePaciente = sc.nextLine();
        System.out.print("Digite o CPF desse paciente: ");
        cpf = sc.nextLine();
        String data;
        int control = 0;

        for (String[] cadastro : cadastros) {
            if(cadastro[2].equals(nomePaciente) && cadastro[4].equals(cpf)) {
                control = 1;
                break;
            }
        }


        if(control == 0) {
            System.out.println("Paciente ainda não possui cadastro no sistema.");
            return new String[]{"ERRO"};
        }
        else {
            for (String listDate : listData) {
                System.out.println("Datas e horários disponíveis!");
                System.out.println(listDate);
            }

            System.out.print("Digite a data e o horário (dd/mm/yyyy - hh/mm) que deseja marcar essa consulta: ");
            data = sc.nextLine();
            control = 0;

            for (String listDate : listData) {
                if (data.equals(listDate)) {
                    control = 1;
                    System.out.println("A consulta foi agendada!");
//                    consultas.add(nomePaciente);
//                    consultas.add(cpf);
//                    consultas.add(data);
                    listData.remove(listDate);
                    break;
                }
            }

            if(control == 0) {
                System.out.println("Esta data já está reservada! Por favor, escolha outra.");
                return new String[]{"ERRO"};
            }
        }

        return new String[]{nomePaciente, cpf, data};
    }

    public void visualizarPacientes(ArrayList<String[]> cadastros) {
        cadastros.forEach(paciente -> {
            System.out.println(paciente[2] + " | " +  paciente[3] + " | " + paciente[4]);
            System.out.println("Telefone: " + paciente[5]);
            System.out.println(paciente[6] + " | " +  paciente[7] + " | " + paciente[9]);
            System.out.println("CEP: " + paciente[8]);
            System.out.println("Sintomas: " + paciente[10]);
            System.out.println("----------------------------------------------------------");
        });
    }

}
