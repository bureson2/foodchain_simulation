package cz.cvut.fel.omo.foodchain.Foodchain.products

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Observer
import cz.cvut.fel.omo.foodchain.Foodchain.observer.Subject
import cz.cvut.fel.omo.foodchain.Foodchain.states.Context
import cz.cvut.fel.omo.foodchain.Foodchain.states.State
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Week
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.BaseParty
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Customer
import java.util.*

/**
 * Product
 *
 * @constructor Create empty Product
 */
open class Product(
    private var name: String,
    private var shopPrice: Double,
    private var productionCost: Double,
    private var amount: Int,
    private var unit: String,
    private var origin: UUID,
    private var state : State,
    private var age : Int = 0
) : Subject, Context {

    private var observers: ArrayList<Observer> = ArrayList()
    private var productType: ProductType = ProductType.NOTSET

    /**
     * Get origin id
     *
     * @return
     */
    open fun getOriginId(): UUID {
        return this.origin
    }

    /**
     * Get product type
     *
     * @return
     */
    open fun getProductType(): ProductType {
        return this.productType
    }

    /**
     * Get shop price
     *
     * @return
     */
    open fun getShopPrice(): Double {
        return shopPrice
    }

    /**
     * Get amount
     *
     * @return
     */
    open fun getAmount(): Int {
        return this.amount
    }

    /**
     * Get name
     *
     * @return
     */
    open fun getName() : String{
        return this.name
    }

    override fun attach(o: Observer) {
        observers.add(o)
    }

    override fun detach(o: Observer) {
        observers.remove(o)
    }

    override fun notifyUpdate() {
        for (i in observers) {
            i.update(this.origin,
                "NEW PRODUCT, " + this.name + ", amount: " + this.amount + this.unit + ", shop price: "
                        + Math.round(this.shopPrice * 100.0) / 100.0 + " in week:" + Week.acutalWeek)
        }
    }

    open fun notifyCreateProduct(party : BaseParty) {
        for (i in observers) {
            i.update(this.origin,
                "NEW PRODUCT, " + this.name + ", amount: " + this.amount + this.unit + ", shop price: "
                        + Math.round(this.shopPrice * 100.0) / 100.0 + " in week:" + Week.acutalWeek + " by" + party.getIdentifier())
        }
    }

    open fun notifyTransport() {
        for (i in observers) {
            i.update(this.origin,
                "PRODUCT HAS BEEN SHIPPED, " + this.name + ", shop price: " + Math.round(this.shopPrice * 100.0) / 100.0 + " in week:" + Week.acutalWeek)
        }
    }

    open fun notifyPurchased(customer : Customer, amount :Int){
        for (i in observers) {
            i.update(this.origin,
                "PRODUCT HAS BEEN PURCHASED, " + this.name + ", amount: " + amount.toString() + this.unit + ", shop price: " +
                        Math.round(this.shopPrice * 100.0) / 100.0 + " in week:" + Week.acutalWeek + " by customer " + customer.getIdentifier())
        }
    }

    open fun notifySoldOut(){
        for (i in observers) {
            i.update(this.origin,
                "PRODUCT WAS SOLD OUT, in week:" + Week.acutalWeek)
        }
    }

    open fun notifySpoiled(){
        for (i in observers) {
            i.update(this.origin,
                "THE REST OF PRODUCT WAS SPOILED, in week:" + Week.acutalWeek)
        }
    }

    /**
     * Decrease amount
     *
     * @param value
     */
    open fun decreaseAmount(value : Int){
        this.amount = this.amount - value
    }

    override fun setState(state: State) {
        this.state = state
    }

    fun getState() : State{
        return state
    }

    fun getAge() : Int{
        return age
    }

    fun increaseAge(){
        this.age++
    }
}
