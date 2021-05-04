import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("+++ Corona APP +++");
        System.out.println("Bem-vindo ao Corona App da OMS!");
        System.out.println("Você possui algum destes sintomas abaixo?");
        System.out.println("\t- Tosse");
        System.out.println("\t- Febre");
        System.out.println("\t- Cansaço extremo");
        System.out.println("\t- Falta de ar");
        System.out.println("\t- Perda de paladar ou olfato");
        System.out.println("\t- Dor de garganta\n\n");
        System.out.println("Caso a resposta seja 'sim' escolha uma das opções abaixo e digite seu número:");

        Paciente paciente = new Paciente();
        Medicos medico = new Medicos();
        String[] sessionPaciente;
        String[] sessionMedico;

        ArrayList<String[]> pacientesCadastros = new ArrayList<>();
        ArrayList<String[]> medicosCadastros = new ArrayList<>();
        ArrayList<String[]> consultas = new ArrayList<>();
        ArrayList<String> listData = new ArrayList<>();
        int opcaoEntrada;
        listData.add("1212");

        Scanner sc = new Scanner(System.in);

        //Menu
        do {
            System.out.println("1. Cadastro (Paciente)");
            System.out.println("2. Login (Paciente)\n");

            System.out.println("3. Cadastro (Equipe de Saúde)");
            System.out.println("4. Login (Equipe de Saúde)");
            System.out.println("5 - Sair");

            opcaoEntrada = sc.nextInt();
            switch (opcaoEntrada) {
                case 1:
                    paciente.cadastrar(pacientesCadastros);
                    break;

                case 2:
                    //Create an exit condition
                    int optPaciente;
                    boolean allowPaciente = false;

                    sessionPaciente = paciente.login(pacientesCadastros);

                    if(sessionPaciente[0].equals("1")) {
                        do {
                            System.out.print("Escolha uma opcao: ");
                            System.out.println("1 - Consultar agendamentos");
                            System.out.println("2 - Consultar mensagens");
                            System.out.println("3 - Sair");
                            optPaciente = sc.nextInt();

                            switch(optPaciente) {
                                case 1:
                                    paciente.visualizarConsulta(consultas, sessionPaciente[1]);
                                    break;

                                case 2:
                                    //Mensegem.consultarMensagens(String cpf, ArrayList<String[]> mensagens);
                                    break;

                                default:
                                    System.out.println("Opcao invalida. Tente novamento.");
                            }

                        } while (optPaciente != 3);
                    }

                    break;

                case 3:
                    medico.cadastrar(medicosCadastros);
                    break;

                case 4:
                    //Create an exit condition
                    int optMedico;
                    boolean allowMedico = false;

                    sessionMedico = medico.login(medicosCadastros);


                    if(sessionMedico[0].equals("1")) {
                        do {
                            System.out.println("Escolha uma opcao: ");
                            System.out.println("1 - Registar agendamentos");
                            System.out.println("2 - Consultar agendamentos");
                            System.out.println("3 - Sair");
                            optMedico = sc.nextInt();

                            switch(optMedico) {
                                case 1:
                                    String[] novaConsulta = medico.registrarConsulta(sessionMedico[1], pacientesCadastros, listData, consultas);
                                    consultas.add(novaConsulta);
                                    break;

                                case 2:
                                    medico.visualizarConsulta(consultas);
                                    break;

                                case 3:
                                    System.out.println("Voltando...");
                                    break;

                                default:
                                    System.out.println("Opcao invalida. Tente novamento.");
                            }

                        } while (optMedico != 3);
                    }

                    break;

                case 5:
                    System.out.println("Obrigado por usar nosso sistema!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida! Tente outra opção");
            }
        } while(opcaoEntrada != 5);

    }
}
