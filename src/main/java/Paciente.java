import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Paciente extends Pessoa {
    //Attributes
    private String symptoms;

    Scanner sc = new Scanner(System.in);

    @Override
    public void cadastrar(ArrayList<String[]> cadastros) {
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

        System.out.println("Cadastro do paciente efetuado!");

        String[] info = {username, password, name, age, cpf, phone, state, city, cep, houseNumber, symptoms};
        cadastros.add(info);
    }

    @Override
    public String[] login(ArrayList<String[]> cadastros) throws IOException {
        String allow = "0";
        boolean found = false;
        char tryAgain = 'n';

        do {
            System.out.print("\nInsira seu CPF: ");
            String cpf = sc.nextLine();

            System.out.print("Insira seu username: ");
            String username = sc.nextLine();

            System.out.print("Insira sua password: ");
            String password = sc.nextLine();

            for (String[] cadastro : cadastros) {
                found = cadastro[0].equals(username) && cadastro[1].equals(password) && cadastro[4].equals(cpf);
            }

            if(!found) {
                System.out.print("Usuário não encontrado! Deseja tentar novamente? [y/n]");
                tryAgain = (char) System.in.read();
                sc.nextLine();
            }
            else {
                allow = "1";
            }

        } while(!found && tryAgain == 'y');

        return new String[]{allow, cpf, username, password, "paciente"};
    }

    public void visualizarConsulta(ArrayList<String[]> consulta, String cpf) {
        int control = 0;

        for(int i = 1; i < consulta.size(); i += 2) {
            if (cpf.equals(consulta.get(i))) {
                System.out.println(consulta.get(i) + " " + consulta.get(i - 1) + " " + consulta.get(i + 1));
            }
        }

    }
}
