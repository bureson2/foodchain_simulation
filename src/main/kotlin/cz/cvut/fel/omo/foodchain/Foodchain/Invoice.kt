package cz.cvut.fel.omo.foodchain.Foodchain

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Observer
import cz.cvut.fel.omo.foodchain.Foodchain.observer.Subject
import cz.cvut.fel.omo.foodchain.Foodchain.enums.InvoiceType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.BaseParty
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Week
import java.util.*

/**
 * Invoice
 *
 * @property subscriber
 * @property contractor
 * @property price
 * @property note
 * @constructor Create empty Invoice
 */
class Invoice( // odberatel
    private val subscriber: BaseParty,// dodavatel
    private val contractor: BaseParty, private val price: Double, private val note: InvoiceType
) : Subject {

    private var observers: ArrayList<Observer> = ArrayList()

    private var isPaid: Boolean
    private var code: UUID
    private var createTime: Int
    private var paidTime: Int = 0

    init {
        this.isPaid = false
        this.code = UUID.randomUUID()
        this.createTime = Week.acutalWeek
    }

    /**
     * Get create time
     *
     * @return
     */
    fun getCreateTime(): Int {
        return this.createTime
    }

    /**
     * Get subscriber
     *
     * @return
     */
    fun getSubscriber(): BaseParty {
        return subscriber
    }

    /**
     * Get contractor
     *
     * @return
     */
    fun getContractor(): BaseParty {
        return contractor
    }

    /**
     * Get price
     *
     * @return
     */
    fun getPrice(): Double {
        return price
    }

    /**
     * Get note
     *
     * @return
     */
    fun getNote(): InvoiceType {
        return note
    }

    /**
     * Get code
     *
     * @return
     */
    fun getCode(): UUID {
        return code
    }

    /**
     * Is paid
     *
     * @return
     */
    fun isPaid(): Boolean {
        return isPaid
    }

    /**
     * Pay invoice
     *
     */
    fun payInvoice() {
        this.paidTime = Week.acutalWeek
        isPaid = true
    }


    override fun attach(o: Observer) {
        observers.add(o)
    }

    override fun detach(o: Observer) {
        observers.remove(o)
    }

    override fun notifyUpdate() {
        for (i in observers) {
            i.update(this.code, "NEW INVOICE, Subsriber: " + this.subscriber.getIdentifier() +
                    ", Contractor: " + this.contractor.getIdentifier() + ", price: " + Math.round(this.price * 100.0) / 100.0  + ", in week:" + Week.acutalWeek)
        }
    }

    /**
     * Notify paid
     *
     */
    fun notifyPaid(){
        for (i in observers) {
            i.update(this.code, "INVOICE HAS BEEN PAID, Subsriber: " + this.subscriber.getIdentifier() +
                    ", Contractor: " + this.contractor.getIdentifier() + ", price: " + Math.round(this.price * 100.0) / 100.0 + ", in week:" + Week.acutalWeek)
        }
    }

    /**
     * Notify unpaid
     *
     */
    fun notifyUnpaid(){
        for (i in observers) {
            i.update(this.code, "INVOICE HAS NOT BEEN PAID, Subsriber: " + this.subscriber.getIdentifier() +
                    ", Contractor: " + this.contractor.getIdentifier() + ", price: " + Math.round(this.price * 100.0) / 100.0)
        }
    }
}