package org.dontan

import org.apache.spark.sql.SparkSession

/**
  * Created by Administrator on 2016/10/28.
  */
object DataExploring {
  def main(args: Array[String]): Unit = {
    if (args.length < 1){
      println("Usage: DataExploring dataFile")
      sys.exit(1)
    }
    val spark = SparkSession.builder().appName("Spark Exercise").getOrCreate()
    val people_rdd = spark.sparkContext.textFile(args(0))
    val male_rdd = people_rdd.filter(line=>line.contains("M"))
    val female_rdd = people_rdd.filter(line=>line.contains("F"))
    val male_height_avg = male_rdd.map(line=>line.split(" ")(2).toInt).mean()
    val male_weight_avg = male_rdd.map(line=>line.split(" ")(3).toInt).mean()
    val male_age_avg = male_rdd.map(line=>line.split(" ")(4).toInt).mean()
    val male_height_minimum = male_rdd.map(_.split(" ")(2)).min()
    val male_height_maximum = male_rdd.map(_.split(" ")(2)).max()
    val male_height_top_5_array = male_rdd.map(line=>line.split(" ")).sortBy(_(2).toInt , false).take(5)
    println(male_height_top_5_array(0)(1)+" "+male_height_top_5_array(0)(2))
    spark.stop()
  }

}
