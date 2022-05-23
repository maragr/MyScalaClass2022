package com.github.MaraSk.FinalWorkJanaMara

import scala.io.StdIn.readLine
/**
 * Runs taskList manager application until user choose to quit
 *
 * Add, print-out or delete tasks from database to user
 * @see [[https://alvinalexander.com/scala/functional-programming-to-do-list-application/]] for more information
 **/
object FinalWork extends App {
  /** Database migration
   * created task object to call methods
   **/
  val db = Task("user", "task")
  db.migrate()
  var taskManagerActive = true
  def doAddCommand(): Unit = {

    val taskInput = readLine("Enter task, please: ")
    val firstTask = Task(userName, taskInput)
    firstTask.addTaskToDB(userName, taskInput)
    println("Your task have been added!")
  }
  def deleteTask(userName:String):Unit = {
    db.printUserTasks(userName)
    val taskID = readLine("Enter task ID that you want to delete: ").toInt
    db.deleteTaskFromDB(taskID)
    println("Task was successfully removed!")
  }
  println("Final work assignment. TODO/TaskList manager. Authors: Jana Fedotova, Māra Skudrīte")
  val userName = readLine("Welcome to TaskList manager. Please enter Your name: ")
  db.AddNewUserToDatabase(userName) //Adding User to Database
  db.printHelp() //to explain what are possibilities to do with task manager
  /**
   * main loop to choose commands till user quits taskList manager
   **/
  while (taskManagerActive) {
    val selectionToDo = readLine(s"$userName, please select what do You want to do in task manager: ")
    selectionToDo.toLowerCase match {
      case "add" => doAddCommand()
      case "h" => db.printHelp()
      case "v" => db.printUserTasks(userName)
      case "rm" => deleteTask(userName)
      case "q" => {
        taskManagerActive = false
        db.printQuit(userName)
      }
      case _ => println("Invalid command, please try again!")
    }

  }
}
