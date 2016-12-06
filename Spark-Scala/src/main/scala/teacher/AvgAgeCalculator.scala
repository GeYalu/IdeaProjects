package org.dontan

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

/**
  * Created by Administrator on 2016/10/28.
  */
object AvgAgeCalculator {
  def main(args: Array[String]): Unit = {
    if (args.length < 1){
      println("Usage: AvgAgeCalculator datafile")
      System.exit(1)
    }
    val spark = SparkSession.builder().appName("Spark Exercise").getOrCreate()
    val dataFile = spark.sparkContext.textFile(args(1))//"sample_age_data.txt"


    spark.stop()
  }

}
