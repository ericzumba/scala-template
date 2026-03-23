import org.apache.spark.sql.SparkSession

object Main {
  def add(a: Int, b: Int): Int = a + b

  def makeSpark(appName: String): SparkSession = 
    SparkSession
      .builder()
      .appName(appName)
      .master("spark://spark-master:7077")
      .config("spark.ui.enabled", "true")
      .config("spark.ui.showConsoleProgress", "true")
      .config("spark.sql.shuffle.partitions", "4")
      .getOrCreate()
  
  
  def main(args: Array[String]): Unit = {
    val spark = makeSpark("scala-run-from-main")
    spark.sparkContext.setLogLevel("WARN")

    try {
      println(s"Spark version: ${spark.version}")
      println(s"Spark master : ${spark.sparkContext.master}")
      println(s"Spark app id : ${spark.sparkContext.applicationId}")

      val executors = spark.sparkContext.getExecutorMemoryStatus.keys.toList.sorted
      println("Executors seen by driver:")
      executors.foreach(e =>println(s" - $e"))
    } finally {
      spark.stop()
    }
  }
}