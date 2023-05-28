import kotlin.random.Random
import kotlin.random.nextInt

class Dog(energy: Int, weight: Int, age: Int, maxAge: Int, name: String) : Animal(energy, weight, age, maxAge, name) {







   override fun move() {
        energy -= 5
        age += Random.nextInt(0..1)
        println("$name Бежит туда сюда ,энергия $energy,вес $weight,возраст $age,максимальный возраст $maxAge")
    }

    override fun giveBirth(): Dog {
        val energy = (1..10).random()
        val weight = (1..5).random()
        val maxAge = maxAge
        val name = name
        val newDog = Dog(energy, weight, age, maxAge, name)
        age = 0
        println("Песель новый появился на свет $name,энергия $energy,вес $weight,возраст $age,максимальный возраст $maxAge")


        return newDog

    }
}




















