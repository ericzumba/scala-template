object Main {
  def add(a: Int, b: Int): Int = a + b

  def main(args: Array[String]): Unit = {
    println(s"2 + 3 = ${add(2, 3)}")
  }
}