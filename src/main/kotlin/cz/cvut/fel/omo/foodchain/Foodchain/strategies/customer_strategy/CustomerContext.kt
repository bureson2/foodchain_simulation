package cz.cvut.fel.omo.foodchain.Foodchain.strategies.customer_strategy

import cz.cvut.fel.omo.foodchain.Foodchain.parties.Customer
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

/**
 * Customer context
 *
 * @constructor Create empty Customer context
 */
class CustomerContext(var strategy: CustomerStrategy) {

    /**
     * Set strategy
     *
     * @param strategy
     */
    fun setStrategy(strategy : BasicStrategy){
        this.strategy = strategy
    }

    /**
     * Set strategy
     *
     * @param strategy
     */
    fun setStrategy(strategy : EasterEggStrategy){
        this.strategy = strategy
    }

    /**
     * Set strategy
     *
     * @param strategy
     */
    fun setStrategy(strategy : MeatLoverStrategy){
        this.strategy = strategy
    }

    /**
     * Set strategy
     *
     * @param strategy
     */
    fun setStrategy(strategy : RandomStrategy){
        this.strategy = strategy
    }

    /**
     * Set strategy
     *
     * @param strategy
     */
    fun setStrategy(strategy : VeganStrategy){
        this.strategy = strategy
    }

    /**
     * Go shopping
     *
     * @param products
     * @return
     */
    fun goShopping(customer : Customer, products : ArrayList<Product>) : Pair<Double, ArrayList<Product>>{
        return strategy.execute(customer , products)
    }
}