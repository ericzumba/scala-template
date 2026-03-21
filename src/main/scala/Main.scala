object Main:
  def add(a: Int, b: Int): Int = a + b

  @main def run(): Unit =
    println(s"2 + 3 = ${add(2, 3)}")