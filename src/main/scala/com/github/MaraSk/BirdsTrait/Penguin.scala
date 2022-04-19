package com.github.MaraSk.BirdsTrait

class Penguin extends Swim with Run {
  swim(): Unit

  run(): Unit

}

class Chicken extends Fly with Run {
  override def fly(): Unit = println("flying")

  override def run(): Unit = println("running very fast")

}

class Birds extends Fly with Run with Swim {
  override def fly(): Unit = println("flying")
  run()

  override def swim(): Unit = println("Notice, that some birds cannot swim")
}

object Day14ExerciseBirds extends App {
  println("Let's create some birds using traits!")

  val penguin1 = new Penguin()
  penguin1.swim()
  penguin1.run()

  val penguin2 = new Penguin()
  penguin2.swim()
  penguin2.run()


  val chicken1 = new Chicken()
  chicken1.run()
  chicken1.fly()

  val chicken2 = new Chicken()
  chicken2.run()
  chicken2.fly()

  val someBird = new Birds()
  someBird.run()
  someBird.fly()
  someBird.swim()
}