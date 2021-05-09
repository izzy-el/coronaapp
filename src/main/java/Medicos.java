import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Medicos extends Pessoa {
    //Attributes
    public String id;
    public String especialidade;
    public String localTrabalho;

    Connect connect = new Connect();

    Scanner sc = new Scanner(System.in);

    @Override
    public void cadastrar(ArrayList<String[]> cadastros) throws SQLException {
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

        connect.cadastrarMedico(name, username, password, id, especialidade, cpf, phone, state, city, cep, houseNumber, localTrabalho);

        String[] info = {name, username, password, id, especialidade, cpf, phone, state, city, cep, houseNumber, localTrabalho};
        cadastros.add(info);
    }

    @Override
    public String[] login(ArrayList<String[]> cadastros) throws IOException {
        String allow = "0";
        String id, username, password;
        AtomicBoolean found = new AtomicBoolean(false);
        char tryAgain = 'n';

        do {
            System.out.print("Insira seu ID de Profissional da Saúde: ");
            id = sc.nextLine();

            System.out.print("Insira seu username: ");
            username = sc.nextLine();

            System.out.print("Insira sua password: ");
            password = sc.nextLine();

            for (String[] cadastro : cadastros) {

                if(cadastro[3].equals(id) && cadastro[1].equals(username) && cadastro[2].equals(password)) {
                    found.set(true);
                }
            }

            if (!found.get()) {
                System.out.print("Usuário não encontrado! Deseja tentar novamente? [y/n] ");
                tryAgain = (char) System.in.read();
                sc.nextLine();
            } else {
                allow = "1";
            }
        } while (!found.get() && tryAgain == 'y');

        return new String[]{allow, id, username, password, "medico"};
    }

    public void visualizarConsulta(ArrayList<String[]> consulta) throws SQLException {
        System.out.print("Digite o nome do paciente: ");
        String nome = sc.nextLine();

        System.out.print("Digite o CPF do paciente: ");
        String cpf = sc.nextLine();
        int control = 0;

        for (String[] agendamento : consulta) {
            if (agendamento[0].equals(nome) && agendamento[1].equals(cpf)) {
                System.out.println(agendamento[0] + " - " + agendamento[1] + " - " + agendamento[2]);
                //control = 1;
                break;
            }
        }
    }


    public String[] registrarConsulta(String id, ArrayList<String[]> cadastros, ArrayList<String[]> listData, ArrayList<String[]> consultas) throws SQLException {
        System.out.print("Digite o nome da pessoa que deseja cadastrar: ");
        String nomePaciente = sc.nextLine();

        System.out.print("Digite o CPF desse paciente: ");
        cpf = sc.nextLine();
        String data;
        int control = 0;

        for (String[] cadastro : cadastros) {
            if (cadastro[2].equals(nomePaciente) && cadastro[4].equals(cpf)) {
                control = 1;
                break;
            }
        }

        if (control == 0) {
            System.out.println("Paciente ainda não possui cadastro no sistema.");
            return new String[]{"ERRO"};
        } else {
            for (String[] listDate : listData) {
                if(listDate[1].equals("y")) {
                    System.out.println("Datas e horários disponíveis!");
                    System.out.println(listDate[0]);
                }
            }

            System.out.print("Digite a data e o horário (dd/mm/yyyy - hh/mm) que deseja marcar essa consulta: ");
            data = sc.nextLine();
            control = 0;

            for (String[] listDate : listData) {
                if (data.equals(listDate[0])) {
                    control = 1;
                    System.out.println("A consulta foi agendada!");
                    listDate[1] = "n";
                    break;
                }
            }

            if (control == 0) {
                System.out.println("Esta data já está reservada! Por favor, escolha outra.");
                return new String[]{"ERRO"};
            }
        }

        connect.registrarConsulta(nomePaciente, cpf, data);

        return new String[]{nomePaciente, cpf, data};
    }

    public void visualizarPacientes(ArrayList<String[]> cadastros) throws IOException, SQLException {
        connect.selectAllPacientes();

        System.out.println("Digite 'f' para aplicar uma filtragem ou 'v' para voltar[f/v]: ");
        char optMensagem = (char) System.in.read();
        if (optMensagem == 'f') {
            this.filtrarPacientes(cadastros);
        } else {
            System.out.println("Voltando...");
        }
    }

    public void filtrarPacientes(ArrayList<String[]> cadastros) throws IOException, SQLException {
        char tryAgain = 'n';
        int filtro;

        do {
            sc.nextLine();
            System.out.println("\n\nDigite o filtro que você deseja aplicar na visualização: ");
            System.out.println("1 - Cidade");
            System.out.println("2 - Estado");
            System.out.println("3 - Idade");
            System.out.println("4 - Sintomas");

            filtro = sc.nextInt();

            if (filtro == 1) {
                sc.nextLine();
                System.out.println("Digite a cidade que deseja aplicar como filtro: ");
                String cidade = sc.nextLine();

                connect.selectPacientesCity(cidade);

            }

            if (filtro == 2) {
                sc.nextLine();
                System.out.println("Digite o estado que deseja aplicar como filtro: ");
                String estado = sc.nextLine();

                connect.selectPacientesState(estado);

            }

            if (filtro == 3) {
                sc.nextLine();
                System.out.println("Digite o sinal do filtro ('maior que' ou 'menor que') a ser aplicado: ");
                String sinal = sc.nextLine();

                sc.nextLine();
                System.out.println("Digite a idade que deseja aplicar como filtro: ");
                String idade = sc.nextLine();

                if (sinal.equals("maior que")) {
                    connect.selectPacientesAgeGreater(idade);

                } else {
                    connect.selectPacientesAgeLess(idade);

                }

            }

            if (filtro == 4) {
                sc.nextLine();
                System.out.println("Digite o sintoma que deseja aplicar como filtro: ");
                String sintomas = sc.nextLine().toLowerCase(Locale.ROOT);

                cadastros.forEach(paciente -> {
                    if (paciente[10].toLowerCase(Locale.ROOT).contains(sintomas)) {
                        System.out.println(paciente[2] + " | " + paciente[3] + " | " + paciente[4]);
                        System.out.println("Telefone: " + paciente[5]);
                        System.out.println(paciente[6] + " | " + paciente[7] + " | " + paciente[9]);
                        System.out.println("CEP: " + paciente[8]);
                        System.out.println("Sintomas: " + paciente[10]);
                        System.out.println("----------------------------------------------------------");
                    }
                });
            }

            System.out.println("Deseja aplicar outra filtragem?");
            tryAgain = (char) System.in.read();

        } while (tryAgain == 'y');
    }
}

