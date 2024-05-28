package Services;

import DatabaseManager.DatabaseManager;
import Repository.ChoiceRepository;
import Models.Choice;

import java.util.List;

public class ChoiceService {
    private final ChoiceRepository choiceRepository;
    private final AuditService auditService;

    public ChoiceService() {
        this.auditService = AuditService.getInstance();
        this.choiceRepository = ChoiceRepository.getInstance();
    }

    public Choice findById(int id, DatabaseManager dbManager) {
        auditService.logAction("Choice find by id");
        return choiceRepository.findById(id, dbManager);
    }

    public List<Choice> findAll(DatabaseManager dbManager) {
        auditService.logAction("Choice find all");
        return choiceRepository.findAll(dbManager);
    }

    public void create(String structure_type_1, String structure_type_2, String chosen_structure, DatabaseManager dbManager) {
        Choice newChoice = new Choice(0, structure_type_1, structure_type_2, chosen_structure);
        choiceRepository.create(newChoice, dbManager);
        auditService.logAction("Choice created");
        System.out.println(newChoice + " has been created");
    }

    public void update(int id, String structure_type_1, String structure_type_2, String chosen_structure, DatabaseManager dbManager) {
        if (choiceRepository.findById(id, dbManager) == null) {
            System.out.println("Choice with id " + id + " does not exist");
            return;
        }
        Choice updatedChoice = new Choice(id, structure_type_1, structure_type_2, chosen_structure);
        choiceRepository.update(updatedChoice, dbManager);
        auditService.logAction("Choice updated");
        System.out.println("Choice has been updated to " + updatedChoice);
    }

    public void delete(int id, DatabaseManager dbManager) {
        Choice choice = choiceRepository.findById(id, dbManager);
        if (choice == null) {
            System.out.println("Choice with id " + id + " does not exist");
            return;
        }

        choiceRepository.delete(id, dbManager);
        auditService.logAction("Choice deleted");
        System.out.println(choice + " has been deleted");
    }
}
