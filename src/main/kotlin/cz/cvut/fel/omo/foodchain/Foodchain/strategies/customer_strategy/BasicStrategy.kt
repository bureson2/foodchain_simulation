package cz.cvut.fel.omo.foodchain.Foodchain.strategies.customer_strategy

import cz.cvut.fel.omo.foodchain.Foodchain.statics.Config
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Customer
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product
import org.slf4j.LoggerFactory

/**
 * Basic strategy
 *
 * @constructor Create empty Basic strategy
 */
class BasicStrategy : CustomerStrategy {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun execute(customer: Customer, products: ArrayList<Product>): Pair<Double, ArrayList<Product>> {
        var spended = 0.0

        val productTypeMap: HashMap<ProductType, Int> = productMapInit()
        val toRemove: ArrayList<Product> = ArrayList()

        for (product in products) {
            if (productTypeMap[product.getProductType()]!! < Config.STANDARD_SHOP_SIZE) {
                if (product.getAmount() >= Config.STANDARD_SHOP_SIZE) {
                    product.decreaseAmount(Config.STANDARD_SHOP_SIZE)
                    spended += Config.STANDARD_SHOP_SIZE * product.getShopPrice()
                    product.notifyPurchased(customer, Config.STANDARD_SHOP_SIZE);
                } else {
                    logger.info("" + product.getProductType() + " " + product.getName() + " byl vyprodan")
                    toRemove.add(product)
                    product.getState().changeToNextState()
                    product.notifySoldOut()
                }
            }
        }
        val productsToReturn = removeProducts(products, toRemove)
        return Pair(spended, productsToReturn)
    }

    /**
     * Remove products
     *
     * @param original
     * @param toRemove
     */
    private fun removeProducts(original: ArrayList<Product>, toRemove: ArrayList<Product>): ArrayList<Product> {
        for (product in toRemove) {
            original.remove(product)
        }
        return original
    }

    /**
     * Product map init
     *
     * @return
     */
    private fun productMapInit(): HashMap<ProductType, Int> {
        val prepareMap: HashMap<ProductType, Int> = HashMap()
        prepareMap[ProductType.CEREALS] = 0
        prepareMap[ProductType.FRUIT] = 0
        prepareMap[ProductType.VEGETABLES] = 0
        prepareMap[ProductType.LEGUMES] = 0
        prepareMap[ProductType.BULKINGREDIENTS] = 0
        prepareMap[ProductType.OTHERS] = 0
        prepareMap[ProductType.SAUCE] = 0
        prepareMap[ProductType.ICED] = 0
        prepareMap[ProductType.CANS] = 0
        prepareMap[ProductType.OIL] = 0
        prepareMap[ProductType.XXX] = 0
        prepareMap[ProductType.MEAT] = 0
        prepareMap[ProductType.DRINK] = 0
        prepareMap[ProductType.ALCOHOL] = 0
        return prepareMap
    }
}