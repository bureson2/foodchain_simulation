package cz.cvut.fel.omo.foodchain.Foodchain.visitor

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report

/**
 * Visitor
 *
 * @constructor Create empty Visitor
 */
interface Visitor {
    /**
     * Visit
     *
     * @param export
     */
    fun visit(export : Report)
}