package kdorff.cart.services

import kdorff.cart.models.InventoryItem
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Tests for InventoryService.
 */
class InventoryServiceSpec extends Specification {

    @Unroll
    def "test add to/retrieve from inventory. Search for '#nameToFind' should return '#expItemName'"() {
        setup:
        InventoryService inventoryService = new InventoryService()
        InventoryItem apple = new InventoryItem('Apple', Money.of(CurrencyUnit.GBP, 0.60d))
        InventoryItem orange = new InventoryItem('Orange', Money.of(CurrencyUnit.GBP, 0.25d))
        InventoryItem unusedPear = new InventoryItem('Pear', Money.of(CurrencyUnit.GBP, 0.50d))

        inventoryService.addItemToInventory(apple)
        inventoryService.addItemToInventory(orange)
        // Add apple twice, it should just replace.
        inventoryService.addItemToInventory(apple)

        expect:
        inventoryService.inventory.size() == 2
        expItemName == inventoryService.findInInventory(nameToFind)?.getName()

        where:
        nameToFind || expItemName
        "Apple"    || "Apple"
        "Orange"   || "Orange"
        "Pear"     || null
        null       || null
        ""         || null
        "apple"    || null
        " Apple"   || null
        "Apple "   || null
    }
}