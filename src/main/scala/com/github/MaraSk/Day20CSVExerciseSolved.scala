package com.github.MaraSk

object Day20CSVExerciseSolved extends App {
  val src = "src/resources/csv/fruitvegprices-19apr22.csv"
  val lines = MyTools.getLinesFromFile(src)

  val splitLines = for (line <- lines) yield line.split(",")

  def arrayToVeggie(arr:Array[String]):Veggie = {
    Veggie(arr(0), arr(1), arr(2), arr(3),arr(4).toDouble, arr(5))
  }

  val veggies = splitLines.tail.map(arrayToVeggie)

  val expensiveApples = veggies.filter(_.item == "apples").sortBy(_.price).reverse
  println(s"Top 5 the most expensive apple entries: \n")
  expensiveApples.take(5).foreach(println)

  val cheapApples = veggies.filter(_.item == "apples").sortBy(_.price)
  println(s"Top 5 the least expensive apple entries: \n")
  cheapApples.take(5).foreach(println)

  val lettuce = veggies.filter(_.item == "lettuce")
  val averagePriceOfLettuce = lettuce.map(_.price).sum / lettuce.length
  val lettucePriceRounded = MyTools.myRound(averagePriceOfLettuce, 2)
  println(s"Average price for lettuce $lettucePriceRounded")

  println("\n")

  //TODO get average price for lettuce
  val lettuceEntries = veggies.filter(_.item == "lettuce")
  val lettuceUnits = lettuceEntries.map(_.unit)
  lettuceUnits.distinct.foreach(println)

  val lettuceHead = lettuceEntries.filter(_.unit == "head")
  val lettuceHeadPrices = lettuceHead.map(_.price)
  println(lettuceHeadPrices.mkString(" "))

  val lettuceHeadAveragePrice = lettuceHeadPrices.sum/lettuceHead.length
  val roundedAverageHead = MyTools.myRound(lettuceHeadAveragePrice, 2)
  println(roundedAverageHead)

  val lettuceTwin = lettuceEntries.filter(_.unit == "twin")
  val lettuceTwinPrices = lettuceTwin.map(_.price)
  println(lettuceTwinPrices.mkString(" "))

  val lettuceTwinAveragePrice = lettuceTwinPrices.sum/lettuceTwin.length
  val roundedAverageTwin = MyTools.myRound(lettuceTwinAveragePrice,2)
  println(roundedAverageTwin)

  val cherrytomatoes2021 = veggies.filter(_.variety == "cherry").filter(_.date.contains("2021"))
  val cherrytomatoes2021prices = cherrytomatoes2021.map(_.price)
  //cherrytomatoes2022.take(10).foreach(println)
  println(s"Max cherry tomatoes price in 2021 is ${cherrytomatoes2021.map(_.price).max} ")
  println(s"Min cherry tomatoes price in 2021 is ${cherrytomatoes2021.map(_.price).min}")
  val cherrytomatoes2022mean = MyTools.myRound(cherrytomatoes2021.map(_.price).sum/cherrytomatoes2021.map(_.price).length,2)
  println(s"Average(mean) cherry tomatoes price in 2021 is $cherrytomatoes2022mean")
}

