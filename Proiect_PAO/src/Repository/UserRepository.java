package Repository;

import Models.User;
import DatabaseManager.DatabaseManager;
import java.sql.Timestamp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements RepositoryInterface<User> {

    private static UserRepository instance;

    private UserRepository() {

    }

    // Singleton pattern
    public static synchronized UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    @Override
    public User findById(int id, DatabaseManager dbManager) {
        String query = "SELECT * FROM pao.Users WHERE user_id = " + id;
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<User> findAll(DatabaseManager dbManager) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM pao.Users";
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    users.add(extractUserFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void create(User entity, DatabaseManager dbManager) {
        String query = "INSERT INTO pao.Users (username, password, email) VALUES ('"
                + entity.getUsername() + "', '"
                + entity.getPassword() + "', '"
                + entity.getEmail() + "')";
        dbManager.executeUpdate(query);
    }

    @Override
    public void update(User entity, DatabaseManager dbManager) {
        String query = "UPDATE pao.Users SET username = '" + entity.getUsername()
                + "', password = '" + entity.getPassword()
                + "', email = '" + entity.getEmail()
                + "', updated_at = CURRENT_TIMESTAMP WHERE user_id = " + entity.getUserId();
        dbManager.executeUpdate(query);
    }

    @Override
    public void delete(int id, DatabaseManager dbManager) {
        String query = "DELETE FROM pao.Users WHERE user_id = " + id;
        dbManager.executeUpdate(query);
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        Timestamp updatedAt = resultSet.getTimestamp("updated_at");
        return new User(userId, username, password, email, createdAt, updatedAt);
    }
}
