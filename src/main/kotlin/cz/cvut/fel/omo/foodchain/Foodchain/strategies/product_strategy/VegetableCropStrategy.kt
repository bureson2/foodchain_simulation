package cz.cvut.fel.omo.foodchain.Foodchain.strategies.product_strategy

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropName
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Processor
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.CropProduct
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

/**
 * Vegetable crop strategy
 *
 * @constructor Create empty Vegetable crop strategy
 */
class VegetableCropStrategy : ProcessorCropStrategy {
    override fun execute(proccessor : Processor, crop: Crop): Product {
        when (crop.getName()) {
            CropName.POTATO -> return createFromPotato(proccessor, crop)
            CropName.SALAD -> {
                val product = CropProduct(
                    crop.getName().toString(),
                    ProductType.VEGETABLES,
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
            CropName.CUCCUMBER -> return createFromCucumber(proccessor, crop)
            CropName.TOMATO -> return createFromTomato(proccessor, crop)
            CropName.ONION -> return createFromOnion(proccessor, crop)
            CropName.SUGARBEAT -> {
                val product = CropProduct(
                    "Suggar",
                    ProductType.BULKINGREDIENTS,
                    crop.getShopPrice() * 1.2,
                    crop.getShopPrice(),
                    (0.65 * crop.getAmount()).toInt(),
                    "kg",
                    crop.getUUID(),
                    crop.getState().changeToNextState()
                )
                product.attach(Report)
                product.notifyCreateProduct(proccessor)
                return product
            }
            else -> throw Exception("Wrong crop name input: " + crop.getName() + "Vegetable strategy")
        }
    }

    /**
     * Create from tomato
     *
     * @param crop
     * @return
     */
    private fun createFromTomato(proccessor : Processor, crop: Crop): Product {
        when ((1..4).random()) {
            1 -> {
                val product = CropProduct(
                    crop.getName().toString(),
                    ProductType.VEGETABLES,
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
            2 -> {
                val product = CropProduct(
                    "Ketchup",
                    ProductType.SAUCE,
                    crop.getShopPrice() * 1.2 + 25,
                    crop.getShopPrice() + 25,
                    (crop.getAmount() * 0.60).toInt(),
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
                    "Dried tomatoes",
                    ProductType.CANS,
                    crop.getShopPrice() * 1.2 + 15,
                    crop.getShopPrice() + 15,
                    (crop.getAmount() * 0.85).toInt(),
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
                    "Cropped tomatoes",
                    ProductType.CANS,
                    crop.getShopPrice() * 1.2 + 10,
                    crop.getShopPrice() + 10,
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
     * Create from cucumber
     *
     * @param crop
     * @return
     */
    private fun createFromCucumber(proccessor : Processor, crop: Crop): Product {
        when ((1..2).random()) {
            1 -> {
                val product = CropProduct(
                    crop.getName().toString(),
                    ProductType.VEGETABLES,
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
                val product = CropProduct(
                    "jar of sour" + crop.getName().toString(),
                    ProductType.VEGETABLES,
                    crop.getShopPrice() * 2.5,
                    crop.getShopPrice() + 20,
                    (crop.getAmount()*0.5).toInt(),
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
     * Create from onion
     *
     * @param crop
     * @return
     */
    private fun createFromOnion(proccessor : Processor, crop: Crop): Product {
        when ((1..2).random()) {
            1 -> {
                val product = CropProduct(
                    crop.getName().toString(),
                    ProductType.VEGETABLES,
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
                val product = CropProduct(
                    "Onion rings",
                    ProductType.ICED,
                    crop.getShopPrice() * 2.0,
                    crop.getShopPrice() + 10,
                    (0.9 * crop.getAmount()).toInt(),
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
     * Create from potato
     *
     * @param crop
     * @return
     */
    private fun createFromPotato(proccessor : Processor, crop: Crop): Product {
        when ((1..3).random()) {
            1 -> {
                val product = CropProduct(
                    crop.getName().toString(),
                    ProductType.VEGETABLES,
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
            2 -> {
                val product = CropProduct(
                    "Chips",
                    ProductType.OTHERS,
                    crop.getShopPrice() * 2.5,
                    crop.getShopPrice() + 15,
                    (0.75 * crop.getAmount()).toInt(),
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
                    "French fries",
                    ProductType.OTHERS,
                    crop.getShopPrice() * 1.8,
                    crop.getShopPrice() + 25,
                    (0.65 * crop.getAmount()).toInt(),
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