class Animal (var animalName: String,
              val animalType: String = "wild",
              var animalLikes: String = "meat",
              var animalSound: String = "Auuuuu",
              var animalAge: Int = 0) {

  def makeSound(animalSound: String): Unit = {
    println(animalSound * 3)
  }

  def meetOrRun(animalMeet: String, likeContact: Boolean): Unit = {
    if (likeContact) {
      println(s"$animalName it is nice to meet You with $animalMeet")
    } else {
      println(s"Let's run away from $animalName and say $animalMeet")
    }
  }
}


  object Day12AnimalExercise extends App {
    println("Let's meet some Animals with greetings or run away from them")
    val wolf = new Animal("wolfSecret", animalSound = "Auuuuuuuuuuuuuuu")
    wolf.makeSound("Auu")
    wolf.meetOrRun("nothing!",likeContact = false)

    val chicken = new Animal("chichenTed", "domestic", "grains", "Cococooco")
    chicken.makeSound("coocococooo")
    chicken.meetOrRun("greetings, I have served some food for You!", likeContact = true)

    val lion = new Animal("lionKing", "wild", animalSound="Rrrrrr")
    lion.makeSound("rrrr")
    lion.meetOrRun("Help!", likeContact = false)
  }

