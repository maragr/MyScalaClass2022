import scala.io.StdIn.readLine

object MaraTreeWork2022 extends App {

  //tree height should be the one assigned
  //simple version for height 3 would be
  //  *
  // ***
  //*****

  //for full points I would like to see the following
  //if user enters name Valdis  and height 9
  //then we should print
  //        *
  //       VVV
  //      AAAAA
  //     LLLLLLL
  //    DDDDDDDDD
  //   IIIIIIIIIII
  //  SSSSSSSSSSSSS
  // VVVVVVVVVVVVVVV
  //AAAAAAAAAAAAAAAAA
  val personName: String = readLine("What is Your name?")
  val treeHeight: Int = readLine(s"$personName what would be the height of Your name tree?").toInt


  if (treeHeight <= 40) {
    var i=1
    var symbol = 1
    var space = treeHeight
    while (i <= treeHeight) {
      println(" "*space+"*"*symbol)
      i+=1; space -= 1; symbol += 2
    }
  } else {
    println(readLine(s"Dear $personName, sorry, this height $treeHeight is too long for survival, please choose height 40 as maximum"))
  }




}
