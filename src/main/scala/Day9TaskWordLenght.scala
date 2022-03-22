import scala.io.StdIn.readLine

object Day9TaskWordLenght extends App {
  //TODO ask user to enter a sentence
  //Split sentence into words using split , you will will have a sequence of words, we did this on Day 8
  //Generate word length sequence (can use map or yield)
  //Filter only words of length over 5
  //print word lengths for every word
  //print the long words

  //you are allowed to use yield or map/filter

  val sentence = readLine ("Please enter the sentence: ")
  val sentenceSplit = sentence.split(" ")
  println (sentenceSplit.mkString(","))
  val wordLenght = sentenceSplit.map(n=>n.length.toInt)
  println (wordLenght.mkString(","))
  val longWords = sentenceSplit.filter(n => n.length > 5)
  println (longWords.mkString(","))
}
