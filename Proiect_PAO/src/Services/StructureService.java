package Services;

import DatabaseManager.DatabaseManager;
import Repository.StructureRepository;
import Models.Structure;

import java.util.List;

public class StructureService {
    private final StructureRepository structureRepository;
    private final AuditService auditService;

    public StructureService() {
        this.auditService = AuditService.getInstance();
        this.structureRepository = StructureRepository.getInstance();
    }

    public Structure findById(int id, DatabaseManager dbManager) {
        auditService.logAction("Structure find by id");
        return structureRepository.findById(id, dbManager);
    }

    public List<Structure> findAll(DatabaseManager dbManager) {
        auditService.logAction("Structure find all");
        return structureRepository.findAll(dbManager);
    }

    public void create(String name, String description, int min_generation_value, int max_generation_value, int w, int h, DatabaseManager dbManager) {
        Structure newStructure = new Structure(0, name, description, min_generation_value, max_generation_value, w, h);
        structureRepository.create(newStructure, dbManager);
        auditService.logAction("Structure created");
        System.out.println(newStructure + " has been created");
    }

    public void update(int id, String name, String description, int min_generation_value, int max_generation_value, int w, int h, DatabaseManager dbManager) {
        if (structureRepository.findById(id, dbManager) == null) {
            System.out.println("Structure with id " + id + " does not exist");
            return;
        }
        Structure updatedStructure = new Structure(id, name, description, min_generation_value, max_generation_value, w, h);
        structureRepository.update(updatedStructure, dbManager);
        auditService.logAction("Structure updated");
        System.out.println("Structure has been updated to " + updatedStructure);
    }

    public void delete(int id, DatabaseManager dbManager) {
        Structure structure = structureRepository.findById(id, dbManager);
        if (structure == null) {
            System.out.println("Structure with id " + id + " does not exist");
            return;
        }

        structureRepository.delete(id, dbManager);
        auditService.logAction("Structure deleted");
        System.out.println(structure + " has been deleted");
    }
}
