package sk.upjs.ics.kopr

import kotlin.random.Random

class CarRandomizer(private val random: Random = Random.Default) {
    private val segmentA = listOf(
        car("Chevrolet Spark", CarSegment.A),
        car("Fiat 500", CarSegment.A),
        car("Kia Picanto", CarSegment.A),
        car("Renault Twingo", CarSegment.A)
    )

    private val segmentC = listOf(
        car("VW Golf", CarSegment.C),
        car("Honda Civic", CarSegment.C),
        car("Toyota Corolla", CarSegment.C),
        car("Mazda 3", CarSegment.C)
    )

    private val segmentJ = listOf(
        car("Toyota RAV4", CarSegment.J),
        car("Honda CR-V", CarSegment.J),
        car("Kia Sportage", CarSegment.J),
        car("VW Tiguan", CarSegment.J)
    )

    private val inventory: MutableMap<CarSegment, List<Car>> = LinkedHashMap()

    init {
        inventory[CarSegment.A] = segmentA
        inventory[CarSegment.C] = segmentC
        inventory[CarSegment.J] = segmentJ
    }

    fun randomCar(): Car {
        val randomSegment = CarSegment.values().random(random)
        val cars = inventory[randomSegment] ?: emptyList()
        return cars.random(random)
    }

    private fun car(specification: String, segment: CarSegment): Car {
        val components = specification.split(" ")
        require(components.size == 2) { "Cannot parse car specification. Expecting 2 arguments in: '$specification'" }
        return Car(components[0], components[1], segment)
    }
}