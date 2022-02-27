package cz.cvut.fel.omo.foodchain.Foodchain.statics

/**
 * Config
 *
 * @constructor Create empty Config
 */
class Config {
    companion object Params {
        // main config
        var WEEKS: Int = 10
        var GROWERS: Int = 2
        var FARMERS: Int = 2
        var PROCESSORS: Int = 2
        var CUSTOMERS: Int = 100
        var RETAILERS: Int = 1

        var STANDARD_SHOP_SIZE: Int = 10
        var VEGAN_SHOP_SIZE: Int = 2
        var WORKOUT_SHOP_SIZE: Int = 500

        const val SALARY_MIN = 10000
        const val SALARY_MAX = 80000

        const val ANIMALS_MIN = 20
        const val ANIMALS_MAX = 50

        const val FIELD_CAPACITY_MIN = 100
        const val FIELD_CAPACITY_MAX = 1000

        const val FLOWER_MAX_AGE = 10

        const val MIN_FIELDS = 2
        const val MAX_FIELDS = 10

        const val MIN_DEAFAULT_CROPTYPES = 5
        const val MAX_DEFAULT_CROPTYPES = 10

        const val MIN_DEFAULT_CROPS = 100
        const val MAX_DEFAULT_CROPS = 1000

        const val FINANCE_MIN = 1000000
        const val FINANCE_MAX = 10000000
        const val NAME_LENGTH = 12
        const val LOCATION_LENGTH = 16

        const val TRANSPORT_TAX = 0.1

        const val TIME_OF_PRODUCT_LIFE = 6
    }
}