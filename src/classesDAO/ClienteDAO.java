package classesDAO;

import classes.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ClienteDAO extends DAOPadrao<Cliente, Integer>{


    public ClienteDAO() throws SQLException {
        super("cliente");
    }

    @Override
    public Cliente converter(ResultSet resultSet) throws SQLException {
        return new Cliente(resultSet);
    }

    @Override
    public void inserir(Cliente objeto) {

        String comandoSQL = "   INSERT INTO cliente VALUES(?,?,?,?);";
        try (PreparedStatement statement =
                     connection.prepareStatement(comandoSQL)) {
            statement.setInt(1, objeto.getId());
            statement.setString(2, objeto.getNome());
            statement.setInt(3,objeto.getIdade());
            try{
                statement.setInt(4, objeto.getProduto().getId());
            }catch (NullPointerException e){
                statement.setNull(4,0);
            }
            statement.execute();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }

    }

    @Override
    public void atualizar(Cliente objeto) {

    }
}
