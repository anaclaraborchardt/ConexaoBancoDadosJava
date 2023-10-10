import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {

    private Integer id;
    private String nome;
    private String senha;
    private Integer idade;
    private Carro carro;

    public Usuario(Integer id, String nome, String senha, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.idade = idade;
    }

    public Usuario(Integer id, String nome, String senha, Integer idade, Carro carro) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.idade = idade;
        this.carro = carro;
    }

    public Usuario(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt(1);
        this.nome = resultSet.getString(2);
        this.senha = resultSet.getString(3);
        this.idade = resultSet.getInt(4);
        int idCarro = resultSet.getInt(5);
        if(idCarro != 0){
            this.carro = new Carro(idCarro);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id= " + id +
                ", nome= '" + nome + '\'' +
                ", senha= '" + senha + '\'' +
                ", idade= " + idade + '\'' +
                ", carro= " + carro +
                '}';
    }
}
