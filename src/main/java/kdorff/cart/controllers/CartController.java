package kdorff.cart.controllers;

import kdorff.cart.models.Cart;
import kdorff.cart.models.ListOfInventoryNames;
import kdorff.cart.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for the sample code.
 */
@RestController
public class CartController {

    /**
     * CartService. Spring-bean.
     */
    @Autowired
    private CartService cartService;

    /**
     * Given the input (list of items) provide the total as JSON. Example output for an input of
     * ["Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange"] would be:
     *
     * {
     *     "cart": [
     *         "Apple ( 4 @ GBP 0.60) : GBP 2.40",
     *         "Orange ( 3 @ GBP 0.25) : GBP 0.75",
     *         "Free Apple ( 2 @ GBP -0.60) : GBP -1.20",
     *         "Free Orange ( 1 @ GBP -0.25) : GBP -0.25"
     *     ],
     *     "total": "GBP 1.70"
     * }
     *
     * @param items list of items to get a total for
     * @return JSON payload of the cart items and total.
     */
    @PostMapping(value = "/cart-total")
    public Cart cartTotal(@RequestBody ListOfInventoryNames items) {
        return cartService.createCartFromList(items.getItems());
    }
}
