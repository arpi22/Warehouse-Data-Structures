import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserSession {
    private static final Scanner scanner = new Scanner(System.in);
    private final List<Storage> storages = new ArrayList<>();

    // Method to add a new storage
    public void addStorage() {
        storages.add(new Storage());
        System.out.println("Storage added.");
    }

    // Method to view the count of storages
    public void viewStorageCount() {
        System.out.println("Number of warehouses: " + storages.size());
    }

    // Method to view the contents of a specific storage
    public void viewStorageContents() {
        try {
            System.out.print("Enter storage number: ");
            String input = scanner.next();
            int storageNumber = Integer.parseInt(input);

            if (storageNumber < 1 || storageNumber > storages.size()) {
                System.out.println("Invalid storage number.");
                return;
            }

            Storage selectedWarehouse = storages.get(storageNumber - 1);
            selectedWarehouse.printStorageContents();
        } catch (NumberFormatException e) {
            System.out.println("You have entered incorrectly. Please enter a valid number.");
        }
    }

    // Method to move materials from one storage to another
    public void moveMaterials() {
        try {
            System.out.print("Enter source storage number: ");
            String sourceStorageNumberInput = scanner.next();
            int sourceStorageNumber = Integer.parseInt(sourceStorageNumberInput);

            if (sourceStorageNumber < 1 || sourceStorageNumber > storages.size()) {
                System.out.println("Invalid source warehouse number.");
                return;
            }

            System.out.print("Enter destination storage number: ");
            String destStorageNumberInput = scanner.next();
            int destStorageNumber = Integer.parseInt(destStorageNumberInput);

            if (destStorageNumber < 1 || destStorageNumber > storages.size()) {
                System.out.println("Invalid destination warehouse number.");
                return;
            }

            Storage sourceStorage = storages.get(sourceStorageNumber - 1);
            Storage destStorage = storages.get(destStorageNumber - 1);

            System.out.println("Available Materials in Source Warehouse:");
            sourceStorage.printStorageContents();

            System.out.print("Enter material to move: ");
            String materialName = scanner.next();
            Material material;
            try {
                material = Material.valueOf(materialName.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid material.");
                return;
            }

            System.out.print("Enter quantity to move: ");
            String quantityInput = scanner.next();
            int quantity = Integer.parseInt(quantityInput);

            int remainingCapacity = destStorage.getMaxCapacity(material) - destStorage.getTotalQuantity(material);
            if (quantity > remainingCapacity) {
                System.out.println("Not enough space in the destination storage. Maximum capacity for " + materialName + " is " + destStorage.getMaxCapacity(material) + ".");
                return;
            }

            sourceStorage.moveMaterials(destStorage, material, quantity);
            System.out.println(quantity + " units of " + materialName + " moved from storage " + sourceStorageNumber + " to storage " + destStorageNumber);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to delete a material from a storage
    public void deleteMaterial() {
        try {
            System.out.println("Available Storages:");
            for (int i = 0; i < storages.size(); i++) {
                System.out.println((i + 1) + ". Storage " + (i + 1));
            }

            System.out.print("Enter storage number to delete from: ");
            int storageNumber = scanner.nextInt();
            scanner.nextLine();

            if (storageNumber < 1 || storageNumber > storages.size()) {
                System.out.println("Invalid storage number.");
                return;
            }

            System.out.println("\nSelect material to delete:");
            System.out.println("1. Iron");
            System.out.println("2. Copper");
            System.out.print("Enter your choice: ");
            String materialChoiceInput = scanner.next();
            int materialChoice = Integer.parseInt(materialChoiceInput);

            Material material;
            switch (materialChoice) {
                case 1:
                    material = Material.IRON;
                    break;
                case 2:
                    material = Material.COPPER;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }

            System.out.print("Enter quantity to delete: ");
            String quantityInput = scanner.next();
            int quantity = Integer.parseInt(quantityInput);

            if (quantity <= 0) {
                System.out.println("Invalid quantity. Quantity must be a positive number.");
                return;
            }

            Storage storage = storages.get(storageNumber - 1);
            storage.removeMaterial(material, quantity);
            System.out.println(quantity + " units of " + material.getName() + " deleted from Storage " + storageNumber + ".");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    // Method to buy a material and add it to a storage
    public void buyMaterial() {
        try {
            System.out.println("Available Warehouses:");
            for (int i = 0; i < storages.size(); i++) {
                System.out.println((i + 1) + ". Warehouse " + (i + 1));
            }

            System.out.print("Enter warehouse number to buy from: ");
            int warehouseNumber = scanner.nextInt();
            scanner.nextLine();

            if (warehouseNumber < 1 || warehouseNumber > storages.size()) {
                System.out.println("Invalid warehouse number.");
                return;
            }

            System.out.println("Available Materials:");
            for (Material material : Material.values()) {
                System.out.println(material.ordinal() + 1 + ". " + material.getName());
            }

            System.out.print("Enter material number to buy: ");
            String materialNumberInput = scanner.next();
            int materialNumber = Integer.parseInt(materialNumberInput);

            if (materialNumber < 1 || materialNumber > Material.values().length) {
                System.out.println("Invalid material number.");
                return;
            }

            Material selectedMaterial = Material.values()[materialNumber - 1];

            System.out.print("Enter quantity to buy: ");
            String quantityInput = scanner.next();
            int quantity = Integer.parseInt(quantityInput);

            if (quantity <= 0) {
                System.out.println("Invalid quantity. Quantity must be a positive number.");
                return;
            }

            Storage storage = storages.get(warehouseNumber - 1);
            int remainingCapacity = selectedMaterial.getMaxCapacity() - storage.getMaterialQuantity(selectedMaterial);
            if (quantity > remainingCapacity) {
                System.out.println("Not enough capacity in the warehouse. Maximum capacity for " + selectedMaterial.getName() + " is " + selectedMaterial.getMaxCapacity() + ".");
                return;
            }

            storage.addMaterial(selectedMaterial, quantity);
            System.out.println(quantity + " units of " + selectedMaterial.getName() + " bought from Warehouse " + warehouseNumber + ".");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
