package com.github.MaraSk.FinalWorkJanaMara

import scala.io.StdIn.readLine

object FinalWorkTaskListManagerJanaMara extends App {
  //https://alvinalexander.com/scala/functional-programming-to-do-list-application/

  //Database migration
  //created task object to call methods
  val db = Task("user", "task")
  db.migrate()
  println("Final work assignment. TODO/TaskList manager. Authors: Jana Fedotova, Māra Skudrīte")
  val userName = readLine("Welcome to TaskList manager. Please enter Your name:")
  db.printHelp()

  //Adding User to Database
  db.AddNewUserToDatabase(userName)
  val selectionToDo = readLine(s"$userName, please select what do You want to do in task manager:")
  if (selectionToDo.toLowerCase == "add") {
    val taskInput = readLine("Enter task, please:")
    val firstTask = Task(userName, taskInput)
    println(firstTask.getUserId(userName))
    firstTask.addTaskToDB(userName, taskInput)
    println("Your task have been added!")
  } else {
    if (selectionToDo.toLowerCase == "h") {
      db.printHelp()
    } else {
      if (selectionToDo.toLowerCase == "v") {
        println(db.printUserTasks(userName, Task.toString()))
      } else {
        if (selectionToDo.toLowerCase == "q") {
          db.printQuit(userName)
        }
      }
    }
  }


  //TODO add loop to for reasking commands
  //TODO add new user support
}