package gitling.studio.app;

import gitling.studio.app.ViewLayer.ConsoleHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://127.0.0.1:5432/postgres";
        String username = "postgres";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Подключение к базе данных выполнено успешно.");

            ConsoleHelper consoleHelper = new ConsoleHelper(connection);
            consoleHelper.start();

        } catch (SQLException e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }
}
