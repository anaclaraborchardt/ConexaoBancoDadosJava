import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String urlBanco = "jdbc:mysql://localhost:3306/javaDB";
    private static final String usuarioBanco = "root";
    private static final String senhaUsuario = "root";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(urlBanco, usuarioBanco, senhaUsuario);
    }
}
