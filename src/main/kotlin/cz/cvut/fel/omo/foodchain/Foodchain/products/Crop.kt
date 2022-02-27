package cz.cvut.fel.omo.foodchain.Foodchain.products

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Observer
import cz.cvut.fel.omo.foodchain.Foodchain.observer.Subject
import cz.cvut.fel.omo.foodchain.Foodchain.states.Context
import cz.cvut.fel.omo.foodchain.Foodchain.states.CropState
import cz.cvut.fel.omo.foodchain.Foodchain.states.State
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Week
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropName
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Grower
import java.util.*

/**
 * Crop
 *
 * @constructor Create empty Crop
 */
class Crop(
    private val name: CropName,
    private val type: CropType,
    private var amount: Int,
    private var growthTime: Int
) : Subject, Context {

    private var observers: ArrayList<Observer> = ArrayList()

    private val uuid: UUID = UUID.randomUUID()
    private var shopPrice: Double = name.shopPrice
    private var productionCost: Double = shopPrice * 0.01
    private var state : State = CropState(this, uuid)

    /**
     * Get u u i d
     *
     * @return
     */
    fun getUUID(): UUID {
        return this.uuid
    }

    /**
     * Get name
     *
     * @return
     */
    fun getName(): CropName {
        return name
    }

    /**
     * Get amount
     *
     * @return
     */
    fun getAmount(): Int {
        return amount
    }

    /**
     * Get production cost
     *
     * @return
     */
    fun getProductionCost(): Double {
        return productionCost
    }

    /**
     * Get shop price
     *
     * @return
     */
    fun getShopPrice(): Double {
        return shopPrice
    }

    /**
     * Get type
     *
     * @return
     */
    fun getType(): CropType {
        return type
    }

    /**
     * Get growth
     *
     * @return
     */
    fun getGrowth(): Int {
        return growthTime
    }

    /**
     * Set amount
     *
     * @param value
     */
    fun setAmount(value: Int) {
        this.amount = value
    }

    /**
     * Decrease amount
     *
     */
    fun decreaseAmount() {
        this.amount -= 5
    }


    /**
     * Grown
     *
     */
    fun grow() {
        growthTime++
    }

    override fun attach(o: Observer) {
        observers.add(o)
    }

    override fun detach(o: Observer) {
        observers.remove(o)
    }

    override fun notifyUpdate() {
        for (i in observers) {
            i.update(this.uuid, "NEW CROP, " + this.name + ", amount: " + this.amount + "kg, in week: " + Week.acutalWeek)
        }
    }

    /**
     * Notify was harvested
     *
     */
    fun notifyWasHarvested(grower : Grower){
        for (i in observers) {
            i.update(this.uuid, "CROP HAS BEEN HARVESTED IN WEEK: " + Week.acutalWeek + " by " + grower.getIdentifier())
        }
    }

    fun notifyTransport() {
        for (i in observers) {
            i.update(this.uuid,
                "CROPT HAS BEEN SHIPPED, " + this.name + ", amount: " + this.amount + "kg, in week:" + Week.acutalWeek)
        }
    }

    /**
     * Set state
     *
     * @param state
     */
    override fun setState(state: State) {
        this.state = state
    }

    /**
     * Get state
     *
     * @return
     */
    fun getState() : State{
        return state
    }
}
