import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        try (Connection connection = Conexao.conectar()) {
            //NOVO USUÁRIO
            Usuario usuario = new Usuario(900, "ana", "ana'", 17,
                    new Carro(3, "Ford", "preto",
                            "ka", 70000, 2020));

            Usuario usuario2 = new Usuario(900, "ana", "ana'", 17,
                    new Carro(3, "Ford", "preto",
                            "ka", 70000, 2020));

            try (ICrud<Carro, Integer> crudCarro = new CarroDAO(connection);
                 ICrud<Usuario, Integer> crudUsuario = new UsuarioDAO(connection)) {
                try {
                    crudCarro.buscarUm(usuario.getCarro().getId());
                    System.out.println(crudUsuario.buscarTodos());
                    System.out.println(crudUsuario.buscarUm(900));
                    crudUsuario.editar(usuario2, 18);
                    crudUsuario.deletar(9);
                    System.out.println(crudUsuario.buscarTodos());
                } catch (NoSuchElementException exception) {
                    crudCarro.inserir(usuario.getCarro());
                }
                crudUsuario.inserir(usuario);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
