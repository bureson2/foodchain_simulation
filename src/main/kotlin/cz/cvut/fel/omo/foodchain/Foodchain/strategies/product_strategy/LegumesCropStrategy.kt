package cz.cvut.fel.omo.foodchain.Foodchain.strategies.product_strategy

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Processor
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.CropProduct
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

/**
 * Legumes crop strategy
 *
 * @constructor Create empty Legumes crop strategy
 */
class LegumesCropStrategy : ProcessorCropStrategy {
    override fun execute(proccessor : Processor, crop: Crop): Product {
        when ((1..2).random()) {
            1 -> {
                val product =  CropProduct(
                    "Raw" + crop.getName().toString(),
                    ProductType.LEGUMES,
                    crop.getShopPrice() * 1.2,
                    crop.getShopPrice(),
                    crop.getAmount(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            else -> {
                val product =  CropProduct(
                    "Can of " + crop.getName().toString(),
                    ProductType.CANS,
                    crop.getShopPrice() * 1.4,
                    crop.getShopPrice() + 5,
                    (crop.getAmount() * 0.8).toInt(),
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