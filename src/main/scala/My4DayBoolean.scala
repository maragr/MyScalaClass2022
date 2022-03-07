import scala.io.StdIn.readLine

object My4DayBoolean extends App {
  val personName = readLine (s"Please enter Your Name:")
  val yearsWorked = readLine(s"$personName how many years You work in our firm?").toDouble
  val monthSalary = readLine(s"$personName what is Your monthly salary?").toDouble
  val bonus2Years = monthSalary*0.15
  val bonuss = monthSalary*0.15*(yearsWorked-2)
  if(yearsWorked<2) {
       println (s"Sorry, $personName You have no bonus")
    } else if (yearsWorked==2) {
    println (s"$personName Your bonus will be $bonus2Years")
  } else println(s"$personName Your bonus will be $bonuss")
// in this case 2 and 3 years get the same bonus

}
