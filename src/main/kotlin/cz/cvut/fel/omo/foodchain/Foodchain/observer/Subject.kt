package cz.cvut.fel.omo.foodchain.Foodchain.observer

import cz.cvut.fel.omo.foodchain.Foodchain.parties.BaseParty

/**
 * Subject
 *
 * @constructor Create empty Subject
 */
interface Subject {
    /**
     * Attach
     *
     * @param o
     */
    fun attach(o: Observer)

    /**
     * Detach
     *
     * @param o
     */
    fun detach(o: Observer)

    /**
     * Notify update
     *
     */
    fun notifyUpdate()
}