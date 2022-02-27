package cz.cvut.fel.omo.foodchain.Foodchain.visitor

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report
import org.slf4j.LoggerFactory
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * Export visitor
 *
 * @constructor Create empty Export visitor
 */
class ExportVisitor : Visitor{

    private var reports : HashMap<UUID, ArrayList<String>> = HashMap()
    private var financeReports : HashMap<UUID, ArrayList<String>> = HashMap()
    private var cropReports : HashMap<UUID, ArrayList<String>> = HashMap()
    private var animalReports : HashMap<UUID, ArrayList<String>> = HashMap()
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun visit(export: Report) {
        reports = export.getReports()
        proccessReports()

    }

    private fun proccessReports(){
        for(report in reports){
            if(report.value.toString().contains("INVOICE")){
                financeReports[report.key] = report.value
            } else if(report.value.toString().contains("ANIMAL")){
                animalReports[report.key] = report.value
            } else {
                cropReports[report.key] = report.value
            }
        }
        export()
    }

    private fun export() {
        val animalsFile = "src/main/resources/exportFoodChainAnimals.txt"
        val cropFile = "src/main/resources/exportFoodChainCrops.txt"
        val invoiceFile = "src/main/resources/exportFoodChainInvoices.txt"

        val fileA = File(animalsFile)
        val fileC = File(cropFile)
        val fileI = File(invoiceFile)

        writeInFile(fileA, animalReports)
        writeInFile(fileC, cropReports)
        writeInFile(fileI, financeReports)

        logger.info("Files were exported")
    }

    private fun writeInFile(file : File, reports : HashMap<UUID, ArrayList<String>> = HashMap()){
        file.printWriter().use { out ->
            reports.forEach { (key) ->
                with(out) { println("UUID: $key") }
                val reportList : ArrayList<String> = reports[key]!!
                for (j in reportList) {
                    out.println(j)
                }
                out.println("\n")
            }
        }
    }

    /**
     * Get animals
     *
     * @return
     */
    fun getAnimals() : HashMap<UUID, ArrayList<String>>{
        return animalReports
    }

    /**
     * Get crops
     *
     * @return
     */
    fun getCrops() : HashMap<UUID, ArrayList<String>>{
        return cropReports
    }

    /**
     * Get invoices
     *
     * @return
     */
    fun getInvoices() : HashMap<UUID, ArrayList<String>>{
        return financeReports
    }

}