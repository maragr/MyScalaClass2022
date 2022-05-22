package com.github.MaraSk.FinalWorkJanaMara

import scala.io.StdIn.readLine

object FinalWorkTaskListManagerJanaMara extends App {
  //https://alvinalexander.com/scala/functional-programming-to-do-list-application/

  //Database migration
  //created task object to call methods
  val db = Task("user", "task")
  db.migrate()
  var taskManagerActive = true
  println("Final work assignment. TODO/TaskList manager. Authors: Jana Fedotova, Māra Skudrīte")
  val userName = readLine("Welcome to TaskList manager. Please enter Your name:")
  //Adding User to Database
  db.AddNewUserToDatabase(userName)
  db.printHelp() //to explain what are possibilities to do with task manager
  while (taskManagerActive){
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
      if (selectionToDo.toLowerCase == "rm") {
        val deleteTask = readLine("Please enter task number which You would like to delete: ")
        db.deleteTaskFromDB(userName, deleteTask.toInt)
      } else {
      if (selectionToDo.toLowerCase == "v") {
        db.printUserTasks(userName, Task.toString())
      } else {
        if (selectionToDo.toLowerCase == "q") {
          taskManagerActive = false
          db.printQuit(userName)
        }
      }
    }
  }
  }
}
}