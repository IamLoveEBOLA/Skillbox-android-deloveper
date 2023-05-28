import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.exitProcess

class NatureReserve{
     var animalZoo = mutableListOf<Animal>(
       Bird(100,6,3,5, "Неизвестная птица"),
        Bird(10,15,5,15,"КакаДемон"),
       Dog(100,5,6,10,"Скуби-ду"),
       Fish(100,10,1,3,"Анчоус"),
        Animal(1000,10,2,5,"Пикачу"),
         Fish(10,1,1,3,"Немо рыба"),
         Animal(100,56,16,65,"Человек-паук"),
         Dog(100,35,1,3,"Робопёс"),
         Bird(100,200,1,5,"Бомбадировщик F-16"),
         Bird(10,1,1,4,"Дядел"),
         Dog(30,35,1,6,"Двух головая собака"),
    Fish(14,1,1,3,"Карась"),
         Animal(50,90,4,200,"Коготь смерти"),
         Dog(40,20,9,15,"Барбос"),
         Fish(1,1,1,10,"Рыба клоун"),
         Fish(10,10,1,3,"Сом"),
         Animal(5,10,1,7,"Кротокрыс"),
         Fish(11,10,3,9,"Щука"),
         Animal(5,1,1,16,"Крыса"),
         Animal(10,3,1,9,"Суслик"),
         Animal(100,65,2,25,"Жираф")

     )

    fun lifeCycle() {
    println("Кол-во животный - ${animalZoo.size}".trimMargin())


        repeat(1000) {
            val randomAnimal = animalZoo.indices.random()
            val randomAction = (1..4).random()
            when (randomAction) {
                1 ->animalZoo[randomAnimal].eat()
                2 ->animalZoo[randomAnimal].move()
                3 ->animalZoo[randomAnimal].sleep()
                4 ->animalZoo[randomAnimal].giveBirth()
            }
            if (animalZoo[randomAnimal].isTooOld) {
                println("${animalZoo[randomAnimal].name} умер")
                animalZoo.removeAt(randomAnimal)
            }
            Thread.sleep(1)
            if (animalZoo.size == 0) {
                println("ВСЕ ЖИВОТНЫЕ МЕРТВЫ!")
                exitProcess(1)
            }

        }
        println("Животных осталось - ${animalZoo.size}")

    }

}












