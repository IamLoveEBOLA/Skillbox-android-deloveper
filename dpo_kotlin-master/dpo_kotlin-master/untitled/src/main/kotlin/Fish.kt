import kotlin.random.Random
import kotlin.random.nextInt

open class Fish(energy: Int, weight: Int, age: Int, maxAge: Int, name: String) : Animal(energy, weight, age, maxAge, name) {
    override fun move() {
        energy -= 5
        age += Random.nextInt(0..1)
        println("$name Плаает туда сюда,энергия $energy,вес $weight,возраст $age,максимальный возраст $maxAge")
    }

    override fun giveBirth(): Fish {
        val energy = (1..10).random()
        val weight = (1..5).random()
        val maxAge = maxAge
        val name = name
        val newFish = Fish(energy, weight, age, maxAge,name)
        age = 0
        println("Рыба родилась из икры $name,энергия $energy,вес $weight,возраст $age,максимальный возраст $maxAge")


        return newFish
    }
}










