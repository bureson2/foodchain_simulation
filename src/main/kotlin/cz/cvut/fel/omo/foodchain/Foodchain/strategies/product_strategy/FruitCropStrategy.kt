package cz.cvut.fel.omo.foodchain.Foodchain.strategies.product_strategy

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropName
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Processor
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.CropProduct
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

/**
 * Fruit crop strategy
 *
 * @constructor Create empty Fruit crop strategy
 */
class FruitCropStrategy : ProcessorCropStrategy {
    override fun execute(proccessor : Processor, crop: Crop): Product {
        if (crop.getName() == CropName.GRAPEVINE) {
            return createFromVine(proccessor, crop)
        }
        when ((1..4).random()) {
            1 -> {
                val product = CropProduct(
                    crop.getName().toString(),
                    ProductType.FRUIT,
                    crop.getShopPrice() * 1.4,
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
            2 -> {
                val product = CropProduct(
                    crop.getName().toString() + "jam",
                    ProductType.SAUCE,
                    crop.getShopPrice() + 50,
                    crop.getShopPrice(),
                    (crop.getAmount() * 0.85).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            3 -> {
                val product = CropProduct(
                    crop.getName().toString() + "juice",
                    ProductType.DRINK,
                    crop.getShopPrice() + 50,
                    crop.getShopPrice(),
                    (crop.getAmount() * 0.85).toInt(),
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
                    crop.getName().toString() + "compote",
                    ProductType.CANS,
                    (crop.getShopPrice() + 40),
                    crop.getShopPrice(),
                    (crop.getAmount() * 0.85).toInt(),
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
     * Create from vine
     *
     * @param crop
     * @return
     */
    private fun createFromVine(proccessor : Processor, crop: Crop): Product {
        when ((1..2).random()) {
            1 -> {
                val product = CropProduct(
                    crop.getName().toString(),
                    ProductType.FRUIT,
                    crop.getShopPrice() * 1.4,
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
                val product = CropProduct(
                    "Vine" + (2010..2022).random(),
                    ProductType.ALCOHOL,
                    crop.getShopPrice() + 120,
                    crop.getShopPrice() * 2.45,
                    (crop.getAmount() * 0.1).toInt(),
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
}