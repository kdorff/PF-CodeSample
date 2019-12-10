package kdorff.cart;

import kdorff.cart.models.InventoryItem;
import kdorff.cart.models.Special;
import kdorff.cart.services.InventoryService;

import kdorff.cart.services.SpecialsService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * Application entry point class.
 */
@SpringBootApplication
public class Application {

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
     * Application entry point main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Initialize our simplistic Inventory and Specials services.
     */
    @PostConstruct
    private void init() {
        // Create inventory
        InventoryItem apple = new InventoryItem("Apple", Money.of(CurrencyUnit.GBP, 0.60d));
        inventoryService.addItemToInventory(apple);

        InventoryItem orange = new InventoryItem("Orange", Money.of(CurrencyUnit.GBP, 0.25d));
        inventoryService.addItemToInventory(orange);

        // Create specials
        Special appleSpecial = new Special(apple, 2, 1);
        specialsService.addSpecial(appleSpecial);

        Special orangeSpecial = new Special(orange, 3, 1);
        specialsService.addSpecial(orangeSpecial);
    }
}
