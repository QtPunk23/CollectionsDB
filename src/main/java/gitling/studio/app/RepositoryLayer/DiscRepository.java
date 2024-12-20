package gitling.studio.app.RepositoryLayer;



import gitling.studio.app.DataLayer.Disc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscRepository {
    private final Connection connection;

    public DiscRepository(Connection connection) {
        this.connection = connection;
    }

    public void createDisc(String title, long mediaTypeId, String description) throws SQLException {
        String query = "INSERT INTO Disc (Title, MediaTypeId, Description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setLong(2, mediaTypeId);
            statement.setString(3, description);
            statement.executeUpdate();
        }
    }

    public List<Disc> readDiscs() throws SQLException {
        String query = """
            SELECT 
                d.DiscId, 
                d.Title, 
                d.Description, 
                mt.Name AS MediaTypeName, 
                c.Name AS CategoryName
            FROM 
                Disc d
            JOIN 
                MediaType mt ON d.MediaTypeId = mt.MediaTypeId
            JOIN 
                Category c ON mt.CategoryId = c.CategoryId
        """;

        List<Disc> discs = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                long discId = resultSet.getLong("DiscId");
                String title = resultSet.getString("Title");
                String description = resultSet.getString("Description");
                String mediaTypeName = resultSet.getString("MediaTypeName");
                String categoryName = resultSet.getString("CategoryName");

                discs.add(new Disc(discId, title, description, mediaTypeName, categoryName));
            }
        }
        return discs;
    }

    public void updateDisc(long discId, String newTitle, long newMediaTypeId, String newDescription) throws SQLException {
        String query = "UPDATE Disc SET Title = ?, MediaTypeId = ?, Description = ? WHERE DiscId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newTitle);
            statement.setLong(2, newMediaTypeId);
            statement.setString(3, newDescription);
            statement.setLong(4, discId);
            statement.executeUpdate();
        }
    }

    public void deleteDisc(long discId) throws SQLException {
        String query = "DELETE FROM Disc WHERE DiscId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, discId);
            statement.executeUpdate();
        }
    }

    public Disc getDiscById(long discId) throws SQLException {
        String query = """
            SELECT 
                d.DiscId, 
                d.Title, 
                d.Description, 
                mt.Name AS MediaTypeName, 
                c.Name AS CategoryName
            FROM 
                Disc d
            JOIN 
                MediaType mt ON d.MediaTypeId = mt.MediaTypeId
            JOIN 
                Category c ON mt.CategoryId = c.CategoryId
            WHERE 
                d.DiscId = ?
        """;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, discId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String title = resultSet.getString("Title");
                    String description = resultSet.getString("Description");
                    String mediaTypeName = resultSet.getString("MediaTypeName");
                    String categoryName = resultSet.getString("CategoryName");

                    return new Disc(discId, title, description, mediaTypeName, categoryName);
                } else {
                    return null; // No disc found
                }
            }
        }
    }
}
