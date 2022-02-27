package cz.cvut.fel.omo.foodchain.Foodchain.states

import java.util.*

class SoldState(context: Context, code : UUID) : State(context, code) {
    override fun changeToNextState() : State {
        return SoldState(context, code)
    }
}