package Repository;

import Models.Choice;
import DatabaseManager.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChoiceRepository implements RepositoryInterface<Choice> {

    private static ChoiceRepository instance;

    private ChoiceRepository() {

    }

    public static synchronized ChoiceRepository getInstance() {
        if (instance == null) {
            instance = new ChoiceRepository();
        }
        return instance;
    }

    @Override
    public Choice findById(int id, DatabaseManager dbManager) {
        String query = "SELECT * FROM pao.Choices WHERE choice_id = " + id;
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    return extractChoiceFromResultSet(resultSet);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Choice> findAll(DatabaseManager dbManager) {
        List<Choice> choices = new ArrayList<>();
        String query = "SELECT * FROM pao.Choices";
        ResultSet resultSet = dbManager.executeQuery(query);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    choices.add(extractChoiceFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return choices;
    }

    @Override
    public void create(Choice entity, DatabaseManager dbManager) {
        String query = "INSERT INTO pao.Choices (structure_type_1, structure_type_2, chosen_structure) VALUES ('"
                + entity.getStructure_type_1() + "', '"
                + entity.getStructure_type_2() + "', '"
                + entity.getChosen_structure() + "')";
        dbManager.executeUpdate(query);
    }

    @Override
    public void update(Choice entity, DatabaseManager dbManager) {
        String query = "UPDATE pao.Choices SET structure_type_1 = '"
                + entity.getStructure_type_1()
                + "', structure_type_2 = '"
                + entity.getStructure_type_2()
                + "', chosen_structure = '"
                + entity.getChosen_structure()
                + "' WHERE choice_id = " + entity.getChoice_id();
        dbManager.executeUpdate(query);
    }

    @Override
    public void delete(int id, DatabaseManager dbManager) {
        String query = "DELETE FROM pao.Choices WHERE choice_id = " + id;
        dbManager.executeUpdate(query);
    }

    private Choice extractChoiceFromResultSet(ResultSet resultSet) throws SQLException {
        int choice_id = resultSet.getInt("choice_id");
        String structure_type_1 = resultSet.getString("structure_type_1");
        String structure_type_2 = resultSet.getString("structure_type_2");
        String chosen_structure = resultSet.getString("chosen_structure");
        return new Choice(choice_id, structure_type_1, structure_type_2, chosen_structure);
    }
}
