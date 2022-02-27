package cz.cvut.fel.omo.foodchain.Foodchain.visitor


/**
 * Export
 *
 * @constructor Create empty Export
 */
interface Export {
    /**
     * Accept
     *
     * @param visitor
     */
    fun accept(visitor : Visitor)
}