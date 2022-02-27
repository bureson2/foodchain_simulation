package cz.cvut.fel.omo.foodchain.Foodchain.channels

import cz.cvut.fel.omo.foodchain.Foodchain.Generator
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Request
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Customer
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Retailer
import org.slf4j.LoggerFactory

/**
 * Retailer to customer channel
 *
 * @constructor Create empty Retailer to customer channel
 */
class RetailerToCustomerChannel(private var retailers: ArrayList<Retailer>) : Channel{

    private var customers : ArrayList<Customer>
    private var generator : Generator = Generator()

    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        this.customers = generator.generateCustomers()
    }

    override fun runSimulation(){
        payForWarehouse()
        fillShops()
        for(customer in customers){
            val retailer : Retailer = retailers[(0 until retailers.size).random()]
            customer.receiveSalary()
            Request.requestGoShopping(retailer, customer)
            customer.payDebts()
           /* retailer.checkResources()*/
        }
        var i = 1
        for(retailer in retailers){
            i++
            retailer.checkResources()
        }
    }

    override fun printStats(){
        logger.info("CURRENT STATE RETAILER / CUSTOMER - CHANNEL")
        for(retailer in retailers){
            logger.info("Retailer: " + retailer.getIdentifier() + " : money : " + retailer.getAmountOfMoney()
                    + " : available products : " + retailer.getStockSize() + " : warehouseProducts : " + retailer.getWarehouseStockSize())
        }
        for(customer in customers){
            logger.info("Customer: " + customer.getIdentifier() + " : money : " + customer.getAmountOfMoney())
        }
    }

    private fun fillShops(){
        for(retailer in retailers){
            Request.requestTakeOutToShop(retailer)
            logger.info("Retailer: " + retailer.getIdentifier() + " naskladnil " + retailer.getStockSize() + " produktu.")
        }
    }

    private fun payForWarehouse(){
        for(retailer in retailers){
            retailer.payForWarehouseManagement()
        }
    }

    /**
     * Add customer
     *
     * @param customer
     */
    fun addCustomer(customer : Customer){
        customers.add(customer)
    }

}