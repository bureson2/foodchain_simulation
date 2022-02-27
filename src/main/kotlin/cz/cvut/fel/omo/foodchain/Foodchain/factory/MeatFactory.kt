package cz.cvut.fel.omo.foodchain.Foodchain.factory

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report
import cz.cvut.fel.omo.foodchain.Foodchain.enums.FishType
import cz.cvut.fel.omo.foodchain.Foodchain.enums.MeatProductType
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.BaseParty
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Transport
import cz.cvut.fel.omo.foodchain.Foodchain.products.MeatProduct
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product
import org.slf4j.LoggerFactory

/**
 * Meat factory
 *
 * @constructor
 *
 * @param subjectName
 * @param location
 * @param amountOfMoney
 */
class MeatFactory(subjectName: String, location: String, amountOfMoney: Double) :
    BaseParty(subjectName, location, amountOfMoney) {

    private var meatsForProducts: ArrayList<MeatProduct> = ArrayList()
    private var productToSell: ArrayList<Product> = ArrayList()

    private val logger = LoggerFactory.getLogger(javaClass)

    /**
     * Get meat resources
     *
     * @return
     */
    fun getMeatResources(): Int {
        return meatsForProducts.size
    }

    /**
     * Get prepared porducts
     *
     * @return
     */
    fun getPreparedPorducts(): Int {
        return productToSell.size
    }

    /**
     * Take meat
     *
     * @param newMeats
     */
    fun takeMeat(newMeats: ArrayList<MeatProduct>) {
        for (meat in newMeats) {
            meatsForProducts.add(meat)
        }
        logger.info("Prevzato masa k zabaleni: " + meatsForProducts.size)
    }

    /**
     * Transport products
     *
     */
    fun transportProducts() {
        Transport.takeProducts(productToSell)
        productToSell = ArrayList()
    }

    /**
     * Package product
     *
     */
    fun packageProduct() {
        productToSell = ArrayList()
        for (meat in meatsForProducts) {
            logger.info("Maso puvodu : " + meat.getOriginId() + " je baleno")
            when (meat.getTypeOfMeat()) {
                MeatProductType.BEEFDUMPLING.toString() -> productToSell.add(packageBeefDumpling(meat))
                MeatProductType.BEEFSHOULDER.toString() -> productToSell.add(packageBeefShoulder(meat))
                MeatProductType.BEEFTENDERLOIN.toString() -> productToSell.add(packageBeefTenderloin(meat))
                MeatProductType.BOVINECHEEK.toString() -> productToSell.add(packageBovineCheek(meat))
                MeatProductType.PORKFLANK.toString() -> productToSell.add(packagePorkFlank(meat))
                MeatProductType.PORKDUMPLING.toString() -> productToSell.add(packagePorkDumpling(meat))
                MeatProductType.PORKLEG.toString() -> {
                    for (i in 1..4) {
                        productToSell.add(packagePorkLeg(meat))
                    }
                }
                MeatProductType.PORKROAST.toString() -> productToSell.add(packagePorkRoast(meat))
                MeatProductType.CHICKENBREAST.toString() -> productToSell.add(packageChickenBreast(meat))
                MeatProductType.CHICKENTHIGH.toString() -> productToSell.add(packageChickenThigh(meat))
                MeatProductType.CHICKENWINGS.toString() -> {
                    for (i in 1..2) {
                        productToSell.add(packageChickenWings(meat))
                    }
                }
                FishType.CARP.toString() -> productToSell.add(packageCarp(meat))
                FishType.BREAM.toString() -> productToSell.add(packageBream(meat))
                FishType.EEL.toString() -> productToSell.add(packageEel(meat))
                FishType.ZANDER.toString() -> productToSell.add(packageZander(meat))
                FishType.CATFISH.toString() -> productToSell.add(packageCatfish(meat))
                FishType.PERCH.toString() -> productToSell.add(packagePerch(meat))
            }
        }
        meatsForProducts.clear()
    }

    /**
     * Package pork dumpling
     *
     * @param meat
     * @return
     */
    private fun packagePorkDumpling(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Pork Dumpling 300g",
            MeatProductType.PORKDUMPLING.toString(),
            ProductType.MEAT,
            150.0,
            180.0,
            300,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package pork roast
     *
     * @param meat
     * @return
     */
    private fun packagePorkRoast(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Pork Roast 300g",
            MeatProductType.PORKROAST.toString(),
            ProductType.MEAT,
            160.0,
            170.0,
            300,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package pork leg
     *
     * @param meat
     * @return
     */
    private fun packagePorkLeg(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Pork Leg 400g",
            MeatProductType.PORKLEG.toString(),
            ProductType.MEAT,
            180.0,
            200.0,
            400,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package pork flank
     *
     * @param meat
     * @return
     */
    private fun packagePorkFlank(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Pork Flank 200g",
            MeatProductType.PORKFLANK.toString(),
            ProductType.MEAT,
            180.0,
            200.0,
            200,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package beef tenderloin
     *
     * @param meat
     * @return
     */
    private fun packageBeefTenderloin(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Beef TenderLoin 400g",
            MeatProductType.BEEFTENDERLOIN.toString(),
            ProductType.MEAT,
            280.0,
            330.0,
            400,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package bovine cheek
     *
     * @param meat
     * @return
     */
    private fun packageBovineCheek(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Bovine Cheek 300g",
            MeatProductType.BOVINECHEEK.toString(),
            ProductType.MEAT,
            220.0,
            270.0,
            300,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package beef shoulder
     *
     * @param meat
     * @return
     */
    private fun packageBeefShoulder(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Beef Shoulder 300g",
            MeatProductType.BEEFSHOULDER.toString(),
            ProductType.MEAT,
            220.0,
            270.0,
            300,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package beef dumpling
     *
     * @param meat
     * @return
     */
    private fun packageBeefDumpling(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Beef Dumpling 300g",
            MeatProductType.BEEFDUMPLING.toString(),
            ProductType.MEAT,
            220.0,
            270.0,
            300,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package chicken thigh
     *
     * @param meat
     * @return
     */
    private fun packageChickenThigh(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Chicken Thigh 100g",
            MeatProductType.CHICKENTHIGH.toString(),
            ProductType.MEAT,
            90.0,
            110.0,
            100,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package chicken breast
     *
     * @param meat
     * @return
     */
    private fun packageChickenBreast(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Chicken Breast 150g",
            MeatProductType.CHICKENBREAST.toString(),
            ProductType.MEAT,
            120.0,
            160.0,
            150,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package chicken wings
     *
     * @param meat
     * @return
     */
    private fun packageChickenWings(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Chicken Wings 150g",
            MeatProductType.CHICKENWINGS.toString(),
            ProductType.MEAT,
            120.0,
            160.0,
            150,
            "g",
            meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package carp
     *
     * @param meat
     * @return
     */
    private fun packageCarp(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Carp 750g", FishType.CARP.toString(), ProductType.MEAT, 420.0, 560.0, 750, "g", meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package bream
     *
     * @param meat
     * @return
     */
    private fun packageBream(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Bream 750g", FishType.BREAM.toString(), ProductType.MEAT, 400.0, 540.0, 750, "g", meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package eel
     *
     * @param meat
     * @return
     */
    private fun packageEel(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Eel 800g", FishType.EEL.toString(), ProductType.MEAT, 500.0, 640.0, 800, "g", meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package zander
     *
     * @param meat
     * @return
     */
    private fun packageZander(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Zander 600g", FishType.ZANDER.toString(), ProductType.MEAT, 500.0, 640.0, 600, "g", meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package catfish
     *
     * @param meat
     * @return
     */
    private fun packageCatfish(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Catfish 600g", FishType.CATFISH.toString(), ProductType.MEAT, 500.0, 640.0, 600, "g", meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }

    /**
     * Package perch
     *
     * @param meat
     * @return
     */
    private fun packagePerch(meat: MeatProduct): MeatProduct {
        val product = MeatProduct(
            "Perch 600g", FishType.PERCH.toString(), ProductType.MEAT, 500.0, 640.0, 600, "g", meat.getOriginId(),
            meat.getState().changeToNextState()
        )
        product.attach(Report)
        product.notifyCreateProduct(this)
        return product
    }
}