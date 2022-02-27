package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.Field
import cz.cvut.fel.omo.foodchain.Foodchain.Generator
import org.slf4j.LoggerFactory

/**
 * Grower
 *
 * @constructor
 *
 * @param subjectName
 * @param location
 * @param amountOfMoney
 */
class Grower(subjectName : String, location : String, amountOfMoney : Double)
    : BaseParty(subjectName, location, amountOfMoney) {

    private val fields : ArrayList<Field> = setInitialField()
    private var supplies : ArrayList<Crop> = setInitalSupplies()

    private val logger = LoggerFactory.getLogger(javaClass)


    /**
     * Set initial field
     *
     * @return
     */
    private fun setInitialField() : ArrayList<Field>{
        val generator = Generator()
        return generator.generateFields()
    }


    /**
     * Set inital supplies
     *
     * @return
     */
    private fun setInitalSupplies() : ArrayList<Crop>{
        val generator = Generator()
        return generator.generateCrops()
    }

    /**
     * Raise field
     *
     */
    fun raiseField(){
        for(field in fields){
            if(amountOfMoney >= field.getCrop().getProductionCost()){
                field.setRaised(true)
                amountOfMoney -= field.getCrop().getProductionCost()
            }
            else if(field.isRaised()){
                field.setRaised(false)
            } else {
                field.decreaseProduction()
            }
        }
    }

    /**
     * Harvest
     *
     */
    fun harvest(){
        val harvestedCrop : ArrayList<Crop> = ArrayList()
        for(field in fields){
            logger.info("Plodina vek: " + field.getCrop().getGrowth())
            if(field.getCrop().getGrowth() >= 10){
                logger.info("Plodina " + field.getCrop().getName() + " sklizena v poctu " + field.getCrop().getAmount())
                val crop = field.getCrop()
                harvestedCrop.add(crop)
                crop.notifyWasHarvested(this)
                field.resetField()
            } else {
                field.growCrop()
                logger.info("Plodina " + field.getCrop().getName() + " vyrostla")
            }
        }

        for(crop in harvestedCrop){
            supplies.add(crop)
        }
    }

    /**
     * Transport supplies
     *
     */
    fun transportSupplies(){
        Transport.takeCropSupplies(supplies)
        supplies = ArrayList()
    }

    /**
     * Get supplies
     *
     * @return
     */
    fun getSupplies() : ArrayList<Crop> {
        return supplies
    }

    /**
     * Add emergency crop
     *
     * @param crop
     */
    fun addEmergencyCrop(crop : Crop){
        supplies.add(crop)
    }
}









