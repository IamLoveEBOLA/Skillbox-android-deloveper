import kotlin.random.Random
import kotlin.random.nextInt

class Bird(energy: Int, weight: Int, age: Int, maxAge: Int, name: String) : Animal(energy, weight, age, maxAge, name) {
     override fun move() {
          energy -= 5
          age += Random.nextInt(0..1)
          println("$name Летит туда сюда, энергия $energy,вес $weight,возраст $age,максимальный возраст $maxAge")
     }

     override fun giveBirth(): Bird {
          val energy = (1..10).random()
          val weight = (1..5).random()
          val maxAge = maxAge
          val name = name
          val newBird = Bird( energy, weight, age, maxAge,name)
          age = 0
          println("Вылупился птенец $name,энергия $energy,вес $weight,возраст $age,максимальный возраст $maxAge")


          return newBird
     }
}












