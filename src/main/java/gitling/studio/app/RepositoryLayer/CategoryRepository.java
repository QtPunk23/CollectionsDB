package gitling.studio.app.RepositoryLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private final Connection connection;

    public CategoryRepository(Connection connection) {
        this.connection = connection;
    }

    public void createCategory(String name) throws SQLException {
        String query = "INSERT INTO Category (Name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }

    public List<String> readCategories() throws SQLException {
        String query = "SELECT * FROM Category";
        List<String> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                long categoryId = resultSet.getLong("CategoryId");
                String name = resultSet.getString("Name");
                categories.add("CategoryId: " + categoryId + ", Name: " + name);
            }
        }
        return categories;
    }

    public void updateCategory(long categoryId, String newName) throws SQLException {
        String query = "UPDATE Category SET Name = ? WHERE CategoryId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newName);
            statement.setLong(2, categoryId);
            statement.executeUpdate();
        }
    }

    public void deleteCategory(long categoryId) throws SQLException {
        String query = "DELETE FROM Category WHERE CategoryId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, categoryId);
            statement.executeUpdate();
        }
    }
}
