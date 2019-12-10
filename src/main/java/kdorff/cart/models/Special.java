package kdorff.cart.models;

/**
 * Class for a Special. Findable using CartService.
 */
public class Special extends InventoryItem {

    /**
     * Prefix on itemName for Specials, as a Special IS an InventoryItem.
     */
    public final static String PREFIX = "Free ";

    /**
     * How many must be bought...
     */
    private int buyThisMany;

    /**
     * How many will be given free (for every buyThisMany this is purchased).
     */
    private int thisManyFree;

    /**
     * Constructor.
     *
     * @param inventoryItem inventory item on special
     * @param buyThisMany the number that you must buy
     * @param thisManyFree number that will be received for free
     */
    public Special(InventoryItem inventoryItem, int buyThisMany, int thisManyFree) {
        super(Special.PREFIX + inventoryItem.getName(), inventoryItem.getCost().negated());
        this.buyThisMany = buyThisMany;
        this.thisManyFree = thisManyFree;
    }

    /**
     * How many must be bought
     *
     * @return how many must be bought
     */
    public int getBuyThisMany() {
        return buyThisMany;
    }

    /**
     * The number that will be received for free.
     *
     * @return number that will be received for free
     */
    public int getThisManyFree() {
        return thisManyFree;
    }
}


