import java.io.IOException;
import java.util.ArrayList;

public abstract class Conta {
    //Attributes
    protected String username;
    protected String password;

    public abstract void cadastrar(ArrayList<String[]> cadastros);
    public abstract String[] login(ArrayList<String[]> cadastros) throws IOException;
}
