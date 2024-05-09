import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, UserSession> userSessions = new HashMap<>();
    private static UserSession currentUserSession;
    public static void main(String[] args) {
        login();
        boolean exit = false;
        while (!exit) {
            displayMenu();
            String choiceInput = scanner.next();
            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            switch (choice) {
                case 1:
                    currentUserSession.addStorage();
                    break;
                case 2:
                    currentUserSession.viewStorageCount();
                    break;
                case 3:
                    currentUserSession.viewStorageContents();
                    break;
                case 4:
                    currentUserSession.deleteMaterial();
                    break;
                case 5:
                    currentUserSession.buyMaterial();
                    break;
                case 6:
                    currentUserSession.moveMaterials();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting the Sandship...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        UserSession session = userSessions.get(username);
        if (session == null) {
            session = new UserSession();
            session.addStorage();
            userSessions.put(username, session);
        }
        currentUserSession = session;
        System.out.println("Logged in as: " + username);
    }

    private static void displayMenu() {
        System.out.println("\nSandship Menu:");
        System.out.println("1. Add storage");
        System.out.println("2. Get storage count");
        System.out.println("3. View Storage contents");
        System.out.println("4. Delete material from storage");
        System.out.println("5. Buy material");
        System.out.println("6. Move materials between warehouses");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

}