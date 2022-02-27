package cz.cvut.fel.omo.foodchain.Foodchain.channels

import cz.cvut.fel.omo.foodchain.Foodchain.factory.MeatFactory
import cz.cvut.fel.omo.foodchain.Foodchain.Generator
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Request
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Farmer
import org.slf4j.LoggerFactory

/**
 * Farmer to meat factory channel
 *
 * @constructor Create empty Farmer to meat factory channel
 */
class FarmerToMeatFactoryChannel : Channel {

    private var farmers: ArrayList<Farmer>
    private val meatFactory: MeatFactory
    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        logger.info("PHASE 0 - Farmers, butchers and meatFactory creation")
        val generator = Generator()
        this.farmers = generator.generateFarmers()
        this.meatFactory = generator.generateFactory()
    }

    override fun runSimulation() {
        for (farmer in farmers) {
            Request.requestTransportToMeatFactory(farmer, meatFactory)
            logger.info("Maso zpracovano a pripaveno k odvozu")
            farmer.feedAnimal()
            farmer.growAnimals()
            meatFactory.payDebts()
        }
    }

    override fun printStats() {
        logger.info("CURRENT STATE FARMER / MEATFACTORY - CHANNEL")
        for (farmer in farmers) {
            logger.info(
                "farmer: " + farmer.getIdentifier() + " : money : " + farmer.getAmountOfMoney() + " : animals : "
                        + farmer.getNumberOfAnimals() + " : animals to processed : " + farmer.getNumberOfAnimalsToProcess()
            )
        }
        logger.info(
            "MeatFactory: " + meatFactory.getIdentifier() + " : money : " + meatFactory.getAmountOfMoney()
                    + " : resources : " + meatFactory.getMeatResources() + " : prepared : " + meatFactory.getPreparedPorducts()
        )
    }

    /**
     * Get farmers
     *
     * @return
     */
    fun getFarmers(): ArrayList<Farmer> {
        return farmers
    }

    /**
     * Get meat factory
     *
     * @return
     */
    fun getMeatFactory(): MeatFactory {
        return meatFactory
    }

}