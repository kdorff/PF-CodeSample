package kdorff.cart.services

import kdorff.cart.models.Cart
import kdorff.cart.models.InventoryItem
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
        cartService.inventoryService = Mock(InventoryService)
        InventoryItem apple = new InventoryItem('Apple', Money.of(CurrencyUnit.GBP, 0.60d))
        InventoryItem orange = new InventoryItem('Orange', Money.of(CurrencyUnit.GBP, 0.25d))
        cartService.inventoryService.findInInventory("Apple") >> apple
        cartService.inventoryService.findInInventory("Orange") >> orange

        expect:
        Cart cart = cartService.createCartFromList(inputs)
        cart.itemsToQuantity.get(apple) == expNumApples
        cart.itemsToQuantity.get(orange) == expNumOranges
        cart.currency == expCurrency
        cart.totalAsDouble == expTotal

        where:
        inputs                                  || expNumApples | expNumOranges | expCurrency | expTotal
        ['Apple', 'Apple', 'Orange', 'Apple']   || 3            | 1             | 'GBP'       | 2.05D
        []                                      || null         | null          | 'GBP'       | 0.00D
        ['Apple']                               || 1            | null          | 'GBP'       | 0.60D
        ['Orange']                              || null         | 1             | 'GBP'       | 0.25D
        ['Orange', 'Apple']                     || 1            | 1             | 'GBP'       | 0.85D
        ['Orange', 'Apple', 'Kitten', 'Orange'] || 1            | 2             | 'GBP'       | 1.10D

    }
}
