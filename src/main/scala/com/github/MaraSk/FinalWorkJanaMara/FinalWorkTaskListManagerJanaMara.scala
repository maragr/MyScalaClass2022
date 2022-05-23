package com.github.MaraSk.FinalWorkJanaMara

import scala.io.StdIn.readLine
/**
 * Runs taskList manager application until user choose to quit
 *
 * Add, print-out or delete tasks from database to user
 * @see [[https://alvinalexander.com/scala/functional-programming-to-do-list-application/]] for more information
 **/
object FinalWorkTaskListManagerJanaMara extends App {

 /** Database migration
  * created task object to call methods
  **/
  val db = Task("user", "task")
  db.migrate()
  /**
   * main loop to choose commands till user quits taskList manager
   **/
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
       // db.deleteTaskFromDB(userName)
        println("Your task have been deleted!")
      } else {
      if (selectionToDo.toLowerCase == "v") {
        db.printUserTasks(userName)
      } else {
        if (selectionToDo.toLowerCase == "q") {
          taskManagerActive = false
          db.printQuit(userName)
        } else println("Invalid command, please try again!")
      }
    }
  }
  }
}
}