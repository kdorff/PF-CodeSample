package kdorff.cart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class to represent a Cart.
 */
public class Cart {
    /**
     * Map of item to desired quantity.
     */
    Map<InventoryItem, Integer> itemsToQuantity = new LinkedHashMap<>();

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
     * Get the cart total as a String with currency such as "GBP 1.70".
     *
     * @return the cart total as a String with currency.
     */
    @JsonProperty("total")
    public String getTotalAsString() {
        return formatAmount(total ,1);
    }

    /**
     * Get the cart total as a double.
     *
     * @return the cart total as a double.
     */
    @JsonIgnore
    public double getTotalAsDouble() {
        return total.getAmount().doubleValue();
    }

    /**
     * Get the contents of the cart, items and related quantities and prices
     * including discounts.
     * TODO: Instead of friendly, probably this should one JSON object per cart item / discount.
     *
     * @return the verbose list of cart items.
     */
    @JsonProperty("cart")
    public List<String> getCartVerbose() {
        List<String> result = new LinkedList<>();
        for (Map.Entry<InventoryItem, Integer> entry : itemsToQuantity.entrySet() ) {
            InventoryItem inventoryItem = entry.getKey();
            Integer count = entry.getValue();
            result.add(
                    inventoryItem.getName() + " ( " +
                    count + " @ " +
                            formatAmount(inventoryItem.getCost(), 1) + ") : " +
                            formatAmount(inventoryItem.getCost(), count));
        }
        return result;
    }

    /**
     * Format an amount including currency such as "GBP 1.50".
     *
     * @param cost the amount to format
     * @param multiplier a multiplier for the amount
     * @return String containing the currency and amount
     */
    private String formatAmount(Money cost, int multiplier) {
        return cost.getCurrencyUnit().toString()  + " " +
                String.format("%.02f", multiplier * cost.getAmount().doubleValue());
    }

    /**
     * Get the list of inventory items in the cart.
     *
     * @return list of inventory items in the cart.
     */
    @JsonIgnore
    public Set<InventoryItem> getInventoryItems() {
        return itemsToQuantity.keySet();
    }

    /**
     * Get the count of a specific item in the cart.
     *
     * @return the count of a specific item in the cart.
     */
    @JsonIgnore
    public Integer getQuantityInCart(InventoryItem inventoryItem) {
        return itemsToQuantity.get(inventoryItem);
    }
}
