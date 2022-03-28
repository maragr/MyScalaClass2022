object Day11ExercisePangram extends App {
  def isPangram(text: String, alpha: String = "abcdefghijklmnopqrstuvwxyz"): Boolean = {
    val testIfPangram = text.toLowerCase.toSet
    alpha.toSet.subsetOf(testIfPangram)
  }


  val myTestText = "The five boxing wizards jump quickly."
  println(isPangram(myTestText))
  val anotherText = "something different."
  println(isPangram(anotherText))
  val anotherRealPangram = "Pack my box with five dozen liquor jugs"
  println (s"lets check if $anotherRealPangram really is the pangram? ", isPangram(anotherRealPangram))
}


