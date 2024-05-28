package Services;

import DatabaseManager.DatabaseManager;
import Repository.LevelRepository;
import Models.Level;

import java.util.List;
import java.util.UUID;

public class LevelService {
    private final LevelRepository levelRepository;
    private final AuditService auditService;

    public LevelService() {
        this.auditService = AuditService.getInstance();
        this.levelRepository = LevelRepository.getInstance();
    }

    public Level findById(int id, DatabaseManager dbManager) {
        auditService.logAction("Level find by id");
        return levelRepository.findById(id, dbManager);
    }

    public List<Level> findAll(DatabaseManager dbManager) {
        auditService.logAction("Level find all");
        return levelRepository.findAll(dbManager);
    }

    public void create(String levelDescription, DatabaseManager dbManager) {
        String specialKeyIdentifier = UUID.randomUUID().toString();
        Level newLevel = new Level(0, levelDescription, specialKeyIdentifier);
        levelRepository.create(newLevel, dbManager);
        auditService.logAction("Level created");
        System.out.println(newLevel + " has been created");
    }

    public void update(int id, String levelDescription, String specialKeyIdentifier, DatabaseManager dbManager) {
        if (levelRepository.findById(id, dbManager) == null) {
            System.out.println("Level with id " + id + " does not exist");
            return;
        }
        Level updatedLevel = new Level(id, levelDescription, specialKeyIdentifier);
        levelRepository.update(updatedLevel, dbManager);
        auditService.logAction("Level updated");
        System.out.println("Level has been updated to " + updatedLevel);
    }

    public void delete(int id, DatabaseManager dbManager) {
        Level level = levelRepository.findById(id, dbManager);
        if (level == null) {
            System.out.println("Level with id " + id + " does not exist");
            return;
        }

        levelRepository.delete(id, dbManager);
        auditService.logAction("Level deleted");
        System.out.println(level + " has been deleted");
    }
}
