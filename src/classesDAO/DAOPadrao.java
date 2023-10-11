package classesDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class DAOPadrao<T,ID> implements ICRUD<T,ID>{

    protected Connection connection;
    protected String comandoSQL;
    private String tabela;
    public DAOPadrao( String tabela) throws SQLException {
        this.connection = Banco.conectar();
        this.tabela = tabela;
    }

    public Set<T> buscarTodos() {
         comandoSQL = "SELECT * FROM"+ tabela+ ";";
        try {
            PreparedStatement statement = connection.prepareStatement(comandoSQL);
            ResultSet resultSet = statement.executeQuery();
            Set<T> lista = new HashSet<>();
            //Ser como um forEach entre aspas
            while (resultSet.next()) {

                lista.add(converter(resultSet));
            }
            return lista;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
    public T buscarUm (Integer id) {
         comandoSQL = "SELECT * FROM "+tabela+" WHERE id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(comandoSQL)) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return converter(resultSet);
            }
            throw new NoSuchElementException();
        } catch (SQLException E) {
            throw new RuntimeException();
        }
    }

    public abstract T converter(ResultSet resultSet) throws SQLException;



    public void deletar( Integer id) {
         comandoSQL = "DELETE FROM "+tabela+" WHERE id = ? ;";

        try (PreparedStatement statement = connection.prepareStatement(comandoSQL)) {

            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

}
