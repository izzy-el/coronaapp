import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Conta {
    //Attributes
    protected String username;
    protected String password;

    public abstract void cadastrar(ArrayList<String[]> cadastros) throws SQLException;
    public abstract String[] login(ArrayList<String[]> cadastros) throws IOException;
}
