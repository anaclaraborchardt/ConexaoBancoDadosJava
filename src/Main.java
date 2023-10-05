import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args){
        //How to access DB
        //File - ProjectStructure - Libraries - + - ArquivosDeProgramas(x86) - MySQL - Conector J (zipado)

        //URL:
        //Padrão de início da url: jdbc:mysql://
        //Após colocar o servidor e a porta de onde ele vai estar
        //Qual banco de dados vou estabelecer a conexão - Database criada no Mysql

        String urlBanco = "jdbc:mysql://localhost:3306/javaDB";
        String usuarioBanco = "root";
        String senhaUsuario = "root";

        //Conexão utilizando o driver do mysql
        //O método getConnection implementa uma exceção checada
        //Try with resources - A connection é Autocloseable:
        //Sendo autocloseable, não preciso fechar a conexão
        try (Connection connection =
                     DriverManager.getConnection(urlBanco, usuarioBanco, senhaUsuario);){
//            inserir(connection, new Usuario(5,
//                    "ana",
//                    "ana'",
//                    17));
            //Preciso do toString
            System.out.println(buscarTodos(connection));
            System.out.println(buscarUmUsuario(1, connection));
            System.out.println(buscarUmUsuario("ana", connection));
            //Ao final da conexão, ela precisa ser finalizada:
            connection.close();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
            //o finally é executado dando exceção ou não
//        } finally {
//
        }
    }

    static void inserir(Connection connection, Usuario usuario){
        //statement também possui exceção checada
        try {
            //statement é o comando interpretado pelo mysql
            Statement statement = connection.createStatement();
            String comandoSQL = "insert into usuarios values("
                    + usuario.getId() + ", '" +
                    usuario.getNome() + "' , '" +
                    usuario.getSenha() + "' , " +
                    usuario.getIdade() +")";
            statement.execute(comandoSQL);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    static Set<Usuario> buscarTodos(Connection connection){
    String comandoSQL = "Select * from usuarios;";

    try{
        Statement statement = connection.createStatement();
        //O resultSet retorna um boolean
        //resultSet é iterável: ele sabe quem é o próximo e vai
        //percorrendo a lista do bd
        ResultSet resultSet = statement.executeQuery(comandoSQL);
        Set<Usuario> listaUsuario = new HashSet<>();

        while(resultSet.next()){
            //Aqui eu posso passar tanto o número da coluna, quanto o nome da mesma
            int id = resultSet.getInt(1);
            int idade = resultSet.getInt(4);
            String nome = resultSet.getString(2);
            String senha = resultSet.getString(3);
            Usuario usuario = new Usuario(id, nome, senha, idade);
            listaUsuario.add(usuario);
        }
        return listaUsuario;
    }catch (SQLException e){
        throw new RuntimeException(e);
    }
    }

    static Usuario buscarUmUsuario(Integer id, Connection connection){
        String comandoSQL = "Select * from usuarios where idUsuario = " + id;

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(comandoSQL);

            resultSet.next();
                int idade = resultSet.getInt(4);
                String nome = resultSet.getString(2);
                String senha = resultSet.getString(3);
                return new Usuario(id, nome, senha, idade);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static Usuario buscarUmUsuario(String nome, Connection connection){
        String comandoSQL = "Select * from usuarios where nome = " + "'" + nome + "'";

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(comandoSQL);

            resultSet.next();
                int id = resultSet.getInt(1);
                int idade = resultSet.getInt(4);
                String senha = resultSet.getString(3);
                return new Usuario(id, nome, senha, idade);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
