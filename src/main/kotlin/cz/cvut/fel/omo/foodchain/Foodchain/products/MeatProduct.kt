package cz.cvut.fel.omo.foodchain.Foodchain.products

import cz.cvut.fel.omo.foodchain.Foodchain.states.State
import cz.cvut.fel.omo.foodchain.Foodchain.enums.ProductType
import java.util.*

/**
 * Meat product
 *
 * @property name
 * @property typeOfMeat
 * @property productType
 * @property shopPrice
 * @property productionCost
 * @property amount
 * @property unit
 * @property origin
 * @constructor Create empty Meat product
 */
class MeatProduct(
    private var name: String,
    private var typeOfMeat: String,
    private var productType: ProductType,
    private var shopPrice: Double,
    private var productionCost: Double,
    private var amount: Int,
    private var unit: String,
    private var origin: UUID,
    state : State
) : Product(name, shopPrice, productionCost, amount, unit, origin, state) {

    override fun getAmount(): Int {
        return amount
    }

    override fun getProductType(): ProductType {
        return this.productType
    }

    override fun getShopPrice(): Double {
        return shopPrice
    }

    override fun getOriginId(): UUID {
        return this.origin
    }

    override fun getName() : String{
        return this.name
    }

    override fun decreaseAmount(value : Int){
        this.amount = this.amount - value
    }

    fun getTypeOfMeat() : String {
        return this.typeOfMeat
    }
}
