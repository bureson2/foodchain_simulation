package cz.cvut.fel.omo.foodchain.Foodchain.channels

import cz.cvut.fel.omo.foodchain.Foodchain.Generator
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Request
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Grower
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Processor
import org.slf4j.LoggerFactory

/**
 * Grower to processor channel
 *
 * @constructor Create empty Grower to processor channel
 */
class GrowerToProcessorChannel : Channel{

    private var growers : ArrayList<Grower>
    private var processors : ArrayList<Processor>

    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        logger.info("PHASE 0 - Growers and processors creation")
        val generator = Generator()
        growers = generator.generateGrowers()
        processors = generator.generateProcessors()
    }

    override fun printStats(){
        logger.info("CURRENT STATE - GROWER / PROCESSOR - CHANNEL")
        for(grower in growers){
            logger.info("Grover: " + grower.getIdentifier() + " : money : " + grower.getAmountOfMoney()
                    + " : supllies : " + grower.getSupplies().size)
        }
        for(processor in processors){
            logger.info("Processor: " + processor.getIdentifier() + " : money : " + processor.getAmountOfMoney()
                    + " : supplies : " + processor.getStockSuppliesSize() + " : products : " + processor.getStockProductsSize())
            processor.payDebts()
        }
    }

    override fun runSimulation(){
        for(grower in growers){
            val processor = processors[(0 until processors.size).random()]
            Request.requestTransportToProcessor(grower, processor, grower.getSupplies())
            grower.harvest()
            processor.payDebts()
        }
    }

    /**
     * Add grower
     *
     * @param grower
     */
    fun addGrower(grower : Grower){
        growers.add(grower)
    }

    /**
     * Add proccessor
     *
     * @param processor
     */
    fun addProccessor(processor: Processor){
        processors.add(processor)
    }

    /**
     * Get processors
     *
     * @return
     */
    fun getProcessors() : ArrayList<Processor>{
        return processors
    }

    /**
     * Get growers
     *
     * @return
     */
    fun getGrowers() : ArrayList<Grower>{
        return growers
    }

}