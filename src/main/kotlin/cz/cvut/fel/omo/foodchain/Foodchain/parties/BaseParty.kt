package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.Invoice
import org.slf4j.LoggerFactory
import java.util.*
import kotlin.collections.ArrayList

/**
 * Base party
 *
 * @constructor Create empty Base party
 */
open class BaseParty(
    private val subjectName: String,
    private val location: String,
    protected var amountOfMoney: Double
) {

    private val identifier : UUID = UUID.randomUUID()
    private var unpaidInvoices : ArrayList<Invoice> = ArrayList()
    protected var debts : ArrayList<Invoice> = ArrayList()

    private val logger = LoggerFactory.getLogger(javaClass)

    /**
     * Get subject name
     *
     * @return
     */
    @JvmName("getName")
    fun getSubjectName() : String{
        return subjectName
    }

    /**
     * Get identifier
     *
     * @return
     */
    @JvmName("getId")
    fun getIdentifier() : UUID{
        return identifier
    }

    /**
     * Get location
     *
     * @return
     */
    @JvmName("getLoc")
    fun getLocation() : String{
        return location
    }

    /**
     * Get amount of money
     *
     * @return
     */
    @JvmName("getMoney")
    fun getAmountOfMoney() : Double{
        return amountOfMoney
    }

    /**
     * Change amount of money
     *
     * @param value
     */
    fun changeAmountOfMoney(value : Double){
        amountOfMoney += value
    }

    /**
     * Take money
     *
     * @param value
     */
    fun takeMoney(value : Double){
        amountOfMoney += value
    }


    /**
     * Pay for invoice
     *
     * @param invoice
     */
    open fun payForInvoice(invoice : Invoice) {
        if (amountOfMoney >= invoice.getPrice()) {
            invoice.getContractor().takeMoney(invoice.getPrice())
            amountOfMoney -= invoice.getPrice()
            logger.info("Faktura " + invoice.getCode() + " zaplacena")
            invoice.payInvoice()
            invoice.notifyPaid()
        } else {
            unpaidInvoices.add(invoice)
            logger.info("!Faktura " + invoice.getCode() + " NENI uhrazena")
            invoice.notifyUnpaid()
        }
    }

    /**
     * Pay debts
     *
     */
    fun payDebts(){
        val toRemove : ArrayList<Invoice> = ArrayList()
        for(invoice in debts){
            if(amountOfMoney >= invoice.getPrice()){
                toRemove.add(invoice)
                payForInvoice(invoice)
            }
        }
        for(invoice in toRemove){
            logger.info("Penize za " + invoice.getCode() + " splaceny")
            debts.remove(invoice)
        }
    }
}