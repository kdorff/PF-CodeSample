package kdorff.cart.services;

import kdorff.cart.models.Special;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to provide a simplistic Specials service.
 *
 * TODO: This is a SIMPLISTIC Inventory. NOT Thread-safe.
 * TODO: This should be backed by a database or similar.
 *
 * NOTE: It is assumed only one Special per unique itemName.
 */
@Service
public class SpecialsService {

    /**
     * The inventory.
     */
    Map<String, Special> specials = new HashMap<>();

    /**
     * Add an special to the specials.
     * It is assumed only one Special per unique itemName.
     *
     * @param special the special to add to specials
     */
     public void addSpecial(Special special) {
         specials.put(special.getName(), special);
     }

    /**
     * Find the Special item by inventory item name. Return null if not found.
     * It is assumed only one Special per unique itemName.
     *
     * @param itemName item name to find a Special for
     * @return the Special or null of not found
     */
    public Special findSpecial(String itemName) {
        Special special = null;
        if (itemName != null) {
            special = specials.get(Special.PREFIX + itemName);
        }
        return special;
    }
}
