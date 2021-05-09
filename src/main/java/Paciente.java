import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Paciente extends Pessoa {
    //Attributes
    private String symptoms;

    Connect connect = new Connect();

    Scanner sc = new Scanner(System.in);

    @Override
    public void cadastrar(ArrayList<String[]> cadastros) throws SQLException {
        System.out.print("Digite seu username: ");
        super.username = sc.nextLine();

        System.out.print("Digite sua senha: ");
        super.password = sc.nextLine();

        System.out.print("Digite seu nome: ");
        super.name = sc.nextLine();

        System.out.print("Digite sua idade: ");
        super.age = sc.nextLine();

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

        System.out.println("Digite os seus sintomas: ");
        symptoms = sc.nextLine();

        connect.cadastrarPaciente(username, password, name, age, cpf, phone, state, city, cep, houseNumber, symptoms);

        System.out.println("Cadastro do paciente efetuado!");

        String[] info = {username, password, name, age, cpf, phone, state, city, cep, houseNumber, symptoms};
        cadastros.add(info);
    }

    @Override
    public String[] login(ArrayList<String[]> cadastros) throws IOException {
        String allow = "0";
        String cpf, username, password;
        AtomicBoolean found = new AtomicBoolean(false);
        char tryAgain = 'n';

        do {
            System.out.print("\nInsira seu CPF: ");
            cpf = sc.nextLine();

            System.out.print("Insira seu username: ");
            username = sc.nextLine();

            System.out.print("Insira sua password: ");
            password = sc.nextLine();

            for (String[] cadastro : cadastros) {
                if(cadastro[0].equals(username) && cadastro[1].equals(password) && cadastro[4].equals(cpf)) {
                    found.set(true);
                }
            }

            if(!found.get()) {
                System.out.print("Usuário não encontrado! Deseja tentar novamente? [y/n]");
                tryAgain = (char) System.in.read();
                sc.nextLine();
            }
            else {
                allow = "1";
            }

        } while(!found.get() && tryAgain == 'y');

        return new String[]{allow, cpf, username, password, "paciente"};
    }

    public void visualizarConsultaPaciente(ArrayList<String[]> consultas, String cpf) {
        int control = 0;

        for (String[] agendamento : consultas) {
            if (agendamento[1].equals(cpf)) {
                System.out.println(agendamento[0] + " - " + agendamento[1] + " - " + agendamento[2]);
                //control = 1;
                break;
            }
        }

    }
}
