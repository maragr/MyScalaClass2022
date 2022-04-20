package com.github.MaraSk

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.Source

case class Document(title:String="", author:String="", url:String="", rows:Array[String]=Array[String]()) {
  val rowCount:Int=rows.length
  val wordsInRows: Array[Int] = rows.map(_.length)
  val wordCount:Int= wordsInRows.sum

  def makeTimeStamp: String = {
    val moment = LocalDateTime.now()
    val titleTimeStamp = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm").format(moment)
    titleTimeStamp
  }

  def makeFileName: String = {
    val author = GutenbergUtil.getAuthor(rows)
    val title = GutenbergUtil.getTitle(rows)
    author.slice(0,10)+"_"+title.slice(0,15)+"_"+makeTimeStamp+".txt"
  }

  def appendLines(lines: Array[String], url:String, author:String, title:String): Array[String]={
    val firstLine:String=s"URL: $url"
    val secondLine:String=s"Author: $author"
    val thirdLine:String=s"Title: $title"
    val nextLines: Array[String]=Array(firstLine, secondLine, thirdLine, "\n\n\n")++lines
    nextLines
  }

  def getLinesFromWeb (url:String) : Array[String]={
    val textFromWeb = Source.fromURL(url).getLines().toArray
    textFromWeb //did not manage to close the file
  }

  def save(dst:String="", folder:String="src/resources/texts"):Unit = {
    val lines = getLinesFromWeb(url)
    val newLines = appendLines(lines, url, author, title)
    val dstPath: String = if (dst == "") folder + "/" + makeFileName else folder + "/" + dst
    MyTools.saveLines(dstPath, newLines)
  }


}

object Day19DocumentReadingSolved extends App {
  val filePath = "src/resources/webPages.txt"
  val fileLines = MyTools.getLinesFromFile(filePath)
  val urlContent = for (line <- fileLines) yield {
    if (line.startsWith("https://")) line
    else "https://" + line
  }

  def getLinesFromWeb (url:String) : Array[String]={
    val html = Source.fromURL(url).getLines().toArray
    html
  }
  def getUrlDocuments(url:Array[String]):Array[Document] = {
    val documentArray = for (url <- url) yield {
      val rows = getLinesFromWeb(url)
      val title = GutenbergUtil.getTitle(rows)
      val author = GutenbergUtil.getAuthor(rows)
      Document(title, author, url, rows)
    }
    documentArray
  }
  val myDocuments = getUrlDocuments(urlContent)
  for (doc <- myDocuments) {
    val dstPath = doc.makeFileName
    doc.save(dstPath)
    println(s"You saved ${doc.title}, author: ${doc.author} from ${doc.url} to file $dstPath!")
  }

}


