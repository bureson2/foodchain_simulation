package cz.cvut.fel.omo.foodchain.Foodchain.enums

import java.util.*

/**
 * Crop name
 *
 * @property shopPrice
 * @constructor Create empty Crop name
 */
enum class CropName (val shopPrice: Double){
    APPLE( 45.0),
    BARLEY(10.0),
    BEAN(10.0),
    CORN(5.0),
    CUCCUMBER(14.0),
    CHERRIES(35.0),
    FLEX(75.0),
    GRAPEVINE(49.0),
    HEMP(120.0),
    HOP(12.0),
    LENTIL(5.0),
    OATS(12.0),
    OILSEED(12.0),
    ONION(20.0),
    PEAR(69.69),
    PLUMS(70.07),
    POPPY(150.01),
    POTATO(45.90),
    RYE(12.20),
    SALAD(37.23),
    SUGARBEAT(35.456),
    SUNFLOWER(12.5678),
    TOMATO(68.45),
    WHEET(8.5);

    companion object {
        fun getCropName(): CropName {
            val random = Random()
            return values()[random.nextInt(values().size)]
        }
    }
}