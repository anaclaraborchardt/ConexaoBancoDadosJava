import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarroDAO extends PadraoDAO<Carro,Integer>{


    public CarroDAO(Connection connection) {
        super(connection, "carro", "idCarro");
    }

    @Override
    public void inserir(Carro obj) {
        comandoSQL = "INSERT INTO carro VALUES(?,?,?,?,?,?) ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(comandoSQL)){
            preparedStatement.setInt(1, obj.getId());
            preparedStatement.setString(2, obj.getMarca());
            preparedStatement.setString(3, obj.getModelo());
            preparedStatement.setDouble(4, obj.getPreco());
            preparedStatement.setString(5, obj.getCor());
            preparedStatement.setInt(6, obj.getAno());
            preparedStatement.execute();
        }catch(SQLException e){

        }
    }

    @Override
    public Carro converter(ResultSet resultSet) throws SQLException {
        return new Carro(resultSet);
    }

    @Override
    public void editar(Carro carro, Integer id) {

    }

    @Override
    public void close() throws Exception {

    }
}
