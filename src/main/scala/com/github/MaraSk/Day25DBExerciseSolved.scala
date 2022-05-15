package com.github.MaraSk

import java.sql.DriverManager
import scala.collection.mutable.ArrayBuffer

//TODO Create Album Case Class with appropriate data types for each field
case class Album(albumID: Int, Title:String, artistID: Int)

//TODO Create Track Case Class
case class Track(TrackID: Int,
                  Name: String,
                  AlbumID: Int,
                  MediaTypeID: Int,
                  GenreID: Int,
                  Composer: String,
                  Milliseconds: Int,
                  Bytes: Int,
                  UnitPrice: Double)

object Day25DBExerciseSolved extends App {
  println("Day 25 Exercise solution")

  //TODO connect to chinook and extract into Array of Album (using ArrayBuffer to build it up)

  val dbPath = "src/resources/db/chinook.db"
  val url =  s"jdbc:sqlite:$dbPath"

  val conn = DriverManager.getConnection(url)
  val statement = conn.createStatement()

  val sql =
    """
      |SELECT * FROM albums;
      |""".stripMargin

  val resultSet = statement.executeQuery(sql)
  val metaData = resultSet.getMetaData
  println(s"We have received ${metaData.getColumnCount} columns")

  val albumBuffer = ArrayBuffer[Album]()

  while (resultSet.next()) {
    val album = Album(
      resultSet.getInt("albumId"),
      resultSet.getString("Title"),
      resultSet.getInt("ArtistId"))
    albumBuffer += album
  }

  //TODO connect to database and extract into Array of Tracks
  val sqlTrack =
    """
      |SELECT * FROM tracks;
      |""".stripMargin

  val setTracks = statement.executeQuery(sqlTrack)
  val tracksMetaData = setTracks.getMetaData

  val trackBuffer = ArrayBuffer[Track]()

  while (setTracks.next()) {
    val track = Track(
      setTracks.getInt("TrackId"),
      setTracks.getString("Name"),
      setTracks.getInt("AlbumId"),
      setTracks.getInt("MediaTypeId"),
      setTracks.getInt("GenreId"),
      setTracks.getString("Composer"),
      setTracks.getInt("Milliseconds"),
      setTracks.getInt("Bytes"),
      setTracks.getDouble("UnitPrice")
    )
    trackBuffer += track
  }

  conn.close()

  val albumCollection = albumBuffer.toArray
  albumCollection.take(5).foreach(println)

  val tracksCollection = trackBuffer.toArray
  tracksCollection.take(5).foreach(println)


  //Extra Challenge
  //TODO save all Tracks into CSV - in src/resources/csv/tracks.csv -
  //results should be very similar or identical to what you get in DBeaver export CSV - tracks_exported.csv
  //Check Day 20 examples on how we did this

  val dst = "src/resources/csv/tracks.csv"

  val trackLines1 = for (t <- tracksCollection) yield {
    t.TrackID.toString + ". " +
      t.Name + "; " +
      t.AlbumID.toString + "; " +
      t.MediaTypeID.toString + "; " +
      t. GenreID.toString + "; " +
      t.Composer + "; " +
      t.Milliseconds.toString + "; " +
      t.Bytes.toString + "; " +
      t.UnitPrice.toString
  }
  trackLines1.take(5).foreach(println)
  val header: Array[String] = Array("TrackId,Name,AlbumId,MediaTypeId,GenreId,Composer,Milliseconds,Bytes,UnitPrice")
  val csvContent = header ++ trackLines1

  MyTools.saveLines(dst, csvContent)

}
