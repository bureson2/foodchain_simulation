package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.strategies.product_strategy.*
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropType
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product

/**
 * Processor
 *
 * @constructor
 *
 * @param subjectName
 * @param location
 * @param amountOfMoney
 */
class Processor(subjectName : String, location : String, amountOfMoney : Double)
    : BaseParty(subjectName,  location, amountOfMoney) {

    private val cerealStrategy : CerealCropStrategy = CerealCropStrategy()
    private val flowerStrategy : FlowerCropStrategy = FlowerCropStrategy()
    private val fruitStrategy : FruitCropStrategy = FruitCropStrategy()
    private val legumesStrategy : LegumesCropStrategy = LegumesCropStrategy()
    private val vegetableStrategy : VegetableCropStrategy = VegetableCropStrategy()

    private var cropSupplies : ArrayList<Crop> = ArrayList()
    private var products : ArrayList<Product> = ArrayList()

    /**
     * Create product
     *
     * @param crop
     * @return
     */
    private fun createProduct(crop : Crop) : Product {
        val context : CropContext
        when(crop.getType()){
            CropType.CEREAL -> {
                context = CropContext(cerealStrategy)
                return context.processProduct(this, crop)
            }
            CropType.FRUIT -> {
                context = CropContext(fruitStrategy)
                return context.processProduct(this, crop)
            }
            CropType.VEGETABLE -> {
                context = CropContext(vegetableStrategy)
                return context.processProduct(this, crop)
            }
            CropType.LEGUMES -> {
                context = CropContext(legumesStrategy)
                return context.processProduct(this, crop)
            }
            CropType.FLOWER -> {
                context = CropContext(flowerStrategy)
                return context.processProduct(this, crop)
            }
            else -> throw Exception("Wrong crop type")
        }
    }

    /**
     * Process product
     *
     */
    fun processProduct(){
        for(supply in cropSupplies){
            products.add(createProduct(supply))
        }
        cropSupplies = ArrayList()
    }

    /**
     * Take crop supplies
     *
     * @param supplies
     */
    fun takeSupplies(supplies : ArrayList<Crop>){
        for(supply in supplies){
            cropSupplies.add(supply)
        }
    }

    /**
     * Transport products
     *
     */
    fun transportProducts(){
        Transport.takeProducts(products)
        products = ArrayList()
    }

    /**
     * Get stock supplies size
     *
     * @return
     */
    fun getStockSuppliesSize() : Int{
        return cropSupplies.size
    }

    /**
     * Get stock products size
     *
     * @return
     */
    fun getStockProductsSize() : Int{
        return products.size
    }
}