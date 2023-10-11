package classesDAO;

import classes.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ProdutoDAO extends DAOPadrao <Produto, Integer>{

    public ProdutoDAO() throws SQLException {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void atualizar(Produto objeto) {
        comandoSQL = "UPDATE " +tabela +" SET nome = ? , preco = ? WHERE id =? ;";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setString(1, objeto.getNome());
            statement.setDouble(2, objeto.getPreco());
            statement.setInt(3, objeto.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }


}
