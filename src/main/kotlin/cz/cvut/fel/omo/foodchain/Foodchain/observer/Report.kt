package cz.cvut.fel.omo.foodchain.Foodchain.observer


import cz.cvut.fel.omo.foodchain.Foodchain.visitor.Export
import cz.cvut.fel.omo.foodchain.Foodchain.visitor.ExportVisitor
import cz.cvut.fel.omo.foodchain.Foodchain.visitor.Visitor
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


object Report : Observer, Export {

    private val visitor = ExportVisitor()

    override fun accept(visitor: Visitor) = visitor.visit(export = this)

    fun prepareForExport() {
        accept(visitor)
    }

    private var reports = hashMapOf<UUID, ArrayList<String>>()


    override fun update(uuid: UUID, report: String) {
        if (!reports.containsKey(uuid)) {
            val newReport: ArrayList<String> = ArrayList()
            newReport.add(report)
            reports[uuid] = newReport
        } else if(reports.containsKey(uuid)) {
            reports[uuid]?.add(report)
        }
    }

    fun getReports(): HashMap<UUID, ArrayList<String>> {
        return reports
    }

    fun getAnimals() : HashMap<UUID, ArrayList<String>>{
        return visitor.getAnimals()
    }

    fun getInvoices() : HashMap<UUID, ArrayList<String>>{
        return visitor.getInvoices()
    }

    fun getCrops() : HashMap<UUID, ArrayList<String>>{
        return visitor.getCrops()
    }


}