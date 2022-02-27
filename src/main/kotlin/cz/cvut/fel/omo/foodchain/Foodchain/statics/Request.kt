package cz.cvut.fel.omo.foodchain.Foodchain.statics

import cz.cvut.fel.omo.foodchain.Foodchain.Invoice
import cz.cvut.fel.omo.foodchain.Foodchain.factory.MeatFactory
import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropType
import cz.cvut.fel.omo.foodchain.Foodchain.enums.InvoiceType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.*
import cz.cvut.fel.omo.foodchain.Foodchain.products.Crop
import cz.cvut.fel.omo.foodchain.Foodchain.products.Meat
import cz.cvut.fel.omo.foodchain.Foodchain.products.MeatProduct
import cz.cvut.fel.omo.foodchain.Foodchain.products.Product
import cz.cvut.fel.omo.foodchain.Foodchain.strategies.product_strategy.MeatStrategy
import org.slf4j.LoggerFactory

/**
 * Request
 *
 * @constructor Create empty Request
 */
class Request {
    companion object RequestService {

        private val logger = LoggerFactory.getLogger(javaClass)

        fun requestTransportToProcessor(grower: Grower, processor: Processor, crops: ArrayList<Crop>) {
            logger.info("Proces transportace (from farmer " + grower.getIdentifier() + " to processor) " + processor.getIdentifier())
            var money = 0.0
            for (crop in crops) {
                money += crop.getAmount() * crop.getShopPrice()
            }

            grower.transportSupplies()
            Transport.cargoDeduction()
            grower.raiseField()

            if(money > 0){
                val invoice = Invoice(processor, grower, money, InvoiceType.CROP)
                proccesTheInvoice(invoice, processor)

                val transportInvoice = Invoice(processor, Transport.transport, money * Config.TRANSPORT_TAX, InvoiceType.TRANSPORT)
                proccesTheInvoice(transportInvoice, processor)
            }

            processor.takeSupplies(Transport.transportCropSuplies())
        }

        fun requestTransportToMeatFactory(farmer: Farmer, meatFactory: MeatFactory) {
            logger.info("Proces transportace (from farmer " + farmer.getIdentifier() + " to processor) " + meatFactory.getIdentifier())

            // Poslani zvirat na jatka
            val processedAnimals: ArrayList<Meat> = farmer.callButcher() // pouze zabyte, musi se rozdelit na jednotlive casti jeste
            logger.info("Na jatka poslano : " + processedAnimals.size)

            // Faktura
            var money = 0.0
            for (animal in processedAnimals) {
                money += animal.getAmount() * animal.getShopPrice()
            }

            val processedMeat =  ArrayList<MeatProduct>()

            val meatStrategy = MeatStrategy()

            for(meat in processedAnimals){
                processedMeat.addAll(meatStrategy.execute(meatFactory, meat))
            }

            Transport.takeMeat(processedMeat)
            Transport.cargoDeduction()
            meatFactory.takeMeat(Transport.transportMeats())

            if(money > 0){
                val invoice = Invoice(meatFactory, farmer, money, InvoiceType.MEAT)
                proccesTheInvoice(invoice, meatFactory)

                val transportInvoice = Invoice(meatFactory, Transport.transport, money * Config.TRANSPORT_TAX, InvoiceType.TRANSPORT)
                proccesTheInvoice(transportInvoice, meatFactory)
            }
        }

        fun requestTransportToWarehouse(processor: Processor, retailer: Retailer) {
            logger.info("Proces transportace (from processor " + processor.getIdentifier() + " to retailer " + retailer.getIdentifier())

            processor.transportProducts()
            Transport.cargoDeduction()
            val transportedProducts : ArrayList<Product> = Transport.transportProducts()

            // Faktura
            var money = 0.0

            for (product in transportedProducts) {
                money += product.getAmount() * product.getShopPrice()
            }
            if(money > 0){
                val invoice = Invoice(retailer, processor, money, InvoiceType.PRODUCT)
                proccesTheInvoice(invoice, retailer)

                val transportInvoice = Invoice(retailer, Transport.transport, money * Config.TRANSPORT_TAX, InvoiceType.TRANSPORT)
                proccesTheInvoice(transportInvoice, retailer)
            }

            retailer.buyProducts(transportedProducts)
        }

        fun requestTransportToWarehouse(meatFactory: MeatFactory, retailer: Retailer) {
            logger.info("Proces transportace (from Vodnany  to retailer " + retailer.getIdentifier())

            meatFactory.transportProducts()
            Transport.cargoDeduction()
            val transportedProducts : ArrayList<Product> = Transport.transportProducts()

            // Faktura
            var money = 0.0
            for (product in transportedProducts){
                money += product.getAmount() * product.getShopPrice()
            }
            if(money > 0){
                val invoice = Invoice(retailer, meatFactory, money, InvoiceType.PRODUCT)
                proccesTheInvoice(invoice, retailer)

                val transportInvoice = Invoice(retailer, Transport.transport, money * Config.TRANSPORT_TAX, InvoiceType.TRANSPORT)
                proccesTheInvoice(transportInvoice, retailer)
            }
            retailer.buyProducts(transportedProducts)
        }

        fun requestFarmerBuyCrops(farmer: Farmer, grower: Grower){
            logger.info("Farmer: " + farmer.getIdentifier() + " nakupuje zasoby od " + grower.getIdentifier())

            var money = 0.0

            for(crop in grower.getSupplies()){
                if(crop.getType() == CropType.CEREAL){
                    money += crop.getAmount() * crop.getShopPrice()
                    farmer.takeResources(crop)
                    grower.getSupplies().remove(crop)
                    break
                }
            }
            if (money > 0) {
                val invoice = Invoice(farmer, grower, money, InvoiceType.PRODUCT)
                proccesTheInvoice(invoice, farmer)
            }
        }

        fun requestProccessMeat(meatFactory: MeatFactory){
            meatFactory.packageProduct()
        }

        fun requestGoShopping(retailer : Retailer, customer: Customer){
            val pair : Pair<Double, ArrayList<Product>> = customer.buyProducts(retailer.getAvailableProducts())
            val money : Double = pair.first
            val products : ArrayList<Product> = pair.second

            retailer.refreshAvailableProducts(products)

            // Faktura
            if(money > 0){
                val invoice = Invoice(customer, retailer, money, InvoiceType.SHOPPING)
                proccesTheInvoice(invoice, customer)
            }
        }

        fun requestTakeOutToShop(retailer: Retailer){
            retailer.fillIn()
        }

        private fun proccesTheInvoice(invoice : Invoice, party : BaseParty){
            Invoices.invoices.add(invoice)
            invoice.attach(Report)
            invoice.notifyUpdate()
            logger.info("Customer: " + party.getIdentifier() + " utratil: " + invoice.getPrice() + " za nakupy a ma: " + party.getAmountOfMoney())
            party.payForInvoice(invoice)
        }
    }
}