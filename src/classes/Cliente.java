package classes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {

    private Integer id;
    private String nome;
    private Integer idade;
    private Produto produto;

    public Cliente(Integer id, String nome, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;

    }
    public Cliente (Integer id, String nome, Integer idade, Produto produto) {
     this (id,nome,idade);
        this.produto = produto;
    }

    public Cliente(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.nome = resultSet.getString("nome");
        this.idade = resultSet.getInt("idade");
        int idProduto = resultSet.getInt("id_produto");
        if (idProduto!=0){
            this.produto = new Produto(idProduto);
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", produto=" + produto +
                '}';
    }
}
