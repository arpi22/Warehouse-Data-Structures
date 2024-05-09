import java.util.HashMap;
import java.util.Map;

public class Storage {
    private Map<Material, Integer> materials;

    public Storage() {
        materials = new HashMap<>();
    }

    // Add material to warehouse
    public void addMaterial(Material material, int quantity) {
        int currentQuantity = materials.getOrDefault(material, 0);
        materials.put(material, currentQuantity + quantity);
    }

    // Remove material from storage
    public void removeMaterial(Material material, int quantity) {
        int currentQuantity = materials.getOrDefault(material, 0);
        if (currentQuantity >= quantity) {
            materials.put(material, currentQuantity - quantity);
        } else {
            throw new IllegalArgumentException("Not enough " + material.getName() + " in the warehouse.");
        }
    }

    // Move materials from one warehouse to another
    public void moveMaterials(Storage destinationStorage, Material material, int quantity) {
        removeMaterial(material, quantity);
        destinationStorage.addMaterial(material, quantity);
    }

    // Get quantity of a specific material in the storage
    public int getMaterialQuantity(Material material) {
        return materials.getOrDefault(material, 0);
    }

    // Output current contents of the storage
    public void printStorageContents() {
        System.out.println("Storage Contents:");
        for (Map.Entry<Material, Integer> entry : materials.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }

    // Get the maximum capacity of the storage for save Material
    public int getMaxCapacity(Material material) {
        return material.getMaxCapacity();
    }

    // Get the total quantity of all materials in the storage
    public int getTotalQuantity(Material material) {
        if (materials.containsKey(material)) {
            return this.materials.get(material);
        } else{
            return 0;
        }
    }

}
