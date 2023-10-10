import java.sql.ResultSet;
import java.sql.SQLException;

public class Carro {

    private Integer id;
    private String cor;
    private String modelo;
    private String marca;
    private double preco;
    private Integer ano;

    public Carro(Integer id, String cor, String modelo, String marca, double preco, Integer ano) {
        this.id = id;
        this.cor = cor;
        this.modelo = modelo;
        this.marca = marca;
        this.preco = preco;
        this.ano = ano;
    }

    public Carro(Integer idCarro) {
        this.id = idCarro;
    }

    public Carro(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt(1);
        this.cor = resultSet.getString(5);
        this.modelo = resultSet.getString(3);
        this.marca = resultSet.getString(2);
        this.preco = resultSet.getDouble(4);
        this.ano = resultSet.getInt(6);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", cor='" + cor + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", preco=" + preco +
                ", ano=" + ano +
                '}';
    }
}
