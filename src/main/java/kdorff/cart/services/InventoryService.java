package kdorff.cart.services;


import kdorff.cart.models.InventoryItem;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to provide a simplistic inventory service.
 *
 * TODO: This is a SIMPLISTIC Inventory. NOT Thread-safe.
 * TODO: This should be backed by a database or similar.
 */
@Service
public class InventoryService {

    /**
     * The inventory.
     */
    Map<String, InventoryItem> inventory = new HashMap<>();

    /**
     * Add an item to the inventory.
     *
     * @param inventoryItem the item to add to inventory
     */
     public void addItemToInventory(InventoryItem inventoryItem) {
        inventory.put(inventoryItem.getName(), inventoryItem);
    }

    /**
     * Find the inventory item by name. Return null if not found.
     *
     * @param itemName the inventory item name
     * @return the InventoryItem or null of not found
     */
    public InventoryItem findInInventory(String itemName) {
        InventoryItem inventoryItem = null;
        if (itemName != null) {
            inventoryItem = inventory.get(itemName);
        }
        return inventoryItem;
    }
}
