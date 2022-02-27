package cz.cvut.fel.omo.foodchain.Foodchain.channels

import cz.cvut.fel.omo.foodchain.Foodchain.Generator
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Request
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Processor
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Retailer
import org.slf4j.LoggerFactory

/**
 * Processor to retailer channel
 *
 * @constructor Create empty Processor to retailer channel
 */
class ProcessorToRetailerChannel(private var processors: ArrayList<Processor>) : Channel{

    private var retailers : ArrayList<Retailer>

    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        logger.info("PHASE 0 - Retailers creation")
        val generator = Generator()
        this.retailers = generator.generateRetailers()
    }

    override fun printStats(){
        logger.info("CURRENT STATE PROCESSOR / RETAILER - CHANNEL")
        for(processor in processors){
            logger.info("Processor: " + processor.getIdentifier() + " : money : " + processor.getAmountOfMoney()
                    + " : supplies : " + processor.getStockSuppliesSize() + " : products : " + processor.getStockProductsSize())
        }
        for(retailer in retailers){
            logger.info("Retailer: " + retailer.getIdentifier() + " : money : " + retailer.getAmountOfMoney()
                    + " : available products : " + retailer.getStockSize() + " : warehouseProducts : " + retailer.getWarehouseStockSize())
        }
    }

    override fun runSimulation(){
        for(processor in processors){
            val retailer : Retailer = retailers[(0 until retailers.size).random()]
            Request.requestTransportToWarehouse(processor, retailer)
            logger.info("Priprava k vytvareni vyrobku")
            processor.processProduct()
            logger.info("Produkty vytvoreny")
            retailer.payDebts()
        }
    }


    /**
     * Get retailers
     *
     * @return
     */
    fun getRetailers() : ArrayList<Retailer>{
        return retailers
    }

    /**
     * Add retailer
     *
     * @param retailer
     */
    fun addRetailer(retailer: Retailer){
        retailers.add(retailer)
    }
}