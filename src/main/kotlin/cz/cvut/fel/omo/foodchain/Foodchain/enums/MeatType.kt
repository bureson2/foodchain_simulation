package cz.cvut.fel.omo.foodchain.Foodchain.enums

/**
 * Meat type
 *
 * @property price
 * @constructor Create empty Meat type
 */
enum class MeatType(val price: Int) {
    BEEF(200),
    PORK(150),
    CHICKEN(100),
    FISH(130),
}