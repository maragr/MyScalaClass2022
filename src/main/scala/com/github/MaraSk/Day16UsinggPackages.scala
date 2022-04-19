package com.github.MaraSk

case class Laptop(name:String, speed:Double, manufacturer:String)

object Day16UsinggPackages extends App {
  println("Testing running my code from package")
  val myLaptop = Laptop ("maras", 4.5, "Lenovo")
  println(myLaptop)




}
