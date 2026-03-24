import org.scalatest.funsuite.AnyFunSuite

class MainSuite extends AnyFunSuite {
  test("add sums two integers") {
    assert(Main.add(2, 3) == 5)
  }
}