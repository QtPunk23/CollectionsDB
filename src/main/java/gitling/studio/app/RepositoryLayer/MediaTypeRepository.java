package gitling.studio.app.RepositoryLayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaTypeRepository {
    private final Connection connection;

    public MediaTypeRepository(Connection connection) {
        this.connection = connection;
    }

    public void createMediaType(String name) throws SQLException {
        String query = "INSERT INTO MediaType (Name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }

    public List<String> readMediaTypes() throws SQLException {
        String query = "SELECT * FROM MediaType";
        List<String> mediaTypes = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                long mediaTypeId = resultSet.getLong("MediaTypeId");
                String name = resultSet.getString("Name");
                mediaTypes.add("MediaTypeId: " + mediaTypeId + ", Name: " + name);
            }
        }
        return mediaTypes;
    }

    public void updateMediaType(long mediaTypeId, String newName) throws SQLException {
        String query = "UPDATE MediaType SET Name = ? WHERE MediaTypeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newName);
            statement.setLong(2, mediaTypeId);
            statement.executeUpdate();
        }
    }

    public void deleteMediaType(long mediaTypeId) throws SQLException {
        String query = "DELETE FROM MediaType WHERE MediaTypeId = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, mediaTypeId);
            statement.executeUpdate();
        }
    }
}
