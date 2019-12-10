package kdorff.cart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent a Cart.
 */
public class Cart {
    /**
     * Map of item to desired quantity.
     */
    Map<InventoryItem, Integer> itemsToQuantity = new HashMap<>();

    /**
     * Total cost for items in cart.
     */
    private Money total = Money.of(CurrencyUnit.GBP, 0.0d);

    /**
     * Add an item to the cart. If the item is already in the cart, increase the quantity.
     *
     * @param inventoryItem the inventory item to add to the cart
     */
    public void addToCart(InventoryItem inventoryItem) {
        if (itemsToQuantity.containsKey(inventoryItem)) {
            itemsToQuantity.put(inventoryItem, itemsToQuantity.get(inventoryItem) + 1);
            total = total.plus(inventoryItem.getCost());
        }
        else {
            itemsToQuantity.put(inventoryItem, 1);
            total = total.plus(inventoryItem.getCost());
        }
    }

    /**
     * Get the cart total.
     *
     * @return the cart total.
     */
    @JsonIgnore
    public Money getTotal() {
        return total;
    }

    /**
     * Get the cart currency.
     *
     * @return the cart currency.
     */
    public String getCurrency() {
        return total.getCurrencyUnit().toString();
    }

    /**
     * Get the cart total as a double.
     *
     * @return the cart total as a double.
     */
    @JsonProperty("total")
    public double getTotalAsDouble() {
        return total.getAmount().doubleValue();
    }

    /**
     * Get the contents of the cart, items and related quantities.
     * This exists for testing and JSON output.
     *
     * @return the map representing the contents of the cart.
     */
    public Map<InventoryItem, Integer> getCart() {
        return itemsToQuantity;
    }
}
