package kdorff.cart.services

import kdorff.cart.models.Cart
import kdorff.cart.models.InventoryItem
import kdorff.cart.models.Special
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Tests for CartService.
 */
class CartServiceSpec extends Specification {

    @Unroll
    def "test createCartFromList. inputs of #inputs should have apples=#expNumApples, oranges=#expNumOranges, total=#expTotal"() {
        setup:
        CartService cartService = new CartService()

        // Create inventory
        cartService.inventoryService = Mock(InventoryService)
        InventoryItem apple = new InventoryItem('Apple', Money.of(CurrencyUnit.GBP, 0.60d))
        InventoryItem orange = new InventoryItem('Orange', Money.of(CurrencyUnit.GBP, 0.25d))
        cartService.inventoryService.findInInventory("Apple") >> apple
        cartService.inventoryService.findInInventory("Orange") >> orange

        // Create specials
        cartService.specialsService = Mock(SpecialsService)
        Special appleSpecial = new Special(apple, 2, 1);
        cartService.specialsService.findSpecial("Apple") >> appleSpecial

        Special orangeSpecial = new Special(orange, 3, 1);
        cartService.specialsService.findSpecial("Orange") >> orangeSpecial

        expect:
        Cart cart = cartService.createCartFromList(inputs)
        cart.itemsToQuantity.get(apple) == expNumApples
        cart.itemsToQuantity.get(orange) == expNumOranges
        cart.totalAsDouble == expTotal

        where:
        inputs                                                             || expNumApples | expNumOranges | expTotal
        ['Apple', 'Orange', 'Orange']                                      || 1            | 2             | 1.10d
        ['Apple', 'Apple', 'Orange', 'Orange']                             || 2            | 2             | 1.10d
        ['Apple', 'Orange', 'Orange', 'Apple', 'Apple', 'Apple', 'Orange'] || 4            | 3             | 1.70d
        ['Apple', 'Apple', 'Orange', 'Apple']                              || 3            | 1             | 1.45d
        ['Orange', 'Apple', 'Orange', 'Orange']                            || 1            | 3             | 1.10d
        ['Orange', 'Apple', 'Orange']                                      || 1            | 2             | 1.10d
        []                                                                 || null         | null          | 0.00D
        ['Apple']                                                          || 1            | null          | 0.60D
        ['Orange']                                                         || null         | 1             | 0.25D
        ['Orange', 'Apple']                                                || 1            | 1             | 0.85D
        ['Orange', 'Apple', 'Kitten', 'Orange']                            || 1            | 2             | 1.10D
    }
}
