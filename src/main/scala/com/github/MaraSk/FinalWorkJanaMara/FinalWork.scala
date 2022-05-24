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

  /**
   * Asks users to enter task
   * uses method from Task class
   * adds task to database
   */
  def doAddCommand(): Unit = {

    val taskInput = readLine("Enter task, please: ")
    val firstTask = Task(userName, taskInput)
    firstTask.addTaskToDB(userName, taskInput)
    println("Your task have been added!")
  }

  /**
   * Prints all users tasks with task Id
   * Ask user which task he wants to remove
   * uses method Task class to remove task
   * @param userName - task manager users name
   */
  def deleteTask(userName:String):Unit = {
    db.printUserTasks(userName)
    val taskID = readLine("Enter task ID that you want to delete: ").toInt
    db.deleteTaskFromDB(taskID)
    println("Task was successfully removed!")
  }

  println("Final work assignment. TODO/TaskList manager. Authors: Jana Fedotova, Māra Skudrīte")
  /** Asks user to enter name */
  val userName = readLine("Welcome to TaskList manager. Please enter Your name: ")
  /** Adding User to Database */
  db.addNewUserToDatabase(userName)
  /** /to explain what are possibilities to do with task manager */
  db.printHelp()
  /**
   * main loop to choose commands till user quits taskList manager
   * asks user to enter command
   */
  while (taskManagerActive) {
    val selectionToDo = readLine(s"$userName, please select what do You want to do in task manager: ")
    selectionToDo.toLowerCase match {
      case "add" => doAddCommand()
      case "h" => db.printHelp()
      case "v" => db.printUserTasks(userName)
      case "rm" => deleteTask(userName)
      case "q" =>
        taskManagerActive = false
        db.printQuit(userName)

      case _ => println("Invalid command, please try again!")
    }

  }
}

