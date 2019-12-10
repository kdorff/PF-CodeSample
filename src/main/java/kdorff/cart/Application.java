package kdorff.cart;

import kdorff.cart.models.InventoryItem;
import kdorff.cart.services.InventoryService;

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
     * Application entry point main method.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Initialize our simplistic Inventory system.
     */
    @PostConstruct
    private void init() {
        inventoryService.addItemToInventory(
                new InventoryItem("Apple", Money.of(CurrencyUnit.GBP, 0.60d)));
        inventoryService.addItemToInventory(
                new InventoryItem("Orange", Money.of(CurrencyUnit.GBP, 0.25d)));
    }
}
