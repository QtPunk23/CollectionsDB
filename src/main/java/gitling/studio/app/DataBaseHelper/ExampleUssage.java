package gitling.studio.app.DataBaseHelper;

import java.sql.Connection;
import java.sql.SQLException;

public class ExampleUssage {
    public static void main(String[] args) {

        Connection connection = null;

        try {

            connection = H2DataBaseConnector.getConnection();

            // Выполнение операций с базой данных

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            H2DataBaseConnector.closeConnection(connection);

        }

    }
}
