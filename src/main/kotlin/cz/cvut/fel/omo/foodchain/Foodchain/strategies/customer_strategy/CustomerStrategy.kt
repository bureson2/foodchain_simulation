package cz.cvut.fel.omo.foodchain.Foodchain.strategies.customer_strategy

import cz.cvut.fel.omo.foodchain.Foodchain.parties.Customer
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

/**
 * Customer strategy
 *
 * @constructor Create empty Customer strategy
 */
interface CustomerStrategy {
    /**
     * Execute
     *
     * @param products
     * @return
     */
    fun execute(customer : Customer, products : ArrayList<Product>): Pair<Double, ArrayList<Product>>
}