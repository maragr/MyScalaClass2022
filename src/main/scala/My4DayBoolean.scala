import scala.io.StdIn.readLine

object My4DayBoolean extends App {
  val personName = readLine (s"Please enter Your Name:")
  val yearsWorked = readLine(s"$personName how many years You work in our firm?").toDouble
  val monthSallary = readLine(s"$personName what is Your monthly salary?").toDouble
  val bonus2Years = monthSallary*0.15
  val bonuss = monthSallary*0.15*(yearsWorked-2)
  if(yearsWorked<2) {
       println (s"Sorry, $personName You have no bonus")
    } else if (yearsWorked==2) {
    println (s"$personName Your bonus will be $bonus2Years")
  } else println(s"$personName Your bonus will be $bonuss")


}
