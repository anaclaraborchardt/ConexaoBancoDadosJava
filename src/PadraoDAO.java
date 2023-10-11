import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class PadraoDAO<T, ID> implements ICrud<T,ID>{

    protected Connection connection;
    protected String comandoSQL;
    private String tabela;
    private String idTabela;

    public PadraoDAO(Connection connection, String tabela, String idTabela) {
        this.connection = connection;
        this.tabela = tabela;
        this.idTabela = idTabela;
    }

    public Set<T> buscarTodos(){
        comandoSQL = "Select * from " + tabela + ";";

        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            ResultSet resultSet = statement.executeQuery();
            Set<T> lista = new HashSet<>();

            while(resultSet.next()){
                lista.add(converter(resultSet));
            }
            return lista;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public T buscarUm(Integer id) {
        comandoSQL = "SELECT *  FROM "+ tabela + " WHERE " + idTabela +" = ? ;";

        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return converter(resultSet);
            }
            throw new NoSuchElementException();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Integer id) {
        comandoSQL = "delete from " + tabela + "  where " + idTabela + " = ?;";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract T converter(ResultSet resultSet) throws SQLException;



}
