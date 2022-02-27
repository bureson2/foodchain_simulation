package cz.cvut.fel.omo.foodchain.Foodchain.channels

import cz.cvut.fel.omo.foodchain.Foodchain.statics.Request
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropName
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Farmer
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Grower
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import org.slf4j.LoggerFactory

/**
 * Grower to farmer channel
 *
 * @constructor Create empty Grower to farmer channel
 */
class GrowerToFarmerChannel(private var growers: ArrayList<Grower>, private var farmers: ArrayList<Farmer>) : Channel{

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun runSimulation(){
        for(farmer in farmers){
            if(farmer.controlResource()){
                Request.requestFarmerBuyCrops(farmer, findCorrectSupplier())
            }
            farmer.payDebts()
        }
    }

    override fun printStats() {
        // Zde se neaktualizuji podstatne statistiky, jednalo by se o redundanci
    }

    /**
     * Find correct supplier
     *
     * @return
     */
    private fun findCorrectSupplier() : Grower{
        for(grower in growers){
            for(crop in grower.getSupplies()){
                if(crop.getType() == CropType.CEREAL){
                    return grower
                }
            }
        }
        logger.info("Nedostatek vhodnych dodavatelu v CR, zasoby budou zakoupeny ze zahranici")
        val exoticGrower = Grower("sosGrower", "Exotica", 0.0)
        val exoticCrop = Crop(CropName.WHEET, CropType.CEREAL, 50, 0)
        exoticGrower.addEmergencyCrop(exoticCrop)
        return exoticGrower
    }
}