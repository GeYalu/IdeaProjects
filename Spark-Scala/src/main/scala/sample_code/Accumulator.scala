package sample_code

import org.apache.spark.sql.SparkSession

//Accumulator with WordCount
object Accumulator {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().
      appName("Word Count Accumulator").
      getOrCreate()

    val sc = spark.sparkContext

    val total_counter = sc.longAccumulator("total_counter")
    val longWords_counter = sc.longAccumulator("longWord_counter")

    val dataFile = sc.textFile(args(0))
    val count = dataFile.flatMap(line => line.split(" ")).map { word =>
      total_counter.add(1)
      if (word.length > 5) longWords_counter.add(1)
      (word, 1)
    }.reduceByKey(_ + _).sortByKey(false)

    println(total_counter.value)
    println(longWords_counter.value)
    count.collect().foreach(println)
    spark.stop()

  }
}