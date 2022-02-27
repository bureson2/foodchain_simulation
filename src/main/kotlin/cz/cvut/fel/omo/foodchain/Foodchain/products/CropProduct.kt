package cz.cvut.fel.omo.foodchain.Foodchain.products

import cz.cvut.fel.omo.foodchain.Foodchain.states.State
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import java.util.*

/**
 * Crop product
 *
 * @property name
 * @property type
 * @property shopPrice
 * @property productionCost
 * @property amount
 * @property unit
 * @property origin
 * @constructor Create empty Crop product
 */
class CropProduct(
    private var name: String,
    private var type: ProductType,
    private var shopPrice: Double,
    private var productionCost: Double,
    private var amount: Int,
    private var unit: String,
    private var origin: UUID,
    state : State
) :  Product(name, shopPrice, productionCost, amount, unit, origin, state) {

    override fun getName() : String{
        return this.name
    }

    override fun getAmount(): Int {
        return this.amount
    }

    override fun getProductType(): ProductType {
        return this.type
    }

    override fun getShopPrice(): Double {
        return shopPrice
    }

    override fun getOriginId(): UUID {
        return this.origin
    }

    override fun decreaseAmount(value : Int){
        this.amount = this.amount - value
    }
}
