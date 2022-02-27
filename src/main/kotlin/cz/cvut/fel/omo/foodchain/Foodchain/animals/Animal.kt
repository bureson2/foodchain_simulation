package cz.cvut.fel.omo.foodchain.Foodchain.animals

import cz.cvut.fel.omo.foodchain.Foodchain.observer.Observer
import cz.cvut.fel.omo.foodchain.Foodchain.observer.Subject
import cz.cvut.fel.omo.foodchain.Foodchain.states.AnimalState
import cz.cvut.fel.omo.foodchain.Foodchain.states.Context
import cz.cvut.fel.omo.foodchain.Foodchain.states.State
import cz.cvut.fel.omo.foodchain.Foodchain.statics.Week
import cz.cvut.fel.omo.foodchain.Foodchain.enums.AnimalType
import cz.cvut.fel.omo.foodchain.Foodchain.enums.CropType
import cz.cvut.fel.omo.foodchain.Foodchain.parties.Farmer
import java.util.*

/**
 * Animal
 *
 * @constructor
 *
 * @param animalType
 */
class Animal(animalType: AnimalType) : Subject, Context {

    private var observers: ArrayList<Observer> = ArrayList()

    private var weight: Double
    private var feed: CropType
    private var foodConsumption: Int
    private var age: Int
    private var price: Double
    private var animalName: String
    private var animalType: AnimalType
    private var origin: UUID = UUID.randomUUID()
    private var state : State

    init {
        this.animalType = animalType
        this.feed = CropType.CEREAL
        this.state = AnimalState(this, origin)

        when (animalType) {
            AnimalType.COW -> {
                this.weight = (150..600).random().toDouble()
                this.foodConsumption = (7..10).random()
                this.age = (1..30).random()
                this.price = 1000.00
                this.animalName = "Cow"
            }
            AnimalType.PIG -> {
                this.weight = (150..600).random().toDouble()
                this.foodConsumption = (6..10).random()
                this.age = (1..20).random()
                this.price = 8000.00
                this.animalName = "Pig"
            }
            AnimalType.CHICKEN -> {
                this.weight = (1..3).random().toDouble()
                this.foodConsumption = (1..3).random()
                this.age = (1..10).random()
                this.price = 100.00
                this.animalName = "Chicken"
            }
            AnimalType.FISH -> {
                this.weight = (1..6).random().toDouble()
                this.foodConsumption = (1..5).random()
                this.age = (1..5).random()
                this.price = 1000.00
                this.animalName = "Fish"
            }
        }
    }

    /**
     * Get age
     *
     * @return
     */
    fun getAge(): Int {
        return this.age
    }

    /**
     * Get weight
     *
     * @return
     */
    fun getWeight(): Double {
        return this.weight
    }

    /**
     * Get name
     *
     * @return
     */
    fun getName(): String {
        return this.animalName
    }

    /**
     * Get origin id
     *
     * @return
     */
    fun getOriginId(): UUID {
        return this.origin
    }

    /**
     * Increase weight
     *
     */
    fun increaseWeight() {
        this.weight *= 1.1
    }

    /**
     * Decrease weight
     *
     */
    fun decreaseWeight() {
        this.weight *= 0.9
    }

    /**
     * Grow animal
     *
     */
    fun growAnimal() {
        this.age++
    }

    override fun attach(o: Observer) {
        observers.add(o)
    }

    override fun detach(o: Observer) {
        observers.remove(o)
    }

    override fun notifyUpdate() {
        for (i in observers) {
            i.update(
                this.origin,
                "NEW ANIMAL, " + this.animalName + ", weight: " + this.weight + "kg, age:" + this.age + " in week:" + Week.acutalWeek
            )
        }
    }

    /**
     * Notify animal was move to process
     *
     */
    fun notifyAnimalWasMoveToProcess(farmer : Farmer){
        for (i in observers) {
            i.update(
                this.origin,
                "ANIMAL HAS BEEN MOVED TO PROCESS IN WEEK: " + Week.acutalWeek + " by " + farmer.getIdentifier()
            )
        }
    }

    /**
     * Notify animal was processed
     *
     */
    fun notifyAnimalWasProcessed(){
        for (i in observers) {
            i.update(
                this.origin,
                "ANIMAL HAS BEEN PROCESSED IN WEEK: " + Week.acutalWeek
            )
        }
    }


    /**
     * Set state
     *
     * @param state
     */
    override fun setState(state: State) {
        this.state = state
    }

    /**
     * Get state
     *
     * @return
     */
    fun getState() : State{
        return state
    }
}