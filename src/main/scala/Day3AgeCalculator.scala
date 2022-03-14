import java.time.Year
import scala.io.StdIn.readLine

object Day3AgeCalculator extends App {
  val year = Year.now.getValue
  val Name = readLine("What is your name?")
  val Age = readLine(s"Greetings $Name, and how old are you?")
  val NewAge = year - Age.toInt + 100
  println(s"In $NewAge you will be one hundred years old!")


}
