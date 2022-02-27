package cz.cvut.fel.omo.foodchain.Foodchain.strategies.product_strategy

import cz.cvut.fel.omo.foodchain.Foodchain.parties.Processor
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

/**
 * Processor crop strategy
 *
 * @constructor Create empty Processor crop strategy
 */
interface ProcessorCropStrategy {
    /**
     * Execute
     *
     * @param crop
     * @return
     */
    fun execute(processor : Processor, crop : Crop) : Product

}