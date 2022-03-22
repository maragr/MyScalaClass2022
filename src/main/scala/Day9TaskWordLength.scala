import scala.io.StdIn.readLine

object Day9TaskWordLength extends App {

  val sentence = readLine ("Please enter the sentence: ")
  val sentenceSplit = sentence.split(" ")
  println (sentenceSplit.mkString(","))
  val wordLength = sentenceSplit.map(n=>n.length)
  println (wordLength.mkString(","))
  val longWords = sentenceSplit.filter(n => n.length > 5)
  println (longWords.mkString(","))
}
