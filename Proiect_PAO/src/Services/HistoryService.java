package Services;

import DatabaseManager.DatabaseManager;
import Repository.HistoryRepository;
import Models.History;

import java.util.List;
import java.util.Comparator;

import java.util.Set;
import java.util.TreeSet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryService {
    private final HistoryRepository historyRepository;
    private final AuditService auditService;

    public HistoryService() {
        this.auditService = AuditService.getInstance();
        this.historyRepository = HistoryRepository.getInstance();
    }

    public History findById(int userId, int levelId, DatabaseManager dbManager) {
        auditService.logAction("History find by id");
        return historyRepository.findById(userId, levelId, dbManager);
    }

    public List<History> findAll(DatabaseManager dbManager) {
        auditService.logAction("History find all");
        return historyRepository.findAll(dbManager);
    }

    public void create(int userId, int levelId, int score, int choiceId, DatabaseManager dbManager) {
        History newHistory = new History(userId, levelId, score, choiceId);
        historyRepository.create(newHistory, dbManager);
        auditService.logAction("History created");
        System.out.println(newHistory + " has been created");
    }

    public void update(int userId, int levelId, int score, int choiceId, DatabaseManager dbManager) {
        if (historyRepository.findById(userId, levelId, dbManager) == null) {
            System.out.println("History with user_id " + userId + " and level_id " + levelId + " does not exist");
            return;
        }
        History updatedHistory = new History(userId, levelId, score, choiceId);
        historyRepository.update(updatedHistory, dbManager);
        auditService.logAction("History updated");
        System.out.println("History has been updated to " + updatedHistory);
    }

    public void delete(int userId, int levelId, DatabaseManager dbManager) {
        History history = historyRepository.findById(userId, levelId, dbManager);
        if (history == null) {
            System.out.println("History with user_id " + userId + " and level_id " + levelId + " does not exist");
            return;
        }

        historyRepository.delete(userId, levelId, dbManager);
        auditService.logAction("History deleted");
        System.out.println(history + " has been deleted");
    }

    public void get_sorted(String type_of_sort, DatabaseManager dbManager, int id)
    {
        if (type_of_sort.equals("BasicSort_Code"))
        {
            Set<History> history_entries = new TreeSet<>(new Comparator<History>()
            {
                @Override
                public int compare(History his1, History his2)
                {
                   if(his1.getUserId() == his2.getUserId())
                   {
                       if(his1.getLevelId() == his2.getLevelId())
                       {
                           return -1*Integer.compare(his1.getScore(), his2.getScore());
                       }
                       return Integer.compare(his1.getLevelId(), his2.getLevelId());
                   }
                   return Integer.compare(his1.getUserId(), his2.getUserId());
                }
            });

            history_entries.addAll(historyRepository.findAll(dbManager));

            System.out.println("History entries sorted by users and their best runs: ");
            for (History his : history_entries) {
                System.out.println(his);
            }
        }
        else if (type_of_sort.equals("Leaderboard_SQL"))
        {
            String filterQuery = "select DISTINCT u.username, h.score from pao.history h join pao.users u on u.user_id = h.user_id where h.level_id = " + id +" ORDER BY h.score DESC; ";
            ResultSet resultSet = dbManager.executeQuery(filterQuery);
            if (resultSet != null) {
                try {
                    System.out.println("Leaderboard for the provided level: ");
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("username") + " has score " + resultSet.getInt("score") + ".");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Invalid filter");
        }

    }

    public void get_for_level(int id, DatabaseManager dbManager)
    {
        String filterQuery = "select * from pao.history where level_id = " + id + ";";
        ResultSet resultSet = dbManager.executeQuery(filterQuery);
        if (resultSet != null) {
            try {
                System.out.println("History entries for the provided level: ");
                while (resultSet.next()) {
                    System.out.println("User_id: " + resultSet.getString("user_id") + " with score " + resultSet.getInt("score") + " choice_id: " + resultSet.getInt("choice_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void get_for_user(int id, DatabaseManager dbManager)
    {
        String filterQuery = "select * from pao.history where user_id = " + id + ";";
        ResultSet resultSet = dbManager.executeQuery(filterQuery);
        if (resultSet != null) {
            try {
                System.out.println("History entries for the provided level: ");
                while (resultSet.next()) {
                    System.out.println("Level_id: " + resultSet.getString("level_id") + " with score " + resultSet.getInt("score") + " choice_id: " + resultSet.getInt("choice_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
