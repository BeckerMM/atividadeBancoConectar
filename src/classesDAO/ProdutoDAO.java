package classesDAO;

import classes.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ProdutoDAO extends DAOPadrao <Produto, Integer>{

    public ProdutoDAO(String tabela) throws SQLException {
        super("produto");
    }

    @Override
    public Produto converter(ResultSet resultSet) throws SQLException {
        return new Produto(resultSet);
    }

    @Override
    public void inserir(Produto objeto) {
        comandoSQL = "INSERT INTO produto VALUES (?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(comandoSQL)) {
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setDouble(3, objeto.getPreco());
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void atualizar(Produto objeto) {

    }


}
