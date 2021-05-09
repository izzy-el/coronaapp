import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class Connect {
    private Connection connect() {
        File dbFile = new File("src/coronaApp.db");
        String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public void selectAllPacientes() throws SQLException {
        String selectAllPacientes = "SELECT * FROM PACIENTES;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectAllPacientes)) {

            while (rs.next()) {
                System.out.println(
                        "Nome: " +
                        rs.getString("NAME") + "\n" +
                        "Idade: " +
                        rs.getString("AGE") + "\n" +
                        "CPF: " +
                        rs.getString("CPF") + "\n" +
                        "Telefone: " +
                        rs.getString("PHONE") + "\n" +
                        "Estado: " +
                        rs.getString("STATE") + "\n" +
                        "Cidade: " +
                        rs.getString("CITY") + "\n" +
                        "CEP: " +
                        rs.getString("CEP") + "\n" +
                        "Numero da casa: " +
                        rs.getString("HOUSENUMBER") + "\n" +
                        "Sintomas: " +
                        rs.getString("SYMPTOMS") + "\n" +
                        "------------------------------------------------------------------------------------------------------------------------------------------------"
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectPacientesCity(String city) throws SQLException {
        String selectPacientesCity = "SELECT * FROM PACIENTES WHERE CITY = ?;";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(selectPacientesCity)) {

            pstmt.setString(1, city);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Nome: " +
                        rs.getString("NAME") + "\n" +
                        "Idade: " +
                        rs.getString("AGE") + "\n" +
                        "CPF: " +
                        rs.getString("CPF") + "\n" +
                        "Telefone: " +
                        rs.getString("PHONE") + "\n" +
                        "Estado: " +
                        rs.getString("STATE") + "\n" +
                        "Cidade: " +
                        rs.getString("CITY") + "\n" +
                        "CEP: " +
                        rs.getString("CEP") + "\n" +
                        "Numero da casa: " +
                        rs.getString("HOUSENUMBER") + "\n" +
                        "Sintomas: " +
                        rs.getString("SYMPTOMS") + "\n" +
                        "------------------------------------------------------------------------------------------------------------------------------------------------"
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void selectPacientesAgeGreater(String age) throws SQLException {
        String selectPacientesAgeGreater = "SELECT * FROM PACIENTES WHERE AGE >= ?;";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(selectPacientesAgeGreater)) {

             pstmt.setString(1, age);

             ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Nome: " +
                        rs.getString("NAME") + "\n" +
                        "Idade: " +
                        rs.getString("AGE") + "\n" +
                        "CPF: " +
                        rs.getString("CPF") + "\n" +
                        "Telefone: " +
                        rs.getString("PHONE") + "\n" +
                        "Estado: " +
                        rs.getString("STATE") + "\n" +
                        "Cidade: " +
                        rs.getString("CITY") + "\n" +
                        "CEP: " +
                        rs.getString("CEP") + "\n" +
                        "Numero da casa: " +
                        rs.getString("HOUSENUMBER") + "\n" +
                        "Sintomas: " +
                        rs.getString("SYMPTOMS") + "\n" +
                        "------------------------------------------------------------------------------------------------------------------------------------------------"
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectPacientesAgeLess(String age) throws SQLException {
        String selectPacientesAgeLess = "SELECT * FROM PACIENTES WHERE AGE <= ?;";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(selectPacientesAgeLess)) {

            pstmt.setString(1, age);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Nome: " +
                        rs.getString("NAME") + "\n" +
                        "Idade: " +
                        rs.getString("AGE") + "\n" +
                        "CPF: " +
                        rs.getString("CPF") + "\n" +
                        "Telefone: " +
                        rs.getString("PHONE") + "\n" +
                        "Estado: " +
                        rs.getString("STATE") + "\n" +
                        "Cidade: " +
                        rs.getString("CITY") + "\n" +
                        "CEP: " +
                        rs.getString("CEP") + "\n" +
                        "Numero da casa: " +
                        rs.getString("HOUSENUMBER") + "\n" +
                        "Sintomas: " +
                        rs.getString("SYMPTOMS") + "\n" +
                        "------------------------------------------------------------------------------------------------------------------------------------------------"
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectPacientesState(String state) throws SQLException {
        String selectPacientesState = "SELECT * FROM PACIENTES WHERE STATE = ?;";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(selectPacientesState)) {

            pstmt.setString(1, state);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Nome: " +
                        rs.getString("NAME") + "\n" +
                        "Idade: " +
                        rs.getString("AGE") + "\n" +
                        "CPF: " +
                        rs.getString("CPF") + "\n" +
                        "Telefone: " +
                        rs.getString("PHONE") + "\n" +
                        "Estado: " +
                        rs.getString("STATE") + "\n" +
                        "Cidade: " +
                        rs.getString("CITY") + "\n" +
                        "CEP: " +
                        rs.getString("CEP") + "\n" +
                        "Numero da casa: " +
                        rs.getString("HOUSENUMBER") + "\n" +
                        "Sintomas: " +
                        rs.getString("SYMPTOMS") + "\n" +
                        "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cadastrarPaciente(String username, String password, String name, String age, String cpf, String phone, String state, String city, String cep, String housenumber, String symptoms) throws SQLException {
        String insert = "INSERT INTO PACIENTES(USERNAME, PASSWORD, NAME, AGE, CPF, PHONE, STATE, CITY, CEP, HOUSENUMBER, SYMPTOMS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(insert)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, age);
            pstmt.setString(5, cpf);
            pstmt.setString(6, phone);
            pstmt.setString(7, state);
            pstmt.setString(8, city);
            pstmt.setString(9, cep);
            pstmt.setString(10, housenumber);
            pstmt.setString(11, symptoms);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cadastrarMedico(String name, String username, String password, String id, String especialidade, String cpf, String phone, String state, String city, String cep, String housenumber, String localTrabalho) throws SQLException {
        String insert = "INSERT INTO MEDICOS(NAME, USERNAME, PASSWORD, ID, ESPECIALIDADE, CPF, PHONE, STATE, CITY, CEP, HOUSENUMBER, LOCALTRABALHO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(insert)) {
            pstmt.setString(1, name);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, id);
            pstmt.setString(5, especialidade);
            pstmt.setString(6, cpf);
            pstmt.setString(7, phone);
            pstmt.setString(8, state);
            pstmt.setString(9, city);
            pstmt.setString(10, cep);
            pstmt.setString(11, housenumber);
            pstmt.setString(12, localTrabalho);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void registrarConsulta(String nome, String cpf, String data) throws SQLException {
        String insert = "INSERT INTO CONSULTAS(NOMEPACIENTE, CPF, DATA) VALUES (?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(insert)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, cpf);
            pstmt.setString(3, data);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------
    public void importarPacientes(ArrayList<String[]> pacientesCadastros) throws SQLException {
        String selectAllPacientes = "SELECT * FROM PACIENTES;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectAllPacientes)) {

                while (rs.next()) {
                    String username = rs.getString("USERNAME");
                    String password = rs.getString("PASSWORD");
                    String name = rs.getString("NAME");
                    String age = rs.getString("AGE");
                    String cpf = rs.getString("CPF");
                    String phone = rs.getString("PHONE");
                    String state = rs.getString("STATE");
                    String city = rs.getString("CITY");
                    String cep = rs.getString("CEP");
                    String houseNumber = rs.getString("HOUSENUMBER");
                    String symptoms = rs.getString("SYMPTOMS");

                    pacientesCadastros.add(new String[]{username, password, name, age, cpf, phone, state, city, cep, houseNumber, symptoms});

                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void importarMedicos(ArrayList<String[]> medicosCadastros) throws SQLException {
        String selectAllMedicos = "SELECT * FROM MEDICOS;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectAllMedicos)) {

            while (rs.next()) {
                String name = rs.getString("NAME");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String id = rs.getString("ID");
                String especialidade = rs.getString("ESPECIALIDADE");
                String cpf = rs.getString("CPF");
                String phone = rs.getString("PHONE");
                String state = rs.getString("STATE");
                String city = rs.getString("CITY");
                String cep = rs.getString("CEP");
                String houseNumber = rs.getString("HOUSENUMBER");
                String localTrabalho = rs.getString("LOCALTRABALHO");

                medicosCadastros.add(new String[]{name, username, password, id, especialidade, cpf, phone, state, city, cep, houseNumber, localTrabalho});

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void importarConsultas(ArrayList<String[]> consultas) throws SQLException{
        String consulta = "SELECT * FROM CONSULTAS;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {

            while (rs.next()) {
                String name = rs.getString("NOMEPACIENTE");
                String cpf = rs.getString("CPF");
                String data = rs.getString("DATA");

                consultas.add(new String[]{name, cpf, data});

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void importarDatas(ArrayList<String[]> datas) throws SQLException {
        String getData = "SELECT * FROM DATASLIVRES;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(getData)) {

            while (rs.next()) {
                String data = rs.getString("DATAS");
                String livre = rs.getString("LIVRE");

                datas.add(new String[]{data, livre});
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void enviarMensagem(String idMedico, String cpfAlvo, String data, String mensagem) throws SQLException {
        String insert = "INSERT INTO MENSAGENS(IDMEDICO, CPFALVO, DATA, TEXTO) VALUES (?, ?, ?, ?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(insert)) {
            pstmt.setString(1, idMedico);
            pstmt.setString(2, cpfAlvo);
            pstmt.setString(3, data);
            pstmt.setString(4, mensagem);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void importarMensagens(ArrayList<String[]> mensagens) throws SQLException {
        String getMensagens = "SELECT * FROM MENSAGENS;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(getMensagens)) {

            while (rs.next()) {
                String idMedico = rs.getString("IDMEDICO");
                String cpfAlvo = rs.getString("CPFALVO");
                String data = rs.getString("DATA");
                String text = rs.getString("TEXTO");

                mensagens.add(new String[]{idMedico, cpfAlvo, data, text});
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
