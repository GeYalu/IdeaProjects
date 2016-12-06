package sample_code

import org.apache.spark.sql.SparkSession

object WordCount {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().
      appName("WordCount").
      getOrCreate()

    val dataFile = spark.sparkContext.textFile(args(0))
    val count = dataFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _).sortByKey(false)

    count.collect().foreach(println)
    spark.stop()

  }
}