package cz.cvut.fel.omo.foodchain.Foodchain.states

import java.util.*

abstract class State(protected var context: Context, protected var code: UUID) {

    abstract fun changeToNextState() : State

    @JvmName("Context")
    fun getContext() : Context{
        return context
    }
}