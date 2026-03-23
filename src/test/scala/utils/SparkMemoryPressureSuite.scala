// src/test/scala/utils/SparkMemoryPressureSuite.scala
package utils

import org.scalatest.funsuite.AnyFunSuite
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel
import org.apache.spark.sql.functions._

class SparkMemoryPressureSuite extends AnyFunSuite {

  private def makeSpark(appName: String): SparkSession =
    SparkSession.builder()
      .appName(appName)
      .master("spark://spark-master:7077")
      .config("spark.ui.enabled", "true")
      .config("spark.ui.showConsoleProgress", "true")
      .config("spark.driver.memory", "1536m")
      .config("spark.executor.memory", "768m")
      .config("spark.executor.cores", "1")
      .config("spark.sql.shuffle.partitions", "192")
      .config("spark.default.parallelism", "96")
      // .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()

  test("memory pressure smoke test") {
    val spark = makeSpark("memory-pressure-smoke")
    spark.sparkContext.setLogLevel("WARN")

    try {
      import spark.implicits._

      val rows = 12000000L

      val df =
        spark.range(rows)
          .select(
            $"id",
            ($"id" % 1000000).as("k1"),
            ($"id" % 100000).as("k2"),
            sha2($"id".cast("string"), 256).as("hash"),
            repeat(lit("abcdefghij"), 20).as("payload") // ~200 chars per row
          )

      val shuffled =
        df.repartition(192, $"k1")
          .groupBy($"k2")
          .agg(
            count("*").as("n"),
            max($"hash").as("max_hash"),
            sum(length($"payload")).as("payload_sum")
          )
          .persist(StorageLevel.MEMORY_AND_DISK)

      val out = shuffled.count()

      println(s"Aggregated rows: $out")

      // Keep pressure around a bit longer so you can inspect docker stats/vmstat
      // Thread.sleep(30000)

      assert(out > 0)
    } finally {
      spark.stop()
    }
  }
}