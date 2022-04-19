package com.github.MaraSk

object Day10CharacterCountSolved extends App {
  def countCharsByCounting(text: String): Map[Char, Int] = {
    val charCountMap = scala.collection.mutable.Map[Char, Int]() //local to my function
    for (c <- text) { //c is what i named individual character in my text for the looping purposes
      //      println(s"Will do something with the character $c")
      //    charCount += (c -> text.count(t => t == c))
      charCountMap += (c -> text.count(_ == c)) //shorter version of above _ refers to single char in text
    }
    charCountMap.toMap //functions should return immutable structures whenever possible
  }

  def countCharsByBuckets(text: String, lower: Boolean = false): Map[Char, Int] = {
    val charCountMap = scala.collection.mutable.Map[Char, Int]()
    val myText = if (lower) text.toLowerCase else text
    for (c <- myText) {
      //code doing something with c goes here
      if (charCountMap.contains(c)) {
        charCountMap(c) += 1 //so we add to the value already stored by that key
      } else {
        //means we have no previous occurences of this particular character
        charCountMap(c) = 1 //so we start counting and set value to that particular key c to 1
      }
    }
    charCountMap.toMap //again we want immutable
  }

  val longText = "A quick brown fox jumped over a sleeping dog" * 1_000
  println(longText.length)

  val t0 = System.nanoTime()
  val resMap = countCharsByCounting(longText)
  val t1 = System.nanoTime()
  MyTools.printDeltaMs(t0, t1, "Counting by actually counting each char")

  val t2 = System.nanoTime()
  val resMap2 = countCharsByBuckets(longText)
  val t3 = System.nanoTime()
  MyTools.printDeltaMs(t2, t3, "Counting by buckets")

  val hugeText = "A quick brown fox jumped over a sleeping dog" * 1_000_000
  println(s" Huge text is ${hugeText.length} characters long")
  val t4 = System.nanoTime()
  val resMap3 = countCharsByBuckets(hugeText)
  val t5 = System.nanoTime()
  MyTools.printDeltaMs(t4, t5, "Counting by buckets huge text")
  println(resMap3)

  val t6 = System.nanoTime()
  val resMap4 = countCharsByBuckets(hugeText, lower = true)
  val t7 = System.nanoTime()
  MyTools.printDeltaMs(t6, t7, "Counting by buckets huge text lower only")
  println(resMap4)

}
