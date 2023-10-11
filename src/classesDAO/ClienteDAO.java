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

        String comandoSQL = "INSERT INTO cliente VALUES(?,?,?,?);";
        try (PreparedStatement statement =
                     connection.prepareStatement(comandoSQL)) {
            statement.setInt(1, objeto.getId());
            statement.setString(2, objeto.getNome());
            statement.setInt(3,objeto.getIdade());
            try{
                statement.setInt(4, objeto.getProduto().getId());
            }catch (NullPointerException e){
                System.out.println("foi");
                statement.setNull(4,0);
            }
            statement.execute();
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }

    }

    @Override
    public void atualizar(Cliente objeto) {
        comandoSQL = "UPDATE " +tabela +" SET nome = ? , idade = ?, id_produto = ? WHERE id =? ;";
        try(PreparedStatement statement = connection.prepareStatement(comandoSQL)){
            statement.setString(1, objeto.getNome());
            statement.setDouble(2, objeto.getIdade());

            try{
                statement.setInt(3, objeto.getProduto().getId());
            }catch (NullPointerException e){
                statement.setNull(3,0);
            }
            statement.setInt(4,objeto.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
