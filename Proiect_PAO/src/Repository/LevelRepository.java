package Repository;

import Models.Level;
import DatabaseManager.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LevelRepository implements RepositoryInterface<Level> {

    private static LevelRepository instance;

    private LevelRepository() {

    }

    // Singleton pattern
    public static synchronized LevelRepository getInstance() {
        if (instance == null) {
            instance = new LevelRepository();
        }
        return instance;
    }

    @Override
    public Level findById(int id, DatabaseManager dbManager) {
        String query = "SELECT * FROM pao.Levels WHERE level_id = " + id;
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    return extractLevelFromResultSet(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Level> findAll(DatabaseManager dbManager) {
        List<Level> levels = new ArrayList<>();
        String query = "SELECT * FROM pao.Levels";
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    levels.add(extractLevelFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return levels;
    }

    @Override
    public void create(Level entity, DatabaseManager dbManager) {
        String query = "INSERT INTO pao.Levels (level_description) VALUES ('"
                + entity.getLevelDescription() + "')";
        dbManager.executeUpdate(query);
    }

    @Override
    public void update(Level entity, DatabaseManager dbManager) {
        String query = "UPDATE pao.Levels SET level_description = '" + entity.getLevelDescription()
                + "', special_key_identifier = '" + entity.getSpecialKeyIdentifier()
                + "' WHERE level_id = " + entity.getLevelId();
        dbManager.executeUpdate(query);
    }

    @Override
    public void delete(int id, DatabaseManager dbManager) {
        String query = "DELETE FROM pao.Levels WHERE level_id = " + id;
        dbManager.executeUpdate(query);
    }

    private Level extractLevelFromResultSet(ResultSet resultSet) throws SQLException
    {
        int levelId = resultSet.getInt("level_id");
        String levelDescription = resultSet.getString("level_description");
        String specialKeyIdentifier = resultSet.getString("special_key_identifier");
        return new Level(levelId, levelDescription, specialKeyIdentifier);
    }
}
