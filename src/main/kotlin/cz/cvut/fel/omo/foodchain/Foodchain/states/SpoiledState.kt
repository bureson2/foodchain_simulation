package cz.cvut.fel.omo.foodchain.Foodchain.states

import java.util.*

class SpoiledState (context: Context, code : UUID) : State(context, code) {
    override fun changeToNextState() : State {
        return SpoiledState(context, code)
    }
}