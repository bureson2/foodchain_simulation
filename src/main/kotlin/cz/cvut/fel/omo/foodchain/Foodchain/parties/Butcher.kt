package cz.cvut.fel.omo.foodchain.Foodchain.parties

import cz.cvut.fel.omo.foodchain.Foodchain.iterator.AnimalToProcess
import cz.cvut.fel.omo.foodchain.Foodchain.observer.Report
import cz.cvut.fel.omo.foodchain.Foodchain.animals.Animal
import cz.cvut.fel.omo.foodchain.Foodchain.enums.MeatType
import cz.cvut.fel.omo.foodchain.Foodchain.products.Meat

/**
 * Butcher
 *
 * @constructor Create empty Butcher
 */
class Butcher {


    /**
     * Proccess animal
     *
     * @param animals
     * @return
     */
    fun proccessAnimal(animals: AnimalToProcess): ArrayList<Meat> { //ITERATOR
        var meatList = ArrayList<Meat>()

        while (animals.hasNext()) {
            val animal: Animal = animals.next()
            when (animal.getName()) {
                "Cow" -> {
                    meatList = prepareMeat(meatList, animal, MeatType.BEEF)
                }

                "Pig" -> {
                    meatList = prepareMeat(meatList, animal, MeatType.PORK)
                }

                "Chicken" -> {
                    meatList = prepareMeat(meatList, animal, MeatType.CHICKEN)
                }

                "Fish" -> {
                    meatList = prepareMeat(meatList, animal, MeatType.FISH)
                }
            }
        }
        return meatList
    }

    private fun prepareMeat(meatList : ArrayList<Meat>, animal : Animal, type : MeatType) : ArrayList<Meat>{
        val shopPrice = 0.7 * animal.getWeight() * type.price
        val meat = Meat(
            type,
            shopPrice,
            shopPrice * 1.4,
            0.7 * animal.getWeight(),
            animal.getOriginId(), animal.getState().changeToNextState().changeToNextState()
        )
        meatList.add(meat)
        animal.notifyAnimalWasProcessed()
        meat.attach(Report)
        meat.notifyUpdate()
        return meatList
    }
}