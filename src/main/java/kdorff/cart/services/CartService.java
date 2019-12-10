package kdorff.cart.services;


import kdorff.cart.models.Cart;
import kdorff.cart.models.InventoryItem;

import kdorff.cart.models.Special;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Service class dealing with the Cart.
 */
@Service
public class CartService {

    /**
     * InventoryService. Spring-bean.
     */
    @Autowired
    InventoryService inventoryService;

    /**
     * SpecialsService. Spring-bean.
     */
    @Autowired
    SpecialsService specialsService;

    /**
     * Create a Cart from the list of items. If an item specified in itemNames is not in the
     * inventory, the request for that item is just ignored and not included in the total
     * or the cart counts.
     * TODO: This code doesn't do anything with currency conversions.
     *
     * @param itemNames The list of items
     * @return the Cart for the list of items
     */
    public Cart createCartFromList(List<String> itemNames) {
        Cart cart = new Cart();
        populateCart(cart, itemNames);
        applySpecials(cart);
        return cart;
    }

    /**
     * Populate the cart.
     *
     * @param cart the cart to populate
     * @param itemNames the item names to populate the car with
     */
    private void populateCart(Cart cart, List<String> itemNames) {
        if (itemNames != null) {
            for (String itemName : itemNames) {
                InventoryItem inventoryItem = inventoryService.findInInventory(itemName);
                if (inventoryItem != null) {
                    // An item we know about
                    cart.addToCart(inventoryItem);
                }
            }
        }
    }

    /**
     * Apply the specials to the cart.
     *
     * @param cart the cart to add specials to
     */
    private void applySpecials(Cart cart) {
        List<InventoryItem> discounts = new LinkedList<>();

        // Collect all the Specials to apply
        for (InventoryItem cartItem : cart.getInventoryItems()) {
            Special special = specialsService.findSpecial(cartItem.getName());
            if (special != null) {
                int timesToApply =
                        ((int) cart.getQuantityInCart(cartItem) / special.getBuyThisMany()) *
                        special.getThisManyFree();
                for (int i = 0; i < timesToApply; i++) {
                    discounts.add(special);
                }
            }
        }

        // Add the Specials to the cart
        for (InventoryItem discount : discounts) {
            cart.addToCart(discount);
        }
    }
}