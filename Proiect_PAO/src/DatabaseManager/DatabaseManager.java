package DatabaseManager;

import java.sql.*;

public class DatabaseManager
{
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String username = "root";
    private static final String password = "";
    private static Connection connection;

    // Singleton pattern
    private static final DatabaseManager instance = new DatabaseManager();

    private DatabaseManager() {
        try
        {
            //JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    public ResultSet executeQuery(String query)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet executeUpdate(String query)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}