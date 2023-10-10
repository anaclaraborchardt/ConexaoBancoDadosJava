import java.sql.*;

public class UsuarioDAO extends PadraoDAO<Usuario, Integer>{

    public UsuarioDAO(Connection connection) {
        super(connection, "usuarios", "idUsuario");
    }

    public void inserir(Usuario usuario){
        comandoSQL = "insert into usuarios " +
        "values(?,?,?,?,?);";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
        statement.setInt(1, usuario.getId());
        statement.setString(2, usuario.getNome());
        statement.setString(3, usuario.getSenha());
        statement.setInt(4,usuario.getIdade());
        try{
            statement.setInt(5, usuario.getCarro().getId());
        }catch(NullPointerException e){
            statement.setNull(5, 0);
        }
            statement.execute();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario converter(ResultSet resultSet) throws SQLException{
        return new Usuario(resultSet);
    }

        public void editar(Usuario usuario, Integer id){
            comandoSQL = "UPDATE usuarios SET nome = ?, senha = ?, idade = ?, fkCarro= ?" +
                    " where idUsuario = ?";
            try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
                statement.setString(1, usuario.getNome());
                statement.setString(2, usuario.getSenha());
                statement.setInt(3, usuario.getIdade());
                try {
                    statement.setInt(4, usuario.getCarro().getId());
                }catch(NullPointerException e) {
                    statement.setNull(4, 0);
                }
                    statement.setInt(5, id);
                    statement.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    public static ResultSet consultaTodosDados(Connection connection) {
        String comandoSQL = "SELECT * " +
                "FROM usuarios " +
                "LEFT JOIN carro " +
                "ON usuarios.fkCarro = carro.idCarro; ";

        try {
            PreparedStatement statement = connection.prepareStatement(comandoSQL);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
