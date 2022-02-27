package cz.cvut.fel.omo.foodchain.Foodchain.strategies.product_strategy

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropName
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Processor
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.CropProduct
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product


/**
 * Flower crop strategy
 *
 * @constructor Create empty Flower crop strategy
 */
class FlowerCropStrategy : ProcessorCropStrategy {
    override fun execute(proccessor : Processor, crop: Crop): Product {
        return when (crop.getName()) {
            CropName.FLEX -> createFromFlex(proccessor, crop)
            CropName.HEMP -> createFromHemp(proccessor, crop)
            CropName.OILSEED -> createFromOilSeed(proccessor, crop)
            CropName.POPPY -> createFromPoppy(proccessor, crop)
            CropName.SUNFLOWER -> createFromSunflower(proccessor, crop)
            else -> throw Exception("Wrong crop name input " + crop.getName())
        }
    }

    /**
     * Create from flex
     *
     * @param crop
     * @return
     */
    private fun createFromFlex(proccessor : Processor, crop: Crop): Product {
        when ((1..2).random()) {
            1 -> {
                val product = CropProduct(
                    "Flex powder",
                    ProductType.BULKINGREDIENTS,
                    (40..60).random().toDouble(),
                    (2..5).random().toDouble(),
                    (crop.getAmount() * 0.75).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            else -> {
                val product = CropProduct(
                    "Flex material",
                    ProductType.OTHERS,
                    (250..600).random().toDouble(),
                    (40..60).random().toDouble(),
                    (crop.getAmount() * 0.15).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
        }
    }

    /**
     * Create from sunflower
     *
     * @param crop
     * @return
     */
    private fun createFromSunflower(proccessor : Processor, crop: Crop): Product {
        when ((1..2).random()) {
            1 -> {
                val product = CropProduct(
                    "Sunflower seeds",
                    ProductType.OTHERS,
                    (30..60).random().toDouble(),
                    (5..10).random().toDouble(),
                    (crop.getAmount() * 0.15).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            else -> {
                val product = CropProduct(
                    "Sunflower oil",
                    ProductType.OIL,
                    (40..75).random().toDouble(),
                    (15..20).random().toDouble(),
                    (crop.getAmount() * 0.15).toInt(),
                    "l",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
        }
    }

    /**
     * Create from poppy
     *
     * @param crop
     * @return
     */
    private fun createFromPoppy(proccessor : Processor, crop: Crop): Product {
        when ((1..2).random()) {
            1 -> {
                val product = CropProduct(
                    "Milled poppy",
                    ProductType.BULKINGREDIENTS,
                    (40..60).random().toDouble(),
                    (2..5).random().toDouble(),
                    (crop.getAmount() * 0.75).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            else -> {
                val product = CropProduct(
                    "Opium",
                    ProductType.XXX,
                    (250..600).random().toDouble(),
                    (40..60).random().toDouble(),
                    (crop.getAmount() * 0.15).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
        }
    }

    /**
     * Create from hemp
     *
     * @param crop
     * @return
     */
    private fun createFromHemp(proccessor : Processor, crop: Crop): Product {
        when ((1..6).random()) {
            1 -> {
                val product = CropProduct(
                    "Hemp Tea",
                    ProductType.DRINK,
                    (150..250).random().toDouble(),
                    (2..5).random().toDouble(),
                    (crop.getAmount() * 0.2).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            2 -> {
                val product = CropProduct(
                    "Hemp ointment",
                    ProductType.OTHERS,
                    (400..1500).random().toDouble(),
                    (100..350).random().toDouble(),
                    (crop.getAmount() * 0.8).toInt(),
                    "l",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            3 -> {
                val product = CropProduct(
                    "Hemp protein",
                    ProductType.OTHERS,
                    (80..200).random().toDouble(),
                    (40..65).random().toDouble(),
                    (crop.getAmount() * 0.2).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            4 -> {
                val product = CropProduct(
                    "Hemp cookies",
                    ProductType.OTHERS,
                    (800..1500).random().toDouble(),
                    (300..600).random().toDouble(),
                    (crop.getAmount() * 0.1).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            5 -> {
                val product = CropProduct(
                    "Hemp oil",
                    ProductType.OIL,
                    (60..140).random().toDouble(),
                    (20..40).random().toDouble(),
                    (crop.getAmount() * 0.1).toInt(),
                    "l",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            else -> {
                val product = CropProduct(
                    "Weed",
                    ProductType.XXX,
                    (600..1200).random().toDouble(),
                    (250..450).random().toDouble(),
                    (crop.getAmount() * 0.1).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
        }
    }

    /**
     * Create from oil seed
     *
     * @param crop
     * @return
     */
    private fun createFromOilSeed(proccessor : Processor, crop: Crop): Product {
        when ((1..2).random()) {
            1 -> {
                val product = CropProduct(
                    "Oilseed oil",
                    ProductType.OIL,
                    (40..60).random().toDouble(),
                    (2..5).random().toDouble(),
                    (crop.getAmount() * 0.75).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            else -> {
                val product = CropProduct(
                    "Biofuel",
                    ProductType.OTHERS,
                    (250..600).random().toDouble(),
                    (40..60).random().toDouble(),
                    (crop.getAmount() * 0.15).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
        }
    }


}