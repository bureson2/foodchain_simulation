package cz.cvut.fel.omo.foodchain.Foodchain.states

import java.util.*

class ProductState(context: Context, code : UUID) : State(context, code) {
    override fun changeToNextState() : State {
        context.setState(SoldState(context, code))
        return SoldState(context, code)
    }
}