package com.github.MaraSk

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object Day17ExerciseSolved extends App {
  val filePath = "src/resources/stopping_by.txt"

  val bufferedSource = Source.fromFile(filePath)
  val myLines = bufferedSource.getLines().toArray
  bufferedSource.close()

  val poemName = myLines.head
  println(s"Poem name: $poemName\n")

  // One method:
  val poetName = myLines(1).drop(3)

  // Another method:
  val lengthOfSecondLine: Int = myLines(1).length
  //  val poetName = text(1).slice(3,lengthOfSecondLine) //so we slice from 4th character to the end
  println(s"Poet name: $poetName\n")

  //  val poetNames = myLines(1).split(" ") //so here I split by single whitespace
  val poetNames = myLines(1).split(" +") //so this was real regex I said I want to split by 1 or more whitespace
  println(poetNames.mkString("|"))
  //we would need to check if we got anything meaning
  val middleName = poetNames(1) //so 2nd word in the 2nd line
  println(s"Poet's first name is: $middleName")


  val woodsLines = for (line <- myLines if line.contains("woods")) yield line
  println("Lines with the word 'woods':\n")
  woodsLines.foreach(println)

  //alternative way to filter what we need
  val woodLines = myLines.filter(_.contains("woods")).map(_.toUpperCase)
  woodLines.foreach(println)

  val outputString = myLines.head +
    "\n" +
    poetName +
    "\n" +
    woodLines.mkString("\n")

  //for building longer strings we might want to use a string buffer instead of creating a string each time
  //here are some good examples

  //https://www.baeldung.com/scala/stringbuilder

  val stringBuilder = new StringBuilder()
  stringBuilder ++= myLines.head
  stringBuilder += '\n' //for single characters  of course ++= "\n" would work as well
  stringBuilder ++= poetName
  stringBuilder ++= "\n"
  stringBuilder ++= woodLines.mkString("\n")

  println("WE built a string, this is recommended for longer texts")
  println(stringBuilder.mkString)


  MyTools.saveText("src/resources/woods.txt", outputString)

  val myBuffer = ArrayBuffer[String]()
  myBuffer += myLines.head
  myBuffer += poetName
  myBuffer ++= woodLines //we are adding another array

  MyTools.saveLines("src/resources/woods_arr.txt", myBuffer.toArray)

  //we can also append to an already existing file
  MyTools.saveLines("src/resources/woods_arr.txt", Array("*" * 30, "Hurray", "hurray"), append = true)
  MyTools.saveLines("src/resources/woods_arr.txt",
    Array("*" * 30, "aha", "oho"),
    append = true,
    lineEnd = "\n\uD83D\uDE05\n" //so this is unicode for a smiley takes 4 bytes actually using \u

  )

}
