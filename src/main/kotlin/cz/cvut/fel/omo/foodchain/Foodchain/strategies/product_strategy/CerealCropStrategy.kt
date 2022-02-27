package cz.cvut.fel.omo.foodchain.Foodchain.strategies.product_strategy

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropName
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Processor
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.CropProduct
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

/**
 * Cereal crop strategy
 *
 * @constructor Create empty Cereal crop strategy
 */
class CerealCropStrategy : ProcessorCropStrategy {
    override fun execute(proccessor : Processor, crop: Crop): Product {
        return when (crop.getName()) {
            CropName.BARLEY -> prepareForBeer(proccessor, crop)
            CropName.CORN -> createFromCorn(proccessor, crop)
            CropName.OATS -> createFromOats(proccessor, crop)
            CropName.RYE -> createFromGrain(proccessor, crop)
            CropName.WHEET -> createFromGrain(proccessor, crop)
            CropName.HOP -> prepareForBeer(proccessor, crop)
            else -> throw Exception("Wrong crop name input")
        }
    }

    /**
     * Create from grain
     *
     * @param crop
     * @return
     */
    private fun createFromGrain(processor : Processor, crop: Crop): Product {
        when ((1..2).random()) {
            1 -> {
                val product = CropProduct(
                    "Flour",
                    ProductType.BULKINGREDIENTS,
                    (15..40).random().toDouble(),
                    (2..5).random().toDouble(),
                    (crop.getAmount() * 0.4).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(processor)
                return product
            }
            else -> {
                val product = CropProduct(
                    "Coarse flour",
                    ProductType.BULKINGREDIENTS,
                    (10..30).random().toDouble(),
                    (1..4).random().toDouble(),
                    (crop.getAmount() * 0.4).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(processor)
                return product
            }
        }
    }

    /**
     * Prepare for beer
     *
     * @param crop
     * @return
     */
    private fun prepareForBeer(processor : Processor, crop: Crop): Product {
        val product = CropProduct(
            "Beer",
            ProductType.ALCOHOL,
            (4..6).random().toDouble(),
            (2..4).random().toDouble(),
            (crop.getAmount() * 0.25).toInt(),
            "l",
            crop.getUUID(),
            crop.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(processor)
        return product
    }

    /**
     * Create from corn
     *
     * @param crop
     * @return
     */
    private fun createFromCorn(processor : Processor, crop: Crop): Product {
        when ((1..3).random()) {
            1 -> {
                val product = CropProduct(
                    "Boiled corn",
                    ProductType.VEGETABLES,
                    (40..45).random().toDouble(),
                    (2..5).random().toDouble(),
                    (crop.getAmount() * 0.95).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(processor)
                return product
            }
            2 -> {
                val product = CropProduct(
                    "Corn can",
                    ProductType.CANS,
                    (30..45).random().toDouble(),
                    (3..6).random().toDouble(),
                    (crop.getAmount() * 0.4).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(processor)
                return product
            }
            else -> {
                val product = CropProduct(
                    "Iced corn",
                    ProductType.ICED,
                    (30..35).random().toDouble(),
                    (1..2).random().toDouble(),
                    (crop.getAmount() * 0.4).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(processor)
                return product
            }
        }
    }

    /**
     * Create from oats
     *
     * @param crop
     * @return
     */
    private fun createFromOats(processor : Processor, crop: Crop): Product {
        when ((1..2).random()) {
            1 -> {
                val product = CropProduct(
                    "Musli",
                    ProductType.CEREALS,
                    (15..30).random().toDouble(),
                    (2..6).random().toDouble(),
                    (crop.getAmount() * 0.75).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(processor)
                return product
            }
            else -> {
                val product = CropProduct(
                    "Oat flakes",
                    ProductType.CEREALS,
                    (20..35).random().toDouble(),
                    (1..2).random().toDouble(),
                    (crop.getAmount() * 0.75).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(processor)
                return product
            }
        }
    }
}