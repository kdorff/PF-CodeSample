package kdorff.cart.models;

import java.util.List;

/**
 * Class to assist with input of a JSON list in CartController to a List of String.
 * (Convert input from the rest endpoint.)
 */
public class ListOfInventoryNames {
    /**
     * The list of names.
     */
    private List<String> items;

    /**
     * Get the list of names.
     *
     * @return list of names
     */
    public List<String> getItems() {
        return items;
    }

    /**
     * Set the list of names.
     */
    public void setItems(List<String> items) {
        this.items = items;
    }
}
