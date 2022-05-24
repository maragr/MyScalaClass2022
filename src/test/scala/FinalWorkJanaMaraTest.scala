import com.github.MaraSk.FinalWorkJanaMara.Task
import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite

class FinalWorkJanaMaraTest extends AnyFunSuite with BeforeAndAfter {

  var testTask: Task = _

  before {
    testTask = new Task("Jana", "Do some tests in final project")

  }

  test("printTask"){
    assert(testTask.printTask === "Task ID: 0, Task: Do some tests in final project")
  }

  test("getUserId"){
    assert(testTask.getUserId("Jana") === 2)
  }

}
