package classesDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {

    private static final String URL_BANCO = "jdbc:mysql://localhost:3306/db_atividade";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL_BANCO,USERNAME,PASSWORD);
    }
}
