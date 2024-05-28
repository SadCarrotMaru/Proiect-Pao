package Repository;

import Models.Structure;
import DatabaseManager.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StructureRepository implements RepositoryInterface<Structure> {

    private static StructureRepository instance;

    private StructureRepository() {

    }

    public static synchronized StructureRepository getInstance() {
        if (instance == null) {
            instance = new StructureRepository();
        }
        return instance;
    }

    @Override
    public Structure findById(int id, DatabaseManager dbManager) {
        String query = "SELECT * FROM pao.Structures WHERE structure_id = " + id;
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    return extractStructureFromResultSet(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Structure> findAll(DatabaseManager dbManager) {
        List<Structure> structures = new ArrayList<>();
        String query = "SELECT * FROM pao.Structures";
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    structures.add(extractStructureFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return structures;
    }

    @Override
    public void create(Structure entity, DatabaseManager dbManager) {
        String query = "INSERT INTO pao.Structures (name, description, min_generation_value, max_generation_value, w, h) VALUES ('"
                + entity.getName() + "', '"
                + entity.getDescription() + "', "
                + entity.getMin_generation_value() + ", "
                + entity.getMax_generation_value() + ", "
                + entity.getW() + ", "
                + entity.getH() + ")";
        dbManager.executeUpdate(query);
    }

    @Override
    public void update(Structure entity, DatabaseManager dbManager) {
        String query = "UPDATE pao.Structures SET name = '" + entity.getName()
                + "', description = '" + entity.getDescription()
                + "', min_generation_value = " + entity.getMin_generation_value()
                + ", max_generation_value = " + entity.getMax_generation_value()
                + ", w = " + entity.getW()
                + ", h = " + entity.getH()
                + " WHERE structure_id = " + entity.getStructure_id();
        dbManager.executeUpdate(query);
    }

    @Override
    public void delete(int id, DatabaseManager dbManager) {
        String query = "DELETE FROM pao.Structures WHERE structure_id = " + id;
        dbManager.executeUpdate(query);
    }

    private Structure extractStructureFromResultSet(ResultSet resultSet) throws SQLException {
        int structure_id = resultSet.getInt("structure_id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        int min_generation_value = resultSet.getInt("min_generation_value");
        int max_generation_value = resultSet.getInt("max_generation_value");
        int w = resultSet.getInt("w");
        int h = resultSet.getInt("h");
        return new Structure(structure_id, name, description, min_generation_value, max_generation_value, w, h);
    }
}
