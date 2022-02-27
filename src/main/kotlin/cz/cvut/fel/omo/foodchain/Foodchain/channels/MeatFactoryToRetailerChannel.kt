package cz.cvut.fel.omo.foodchain.Foodchain.channels

import cz.cvut.fel.omo.foodchain.Foodchain.factory.MeatFactory
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Request
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Retailer
import org.slf4j.LoggerFactory

/**
 * Meat factory to retailer channel
 *
 * @constructor Create empty Meat factory to retailer channel
 */
class MeatFactoryToRetailerChannel(private var meatFactory: MeatFactory, private var retailers: ArrayList<Retailer>) : Channel{

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun runSimulation(){
        val retailer : Retailer = retailers[(0 until retailers.size).random()]
        logger.info("CURRENT STATE MEATFACTORY / RETAILER - CHANNEL")
        logger.info("Zahajeni zpracovani masnych produktu : " + meatFactory.getPreparedPorducts() + " zasoby: " + meatFactory.getMeatResources())
        Request.requestProccessMeat(meatFactory)
        logger.info("Masne produkty pripraveny : " + meatFactory.getPreparedPorducts() + " zasoby: " + meatFactory.getMeatResources())
        Request.requestTransportToWarehouse(meatFactory, retailer)
        logger.info("Masne produkty prevezeny do prodeje, aktualni stav produktu : " + meatFactory.getPreparedPorducts())
        retailer.payDebts()
    }

    override fun printStats() {
        logger.info("MeatFactory: " + meatFactory.getIdentifier() + " : money : " + meatFactory.getAmountOfMoney()
                + " : supplies : " + meatFactory.getMeatResources() + " : products : " + meatFactory.getPreparedPorducts())
        for(retailer in retailers){
            logger.info("Retailer: " + retailer.getIdentifier() + " : money : " + retailer.getAmountOfMoney()
                    + " : available products : " + retailer.getStockSize() + " : warehouseProducts : " + retailer.getWarehouseStockSize())
        }
    }
}