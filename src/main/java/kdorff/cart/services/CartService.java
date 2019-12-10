package kdorff.cart.services;


import kdorff.cart.models.Cart;
import kdorff.cart.models.InventoryItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * Create a Cart from the list of items. If an item specified in itemNames is not in the
     * inventory, the request for that item is just ignored and not included in the total
     * or the cart counts.
     *
     * @param itemNames The list of items
     * @return the Cart for the list of items
     */
    public Cart createCartFromList(List<String> itemNames) {
        Cart cart = new Cart();
        if (itemNames != null) {
            for (String itemName : itemNames) {
                InventoryItem inventoryItem = inventoryService.findInInventory(itemName);
                if (inventoryItem != null) {
                    // An item we know about
                    cart.addToCart(inventoryItem);
                }
            }
        }

        return cart;
    }
}
