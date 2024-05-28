package Reader;

import DatabaseManager.DatabaseManager;
import Services.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import Models.*;

public class Reader
{
    ChoiceService choiceService;
    HistoryService historyService;
    LevelService levelService;
    StructureService structureService;
    UserService userService;
    DatabaseManager dbManager;
    Scanner scanner;
    SimpleDateFormat sdf;

    public Reader(DatabaseManager dbManager) {
        choiceService = new ChoiceService();
        historyService = new HistoryService();
        levelService = new LevelService();
        structureService = new StructureService();
        userService = new UserService();
        this.dbManager = dbManager;
        scanner = new Scanner(System.in);
        sdf = new SimpleDateFormat("dd-MMM-yy");
    }

    public void startReader() {
        System.out.println("Reader started , choose an action to perform (use listActions to see all actions)");
        while (true) {
            System.out.print("Enter action name: ");
            String action = scanner.nextLine();
            switch (action) {
                case "listActions":
                    listActions();
                    break;

                case "GetLeaderboard":
                    GetLeaderboard();
                    break;

                case "GetSortedHistoryEntries":
                    GetSortedHistoryEntries();
                    break;

                case "GetHistoryForUser":
                    GetHistoryForUser();
                    break;

                case "GetHistoryForLevel":
                    GetHistoryForLevel();
                    break;

                case "findById":
                    System.out.print("Enter the entity ( User, Choice, History, Level or Structure ): ");
                    String entity = scanner.nextLine();

                    System.out.print("Enter the id: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    int id2 = 0;
                    if (entity.equals("History"))
                        id2 = Integer.parseInt(scanner.nextLine());

                    findById(id, entity, id2);
                    break;
                case "findAll":
                    System.out.print("Enter the entity ( User, Choice, History, Level or Structure ): ");
                    String entity2 = scanner.nextLine();

                    findAll(entity2);
                    break;
                case "create":
                    System.out.print("Enter the entity ( User, Choice, History, Level or Structure ): ");
                    String entity3 = scanner.nextLine();

                    create(entity3);
                    break;
                case "update":
                    System.out.print("Enter the entity ( User, Choice, History, Level or Structure ): ");
                    String entity4 = scanner.nextLine();

                    update(entity4);
                    break;
                case "delete":
                    System.out.print("Enter the entity ( User, Choice, History, Level or Structure ): ");
                    String entity5 = scanner.nextLine();

                    delete(entity5);
                    break;
                case "exit":
                    return;



                default:
                    System.out.println("Invalid action");
                    break;
            }
        }
    }

    private void listActions()
    {
        System.out.println("Custom actions:");
        System.out.println("GetLeaderboard");
        System.out.println("GetSortedHistoryEntries");
        System.out.println("GetHistoryForUser");
        System.out.println("GetHistoryForLevel");

        System.out.println("Normal actions : findById , findAll , create , update , delete, followed by the entity name");
        System.out.println("Or type exit to exit the reader");
    }

    private void GetSortedHistoryEntries()
    {
        historyService.get_sorted("BasicSort_Code", dbManager, 0);
    }

    private void GetLeaderboard()
    {
        System.out.print("Enter the level id: ");
        int levelId = Integer.parseInt(scanner.nextLine());
        historyService.get_sorted("Leaderboard_SQL", dbManager, levelId);
    }

    private void GetHistoryForUser()
    {
        System.out.print("Enter the user id: ");
        int userId = Integer.parseInt(scanner.nextLine());
        historyService.get_for_user(userId,dbManager);
    }

    private void GetHistoryForLevel()
    {
        System.out.print("Enter the level id: ");
        int levelId = Integer.parseInt(scanner.nextLine());
        historyService.get_for_level(levelId,dbManager);
    }


////////////////////////////////////////////////////////////////////////////// CRUD ACTIONS ////////////////////////////////////////////////////

    private void findAll(String entity) {
        switch (entity) {
            case "User":
                System.out.println("All users: " + userService.findAll(dbManager));
                break;
            case "Choice":
                System.out.println("All choices: " + choiceService.findAll(dbManager));
                break;
            case "History":
                System.out.println("Full history: " + historyService.findAll(dbManager));
                break;
            case "Level":
                System.out.println("All levels: " + levelService.findAll(dbManager));
                break;
            case "Structure":
                System.out.println("All structures: " + structureService.findAll(dbManager));
                break;
            default:
                System.out.println("Invalid entity");
                break;
        }
    }

    private void findById(int id, String entity, int id2)
    {
        switch (entity) {
            case "User":
                User user = userService.findById(id, dbManager);
                if (user == null)
                    System.out.println("User with id " + id + " does not exist");
                else
                    System.out.println("User found: " + user);
                break;
            case "Choice":
                Choice choice = choiceService.findById(id, dbManager);
                if (choice == null)
                    System.out.println("Choice with id " + id + " does not exist");
                else
                    System.out.println("Choice found: " + choice);
                break;
            case "History":
                History history = historyService.findById(id, id2, dbManager);
                if (history == null)
                    System.out.println("History entry with id " + id + " does not exist");
                else
                    System.out.println("History entry found: " + history);
                break;
            case "Level":
                Level level = levelService.findById(id, dbManager);
                if (level == null)
                    System.out.println("Level with id " + id + " does not exist");
                else
                    System.out.println("Level found: " + level);
                break;
            case "Structure":
                Structure structure = structureService.findById(id, dbManager);
                if (structure == null)
                    System.out.println("Structure with id " + id + " does not exist");
                else
                    System.out.println("Structure found: " + structure);
                break;
            default:
                System.out.println("Invalid entity");
                break;
        }
    }

    private void create(String entity)
    {
        switch (entity)
        {
            case "User":
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                userService.create(username, password, email, dbManager);
                break;
            case "Level":
                System.out.print("Enter level description: ");
                String levelDescription = scanner.nextLine();
                levelService.create(levelDescription, dbManager);
                break;
            case "Choice":
                System.out.print("Enter the first structure: ");
                String first_structure = scanner.nextLine();
                System.out.print("Enter the second structure: ");
                String second_structure = scanner.nextLine();
                System.out.print("Enter the chosen structure: ");
                String chosen_structure = scanner.nextLine();
                choiceService.create(first_structure,second_structure,chosen_structure, dbManager);
                break;
            case "Structure":
                System.out.print("Enter the structure name: ");
                String structure_name = scanner.nextLine();
                System.out.print("Enter the structure description: ");
                String structure_description = scanner.nextLine();
                System.out.print("Enter the minimal generational value: ");
                int minimal_generational_value = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the maximum generational value: ");
                int maximal_generational_value = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the width of the structure: ");
                int w = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the height of the structure: ");
                int h = Integer.parseInt(scanner.nextLine());
                structureService.create(structure_name, structure_description, minimal_generational_value, maximal_generational_value, w, h, dbManager);
                break;
            case "History":
                System.out.print("Enter the user id: ");
                int u_id = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the level id: ");
                int l_id = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the score: ");
                int score = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the choice id:");
                int c_id = Integer.parseInt(scanner.nextLine());
                historyService.create(u_id, l_id, score, c_id, dbManager);
                break;
            default:
                System.out.println("Invalid entity");
                break;

        }
    }

    private void update(String entity) {
        switch (entity)
        {
            case "User":
                System.out.print("Enter user ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter new username: ");
                String username = scanner.nextLine();
                System.out.print("Enter new password: ");
                String password = scanner.nextLine();
                System.out.print("Enter new email: ");
                String email = scanner.nextLine();
                userService.update(id, username, password, email, dbManager);
                break;
            case "Level":
                System.out.print("Enter level ID: ");
                int id2 = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter new level description: ");
                String levelDescription = scanner.nextLine();
                System.out.print("Enter new special key identifier: ");
                String specialKeyIdentifier = scanner.nextLine();
                levelService.update(id2, levelDescription, specialKeyIdentifier, dbManager);
                break;
            case "Choice":
                System.out.print("Enter choice ID: ");
                int id3 = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter new first structure: ");
                String first_structure = scanner.nextLine();
                System.out.print("Enter new second structure: ");
                String second_structure = scanner.nextLine();
                System.out.print("Enter new chosen structure: ");
                String chosen_structure = scanner.nextLine();
                choiceService.update(id3, first_structure,second_structure,chosen_structure, dbManager);
                break;
            case "Structure":
                System.out.print("Enter structure ID: ");
                int id4 = scanner.nextInt();
                scanner.nextLine(); // consume newline
                System.out.print("Enter new structure name: ");
                String structure_name = scanner.nextLine();
                System.out.print("Enter new structure description: ");
                String structure_description = scanner.nextLine();
                System.out.print("Enter new minimal generational value: ");
                int minimal_generational_value = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter new maximum generational value: ");
                int maximal_generational_value = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter new width of the structure: ");
                int w = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter new height of the structure: ");
                int h = Integer.parseInt(scanner.nextLine());
                structureService.update(id4, structure_name, structure_description, minimal_generational_value, maximal_generational_value, w, h, dbManager);
                break;
            case "History":
                System.out.print("Enter the user_id: ");
                int id5 = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the level_id: ");
                int id6 = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the score: ");
                int score = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the choice id:");
                int c_id = Integer.parseInt(scanner.nextLine());
                historyService.update(id5, id6, score, c_id, dbManager);

            default:
                System.out.println("Invalid entity");
                break;
        }
    }

    private void delete(String entity) {
        switch (entity)
        {
            case "User":
                System.out.print("Enter user ID: ");
                int id = scanner.nextInt();
                userService.delete(id, dbManager);
                break;
            case "Level":
                System.out.print("Enter level ID: ");
                int id2 = scanner.nextInt();
                levelService.delete(id2, dbManager);
                break;
            case "Choice":
                System.out.print("Enter the id: ");
                int id3 = Integer.parseInt(scanner.nextLine());
                choiceService.delete(id3, dbManager);
                break;
            case "Structure":
                System.out.print("Enter the id: ");
                int id4 = Integer.parseInt(scanner.nextLine());
                structureService.delete(id4, dbManager);
                break;
            case "History":
                System.out.print("Enter the user_id: ");
                int id5 = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the level_id: ");
                int id6 = Integer.parseInt(scanner.nextLine());
                historyService.delete(id5, id6, dbManager);
                break;
            default:
                System.out.println("Invalid entity");
                break;
        }
    }
}