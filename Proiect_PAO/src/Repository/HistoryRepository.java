package Repository;

import Models.History;
import DatabaseManager.DatabaseManager;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryRepository implements RepositoryInterface<History> {

    private static HistoryRepository instance;

    private HistoryRepository() {

    }

    // Singleton pattern
    public static synchronized HistoryRepository getInstance() {
        if (instance == null) {
            instance = new HistoryRepository();
        }
        return instance;
    }

    @Override
    public History findById(int userId, DatabaseManager dbManager)
    {
        String query = "SELECT * FROM pao.History WHERE user_id = " + userId;
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    return extractHistoryFromResultSet(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public History findById(int userId, int levelId, DatabaseManager dbManager) {
        String query = "SELECT * FROM pao.History WHERE user_id = " + userId + " AND level_id = " + levelId;
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    return extractHistoryFromResultSet(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<History> findAll(DatabaseManager dbManager) {
        List<History> histories = new ArrayList<>();
        String query = "SELECT * FROM pao.History";
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    histories.add(extractHistoryFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return histories;
    }

    @Override
    public void create(History entity, DatabaseManager dbManager) {
        String query = "INSERT INTO pao.History (user_id, level_id, score, choice_id) VALUES ("
                + entity.getUserId() + ", "
                + entity.getLevelId() + ", "
                + entity.getScore() + ", "
                + entity.getChoiceId() + ")";
        dbManager.executeUpdate(query);
    }

    @Override
    public void update(History entity, DatabaseManager dbManager) {
        String query = "UPDATE pao.History SET score = " + entity.getScore()
                + ", choice_id = " + entity.getChoiceId()
                + " WHERE user_id = " + entity.getUserId()
                + " AND level_id = " + entity.getLevelId();
        dbManager.executeUpdate(query);
    }

    public void delete(int userId, int levelId, DatabaseManager dbManager) {
        String query = "DELETE FROM pao.History WHERE user_id = " + userId + " AND level_id = " + levelId;
        dbManager.executeUpdate(query);
    }

    @Override
    public void delete(int userId, DatabaseManager dbManager)
    {
        String query = "DELETE FROM pao.History WHERE user_id = " + userId;
        dbManager.executeUpdate(query);
    }

    private History extractHistoryFromResultSet(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_id");
        int levelId = resultSet.getInt("level_id");
        int score = resultSet.getInt("score");
        int choiceId = resultSet.getInt("choice_id");
        return new History(userId, levelId, score, choiceId);
    }
}
