package com.github.MaraSk.FinalWorkJanaMara

import java.sql.{Connection, DriverManager, PreparedStatement}
import scala.collection.mutable.ArrayBuffer

case class Task(userName:String, task:String){

  def printHelp():Unit = {
    println("add <task> - add a to-do item")
    println("h - show this help text")
    //println("rm [task number] - remove a task by its number (lets make this last)")
    println("v - view the list of tasks")
    println("q - quit")
    //println("s - show tasks (on current date or all tasks)")
  }

   val url =  s"jdbc:sqlite:src/resources/taskmanager/taskmanager.db"

  val conn: Connection = DriverManager.getConnection(url) //TODO handle exceptions at connection time
  //println(s"Opened Database at ${conn.getMetaData.getURL}")

  def migrate():Unit = {
    //migrate for db refers to table creation other setup needed to start work in a new environment
    //https://www.sqlitetutorial.net/sqlite-create-table/

    val statement = conn.createStatement() //we create a statement object that will handle sending SQL statements to the DB
    //this query should do nothing if table already exists
    val sql =
      """
        |CREATE TABLE IF NOT EXISTS users (
        |id INTEGER PRIMARY KEY,
        |name TEXT NOT NULL,
        |created TEXT
        |);
        |""".stripMargin

    statement.addBatch(sql)
    val sql1 =
      """
        |CREATE TABLE IF NOT EXISTS tasks (
        |task_id INTEGER PRIMARY KEY,
        |user_id INTEGER NOT NULL,
        |task TEXT,
        |created TEXT,
        |   FOREIGN KEY (user_id)
        |       REFERENCES users (id)
        |);
        |""".stripMargin
    statement.addBatch(sql1)
    statement.executeBatch()
  }
  def AddNewUserToDatabase(userName:String):Unit={
    if (getUserCount(userName) == 0) {
      val insertSql = """
                        |INSERT INTO users (name,created)
                        |values (?,CURRENT_TIMESTAMP)
""".stripMargin
      val preparedStmt: PreparedStatement = conn.prepareStatement(insertSql)
      preparedStmt.setString (1, userName)
      preparedStmt.execute
      preparedStmt.close()
    }
    else {
      println(s"User $userName already exists, nothing to do here!")
    }
  }
  def getUserId(userName:String):Int = {
    val sql =
      """
        |SELECT id cnt FROM users u
        |WHERE name = ?
        |LIMIT 1;
        |""".stripMargin
    val preparedStmt: PreparedStatement = conn.prepareStatement(sql)

    preparedStmt.setString(1, userName)

    val rs = preparedStmt.executeQuery

    val id = rs.getInt(1) //just the first column not worrying about the column name
    preparedStmt.close()
    id
  }
  def getUserCount(name:String):Int = {
    val sql =
      """
        |SELECT COUNT(*) cnt FROM users u
        |WHERE name = ?;
        |""".stripMargin
    val preparedStmt: PreparedStatement = conn.prepareStatement(sql)

    preparedStmt.setString(1, name)

    val rs = preparedStmt.executeQuery

    val cnt = rs.getInt(1) //just the first column not worrying about the column name
    preparedStmt.close()
    cnt
  }
  def addTaskToDB(userName:String, task:String):Unit = {
    val userid = getUserId(userName)
    val insertSql = """
                      |INSERT INTO tasks (user_id,task,created)
                      |values (?,?,CURRENT_TIMESTAMP)
""".stripMargin
    val preparedStmt: PreparedStatement = conn.prepareStatement(insertSql)
    preparedStmt.setInt (1, userid)
    preparedStmt.setString (2, task)
    preparedStmt.execute
    preparedStmt.close()
  }
  def showAllUsersTasks(userName:String):Array[Task]={
    val sql =
      """
        |SELECT u.name, t.task FROM tasks t
        |JOIN users u
        |ON u.id = t.user_id
        |WHERE u.name = ?
        |;
        |""".stripMargin

    val userBuffer = ArrayBuffer[Task]() //so we start with an empty buffer to store our rows
    val preparedStmt: PreparedStatement = conn.prepareStatement(sql)
    preparedStmt.setString(1, userName)
    val rs = preparedStmt.executeQuery
    while (rs.next()) {
      val userName = Task(rs.getString("name"), rs.getString("task"))
      userBuffer += userName
    }
    userBuffer.toArray //better to return immutable values
  }

  def printUserTasks(userName:String, task: String):Unit = {
    val allTasks = showAllUsersTasks(userName)
    println(s"Here is your tasks $userName:")
    allTasks.foreach(task => println(task.printTask))
  }


  def printQuit(userName:String):Unit = {
    println(s"Thank You $userName for using task manager! Good bye!")
  }
  // simple notification about quiting the TaskManager
  def printTask:String = s"$task"


}
