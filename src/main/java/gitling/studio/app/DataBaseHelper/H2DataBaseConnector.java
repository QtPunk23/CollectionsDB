package gitling.studio.app.DataBaseHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DataBaseConnector {
    private static final String JDBC_URL = "jdbc:h2:~/test";

    private static final String USER = "sa";

    private static final String PASSWORD = "";


    // Метод для получения соединения

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

    }


    // Метод для закрытия соединения

    public static void closeConnection(Connection connection) {

        if (connection != null) {

            try {

                connection.close();

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }
}
