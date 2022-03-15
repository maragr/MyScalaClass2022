import scala.io.StdIn.readLine

object MaraTreeWork2022 extends App {


  val personName: String = readLine("What is Your name?").toUpperCase
  val treeHeight: Int = readLine(s"$personName what would be the height of Your name tree?").toInt


  if (treeHeight <= 40) {
    var i=1
    var symbol = 1
    var space = treeHeight
    while (i <= treeHeight) {
      for (c <- personName) {
        println(" " * space + s"$c" * symbol)
        i += 1; space -= 1; symbol += 2
      }
    }
  } else {
    { println(readLine(s"Dear $personName, sorry, this height $treeHeight is too long for survival, please run programm again and choose height 40 as maximum"))
          }
  }
}
