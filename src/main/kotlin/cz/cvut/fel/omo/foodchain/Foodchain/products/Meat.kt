package cz.cvut.fel.omo.foodchain.Foodchain.products

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Observer
import cz.cvut.fel.omo.foodchain.Foodchain.observer.Subject
import cz.cvut.fel.omo.foodchain.Foodchain.states.Context
import cz.cvut.fel.omo.foodchain.Foodchain.states.State
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Week
import cz.cvut.fel.omo.foodchain.Foodchain.enums.MeatType
import java.util.*

/**
 * Meat
 *
 * @constructor Create empty Meat
 */
class Meat(
    private val type: MeatType,
    private var shopPrice: Double,
    private var productionCost: Double,
    private var amount: Double,
    private var origin: UUID,
    private var state : State
) : Subject, Context {

    private var observers: ArrayList<Observer> = ArrayList()

    /**
     * Get origin i d
     *
     * @return
     */
    fun getOriginID(): UUID {
        return this.origin
    }

    /**
     * Get type
     *
     * @return
     */
    fun getType(): MeatType {
        return this.type
    }


    /**
     * Get shop price
     *
     * @return
     */
    fun getShopPrice(): Double {
        return this.shopPrice
    }


    /**
     * Get amount
     *
     * @return
     */
    fun getAmount(): Double {
        return this.amount
    }

    override fun attach(o: Observer) {
        observers.add(o)
    }

    override fun detach(o: Observer) {
        observers.remove(o)
    }

    override fun notifyUpdate() {
        for (i in observers) {
            i.update(this.origin, "NEW MEAT, " + this.type + ", amount: " + Math.round(this.amount * 100.0) / 100.0 + "kg, in week: " + Week.acutalWeek)
        }
    }

    fun notifyTransport() {
        for (i in observers) {
            i.update(this.origin,
                "MEAT HAS BEEN SHIPPED, " + this.type + ", amount: " + Math.round(this.amount * 100.0) / 100.0 + "kg, in week: " + Week.acutalWeek)
        }
    }

    override fun setState(state: State) {
        this.state = state
    }

    fun getState() : State{
        return state
    }
}
