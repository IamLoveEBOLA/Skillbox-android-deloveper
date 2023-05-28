fun main() {
    val firstName: String = "Aboba"
    val lastName: String = "Abobovich"
    var height: Double = 1.80
    var weight: Float = 99.9f
    var isChild: Boolean = height < 1.50 && weight < 40
    var info: String= """ Меня зовут: $firstName $lastName;
    Мой рост  $height m
    Мой вес $weight kg
    Я являюсь ребенком? $isChild """
    println(info)


    height = 1.10
    weight = 25f
    isChild= height < 1.50 && weight < 40
    info = """ Меня зовут: $firstName $lastName;
    Мой рост  $height m
    Мой вес $weight kg
    Я являюсь ребенком? $isChild """
    println(info)


}