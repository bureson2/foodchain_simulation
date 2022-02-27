package cz.cvut.fel.omo.foodchain.Foodchain

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Config
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@SpringBootApplication
@Controller
class FoodchainApplication {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping(value = ["/start"])
    fun start(
        @RequestParam(value = "WEEKS") WEEKS: Int,
        @RequestParam(value = "GROWERS") GROWERS: Int,
        @RequestParam(value = "FARMERS") FARMERS: Int,
        @RequestParam(value = "PROCESSORS") PROCESSORS: Int,
        @RequestParam(value = "CUSTOMERS") CUSTOMERS: Int,
        @RequestParam(value = "RETAILERS") RETAILERS: Int,
        @RequestParam(value = "STANDARD_SHOP_SIZE") STANDARD_SHOP_SIZE: Int,
        @RequestParam(value = "VEGAN_SHOP_SIZE") VEGAN_SHOP_SIZE: Int,
        @RequestParam(value = "WORKOUT_SHOP_SIZE") WORKOUT_SHOP_SIZE: Int,
        model : Model
    ) {
        println("start")

        Config.WEEKS = WEEKS
        Config.GROWERS = GROWERS
        Config.FARMERS = FARMERS
        Config.PROCESSORS = PROCESSORS
        Config.CUSTOMERS = CUSTOMERS
        Config.RETAILERS = RETAILERS
        Config.STANDARD_SHOP_SIZE = STANDARD_SHOP_SIZE
        Config.VEGAN_SHOP_SIZE = VEGAN_SHOP_SIZE
        Config.WORKOUT_SHOP_SIZE = WORKOUT_SHOP_SIZE

        val simulation = Simulation()

        for (i in (1..Config.WEEKS)) {
            logger.info("$i. WEEK")
            simulation.runWeek()
        }

        /* Report.export()*/
        Report.prepareForExport()
        model.addAttribute("reports", Report.getReports())
    }

    @RequestMapping(value = ["/invoices"])
    fun invoices(model : Model) {
        model.addAttribute("invoices", Report.getInvoices())
    }

    @RequestMapping(value = ["/animals"])
    fun animals(model : Model) {
        model.addAttribute("animals", Report.getAnimals())
    }

    @RequestMapping(value = ["/crops"])
    fun crops(model : Model) {
        model.addAttribute("crops", Report.getCrops())
    }
}


fun main(args: Array<String>) {
    runApplication<FoodchainApplication>(*args)
}