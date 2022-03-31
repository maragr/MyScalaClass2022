class Song (val title:String, val author:String, val song:Array[String]) {
  println(s"New $author song $title made")
  def sing ():Unit = {
    println(s"$author - $title")
    println(song.mkString(","))
  }

}

object Day13ExerciseSong extends App {
  println("Let's make some songs!")
  val ziemeļmeita = new Song("Ziemeļmeita", "Jumprava", Array("Gāju meklēt ziemeļmeitu","Garu, tālu ceļu veicu"))
  ziemeļmeita.sing()
  val chaotic = new Song("Chaotic", "Tate McRae", Array("I have this paralyzing fear that I'll maybe go nowhere, But God forbid me ever admitting I could be scared"))
  chaotic.sing()
}
