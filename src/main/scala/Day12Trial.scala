class Animal (var animalName: String,
              val animalType: String = "wild",
              var animalLikes: String = "meat",
              var animalSound: String = "Auuuuu",
              var animalAge: Int = 0) {

  def makeSound(animalSound: String): Unit = {
    println(animalSound * 3)
  }
  def meetOrRun (animalMeet: String): Unit = {
    println (animalMeet)
  }
}

  object Day12Trial extends App {
    println("why there is no possibility to make class choosing new class not object?")
    val wolf = new Animal("wolf", animalSound = "Auuuuuuuuuuuuuuu")
    wolf.makeSound("Auu")
    println (wolf.makeSound("Au"))
    wolf.meetOrRun("run away how fast You can!")
}
