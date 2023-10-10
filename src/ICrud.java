import java.util.Set;

public interface ICrud <T, ID> extends AutoCloseable{

    void inserir(T obj);

    T buscarUm(ID id);

    Set<T> buscarTodos();

    void editar(T obj, Integer id);

    void deletar(ID id);

}
