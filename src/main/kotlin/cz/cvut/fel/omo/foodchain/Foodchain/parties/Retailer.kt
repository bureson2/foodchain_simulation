package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product
import cz.cvut.fel.omo.foodchain.Foodchain.states.SpoiledState
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Config
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * Retailer
 *
 * @constructor
 *
 * @param subjectName
 * @param location
 * @param amountOfMoney
 */
class Retailer(subjectName: String, location: String, amountOfMoney: Double) :
    BaseParty(subjectName, location, amountOfMoney) {

    @Autowired
    private var warehouse: Warehouse = Warehouse()
    private var availableProducts: ArrayList<Product> = ArrayList()
    private var productTypeMap: HashMap<ProductType, Int> = productMapInit()

    private val logger = LoggerFactory.getLogger(javaClass)

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

    /**
     * Buy products
     *
     * @param products
     */
    fun buyProducts(products: ArrayList<Product>) {
        logger.info("Do warehouse privezeno: " + products.size)
        warehouse.takeIn(products)
        logger.info("Naskladneno celkem typu produktu: " + warehouse.getStockSize())
    }

    /**
     * Vacate warehouse
     *
     */
    fun vacateWarehouse() {
        for (product in warehouse.getStoragedProducts()) {
            var count: Int? = productTypeMap[product.getProductType()]
            count = count?.plus(product.getAmount())
            if (count != null) {
                productTypeMap[product.getProductType()] = count
            }
            availableProducts.add(product)
        }
    }

    /**
     * Fill in
     *
     */
    fun fillIn() {
        for (product in warehouse.getStoragedProducts()) {
            if (productMapInit()[product.getProductType()]!! < 10) {
                availableProducts.add(product)
                warehouse.getStoragedProducts().remove(product)
            }
        }
    }

    /**
     * Fill in
     *
     * @param type
     */
    fun fillIn(type: ProductType) {
        val newProducts: ArrayList<Product> = warehouse.getSpecificProducts(type)
        for (product in newProducts) {
            availableProducts.add(product)
        }
    }

    /**
     * Get stock size
     *
     * @return
     */
    fun getStockSize(): Int {
        return availableProducts.size
    }

    /**
     * Get warehouse stock size
     *
     * @return
     */
    fun getWarehouseStockSize(): Int {
        return warehouse.getStockSize()
    }

    /**
     * Get available products
     *
     * @return
     */
    fun getAvailableProducts() : ArrayList<Product>{
        return availableProducts
    }

    /**
     * Pay for warehouse management
     *
     */
    fun payForWarehouseManagement(){
        this.amountOfMoney -= warehouse.callForPayment()
    }

    /**
     * Refresh available products
     *
     * @param products
     */
    fun refreshAvailableProducts(products: ArrayList<Product>){
        this.availableProducts = products
    }

    /**
     * Check resources
     *
     */
    fun checkResources(){
        val toRemove : ArrayList<Product> = ArrayList()
        for(product : Product in availableProducts){
            product.increaseAge()
            logger.info(product.getName() + "se blizi datu spotreby")
            if(product.getAge() >= Config.TIME_OF_PRODUCT_LIFE){
                logger.info("" + product.getProductType() + " " + product.getName() + " se zkazil a byl vyhozen")
                product.notifySpoiled()
                product.setState(SpoiledState(product.getState().getContext(), product.getOriginId()))
                toRemove.add(product)
            }
        }
        removeProducts(availableProducts, toRemove)
    }

    /**
     * Remove products
     *
     * @param original
     * @param toRemove
     * @return
     */
    private fun removeProducts(original : ArrayList<Product>, toRemove : ArrayList<Product>) : ArrayList<Product>{
        for(product in toRemove){
            original.remove(product)
        }
        return original
    }


}