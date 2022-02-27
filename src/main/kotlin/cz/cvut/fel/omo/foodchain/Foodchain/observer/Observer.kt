package cz.cvut.fel.omo.foodchain.Foodchain.observer

import java.util.*

/**
 * Observer
 *
 * @constructor Create empty Observer
 */
interface Observer {
    /**
     * Update
     *
     * @param uuid
     * @param report
     */
    fun update(uuid: UUID, report: String)
}