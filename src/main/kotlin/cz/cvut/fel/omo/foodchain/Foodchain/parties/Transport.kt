package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.MeatProduct
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product
import org.slf4j.LoggerFactory
import kotlin.collections.ArrayList

/**
 * Transport
 *
 * @constructor Create empty Transport
 */
@Suppress("JAVA_CLASS_ON_COMPANION")
class Transport{

    companion object TransportCompany {

        private var amountOfMoney : Double = 10000.00
        var transport : BaseParty = BaseParty("Transport S.R.O", "Unknown", amountOfMoney)

        private val logger = LoggerFactory.getLogger(javaClass)

        private var cropSupplies : ArrayList<Crop> = ArrayList()
        private var products : ArrayList<Product> = ArrayList()
        private var meats : ArrayList<MeatProduct> = ArrayList()


        /**
         * Transport crop suplies
         *
         * @return
         */
        fun transportCropSuplies() : ArrayList<Crop>{
            val toTransport : ArrayList<Crop> = cropSupplies
            for(supply in cropSupplies){
                transport.changeAmountOfMoney((supply.getShopPrice() * supply.getAmount() * 0.1))
            }
            this.cropSupplies = ArrayList()
            return toTransport
        }

        /**
         * Transport products
         *
         * @return
         */
        fun transportProducts() : ArrayList<Product>{
            val toTransport : ArrayList<Product> = products
            for(product in products){
                transport.changeAmountOfMoney((product.getShopPrice() * product.getAmount() * 0.1))
            }
            this.products = ArrayList()
            return toTransport
        }

        /**
         * Transport meats
         *
         * @return
         */
        fun transportMeats() : ArrayList<MeatProduct>{
            val toTransport : ArrayList<MeatProduct> = meats
            for(meat in meats){
                transport.changeAmountOfMoney((meat.getShopPrice() * meat.getAmount() * 0.1))
            }
            this.meats = ArrayList()
            return toTransport
        }

        /**
         * Take crop supplies
         *
         * @param supplies
         */
        fun takeCropSupplies(supplies : ArrayList<Crop>){
            if(supplies.size == 0) return
            for(supply in supplies){
                this.cropSupplies.add(supply)
                supply.notifyTransport()
            }
        }

        /**
         * Take products
         *
         * @param products
         */
        fun takeProducts(products : ArrayList<Product>){
            for(product in products){
                this.products.add(product)
                product.notifyTransport()
            }
        }


        /**
         * Take meat
         *
         * @param meats
         */
        fun takeMeat(meats : ArrayList<MeatProduct>){
            logger.info("transport prebira masa : " + meats.size)
            for(meat in meats){
                this.meats.add(meat)
                meat.notifyTransport()
            }
            logger.info("Transport prevzal masa : " + this.meats.size)
        }


        /**
         * Cargo deduction
         *
         */
        fun cargoDeduction(){
            var costs = 0.0
            for(meat in meats){
                costs += meat.getShopPrice() * meat.getAmount() * 0.025
            }
            for(crop in cropSupplies){
                costs += crop.getShopPrice() * crop.getAmount() * 0.025
            }
            for(product in products){
                costs += product.getShopPrice() * product.getAmount() * 0.025
            }
            transport.changeAmountOfMoney(-costs)
            logger.info("Dopravni spolecnost na nakladech utratila: $costs")
        }
    }
}