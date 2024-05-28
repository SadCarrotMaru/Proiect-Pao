package Services;

import DatabaseManager.DatabaseManager;
import Repository.UserRepository;
import Models.User;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private final AuditService auditService;

    public UserService() {
        this.auditService = AuditService.getInstance();
        this.userRepository = UserRepository.getInstance();
    }

    public User findById(int id, DatabaseManager dbManager) {
        auditService.logAction("User find by id");
        return userRepository.findById(id, dbManager);
    }

    public List<User> findAll(DatabaseManager dbManager) {
        auditService.logAction("User find all");
        return userRepository.findAll(dbManager);
    }

    public void create(String username, String password, String email, DatabaseManager dbManager) {
        User newUser = new User(0, username, password, email, null, null);
        userRepository.create(newUser, dbManager);
        auditService.logAction("User created");
        System.out.println(newUser + " has been created");
    }

    public void update(int id, String username, String password, String email, DatabaseManager dbManager) {
        if (userRepository.findById(id, dbManager) == null) {
            System.out.println("User with id " + id + " does not exist");
            return;
        }
        User updatedUser = new User(id, username, password, email, null, null);
        userRepository.update(updatedUser, dbManager);
        auditService.logAction("User updated");
        System.out.println("User has been updated to " + updatedUser);
    }

    public void delete(int id, DatabaseManager dbManager) {
        User user = userRepository.findById(id, dbManager);
        if (user == null) {
            System.out.println("User with id " + id + " does not exist");
            return;
        }

        userRepository.delete(id, dbManager);
        auditService.logAction("User deleted");
        System.out.println(user + " has been deleted");
    }
}
