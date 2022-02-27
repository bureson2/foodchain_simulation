package cz.cvut.fel.omo.foodchain.Foodchain

import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Config
import java.util.*
import kotlin.math.roundToInt

/**
 * Field
 *
 * @constructor Create empty Field
 */
class Field(private var crop: Crop, private val capacity: Int) {

    private var isRaised: Boolean = true
    private var uuid: UUID = UUID.randomUUID()

    /**
     * Get capacity
     *
     * @return
     */
    fun getCapacity(): Int {
        return this.capacity
    }

    /**
     * Is raised
     *
     * @return
     */
    fun isRaised(): Boolean {
        return isRaised
    }

    /**
     * Set raised
     *
     * @param value
     */
    fun setRaised(value: Boolean) {
        isRaised = value
    }

    /**
     * Decrease production
     *
     */
    fun decreaseProduction() {
        crop.setAmount((crop.getAmount() * 0.8).roundToInt())
    }

    /**
     * Reset field
     *
     */
    fun resetField() {
        val generator = Generator()
        this.crop = generator.generateCrop((Config.FIELD_CAPACITY_MIN..Config.FIELD_CAPACITY_MAX).random())
    }

    /**
     * Get crop
     *
     * @return
     */
    fun getCrop(): Crop {
        return crop
    }

    /**
     * Grow crop
     *
     */
    fun growCrop() {
        crop.grow()
    }
}