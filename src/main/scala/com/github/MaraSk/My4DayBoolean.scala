package com.github.MaraSk

import scala.io.StdIn.readLine

object My4DayBoolean extends App {
  val personName = readLine(s"Please enter Your Name:")
  val yearsWorked = readLine(s"$personName how many years You work in our firm?").toDouble
  val monthSalary = readLine(s"$personName what is Your monthly salary?").toDouble
  val bonus2Years = monthSalary * 0.15
  val bonuss = monthSalary * 0.15 * (yearsWorked - 2)
  val roundedBonus = Math.round(bonuss * 100) / 100.0
  if (yearsWorked <= 2) {
    println(s"Sorry, $personName You have no bonus")
  } else println(s"$personName Your bonus will be $roundedBonus")

}
