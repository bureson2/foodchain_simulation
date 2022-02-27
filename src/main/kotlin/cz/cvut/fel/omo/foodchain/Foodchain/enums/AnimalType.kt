package cz.cvut.fel.omo.foodchain.Foodchain.enums

import java.util.*

/**
 * Animal type
 *
 * @constructor Create empty Animal type
 */
enum class AnimalType {

    COW,
    PIG,
    CHICKEN,
    FISH;

    companion object {
        fun getAnimal(): AnimalType {
            val random = Random()
            return values()[random.nextInt(values().size)]
        }
    }
}





