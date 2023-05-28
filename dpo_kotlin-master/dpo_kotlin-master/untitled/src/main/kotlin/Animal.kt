import kotlin.random.Random
import kotlin.random.nextInt

  open class Animal(
      var energy: Int,
      var weight: Int,
      var age: Int,
      val maxAge: Int,
      val name: String

 ) {


      val isTooOld: Boolean
          get() = age > maxAge



      open fun sleep() {
          energy += 5
          age += 1
          println("$name спит,энергия $energy,вес $weight,возраст $age,максимальный возраст $maxAge")

      }

      open fun eat() {
          energy += 3
          weight += 1
          age += Random.nextInt(0..1)
          println("$name жрет,энергия $energy,вес $weight,возраст $age,максимальный возраст $maxAge")
      }

      open fun move() {
          energy -= 5
          age += Random.nextInt(0..1)
          println("$name ходит туда сюда,энергия $energy,вес $weight,возраст $age,максимальный возраст $maxAge")
      }


      private fun tryIncreamenAge() {
          if (Random.nextBoolean()) this.age++
      }


      open fun giveBirth(): Animal {
          val energy = (1..10).random()
          val weight = (1..5).random()
          val maxAge = maxAge
          val name = name
          val newAnimal = Animal(energy, weight, age, maxAge,name)
          age = 0
          println("Родилось животное $name ,энергия $energy,вес $weight,возраст $age,максимальный возраст $maxAge")


          return newAnimal
      }
  }





















