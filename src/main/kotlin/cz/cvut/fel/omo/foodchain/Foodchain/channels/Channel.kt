package cz.cvut.fel.omo.foodchain.Foodchain.channels

/**
 * Channel
 *
 * @constructor Create empty Channel
 */
interface Channel {
    /**
     * Run simulation
     *
     */
    fun runSimulation()

    /**
     * Print stats
     *
     */
    fun printStats()
}