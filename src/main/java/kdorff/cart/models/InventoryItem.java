package kdorff.cart.models;

import org.joda.money.Money;

/**
 * Class for an inventory item. Findable using InventoryService.
 */
public class InventoryItem {
    /**
     * Item name.
     */
    private String name;

    /**
     * Item cost.
     */
    private Money cost;

    /**
     * Constructor.
     *
     * @param name name of inventory item.
     * @param cost
     */
    public InventoryItem(String name, Money cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * Get item name.
     *
     * @return item name
     */
    public String getName() {
        return name;
    }

    /**
     * Get item cost.
     *
     * @return item cost.
     */
    public Money getCost() {
        return cost;
    }

    /**
     * For toString(), use the name of the item. This improves the generated JSON output.
     *
     * @return toString for this class.
     */
    public String toString() {
        return name;
    }
}
