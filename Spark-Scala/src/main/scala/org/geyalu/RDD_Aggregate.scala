package org.geyalu

import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkContext._

object RDD_Aggregate {

  def main(args: Array[String]): Unit = {

  }

    val spark = SparkSession.builder().appName("AG").getOrCreate()
    val data = spark.sparkContext.textFile("persons.txt")
    data.cache()


    val male_rdd = data.filter(line => line.contains("M"))
    val female_rdd = data.filter(line => line.contains("F"))
    val male_sample = male_rdd.takeSample(false, 10, 2)
    val female_sample = female_rdd.takeSample(false, 10, 2)
    val all_sample = male_sample ++ female_sample
    val Allsample_rdd = spark.sparkContext.parallelize(all_sample, 5)
    val allsample_rdd_weight = Allsample_rdd



    Allsample_rdd.foreach(println)


    //体重最重的人之中，最高的
    def seqOp(a: String, b: String): String = (a, b) match {
      case (a, b) if (a.split(" ")(3).toInt > b.split(" ")(3).toInt) => a
      case _ => b
    }

    def combOp(a: String, b: String): String = (a, b) match {
      case (a, b) if (a.split(" ")(2).toInt > b.split(" ")(2).toInt) => a
      case _ => b
    }
    val result_string = Allsample_rdd.aggregate("0 0 0 0 0")(seqOp, combOp)

    println(result_string)
    val result_rdd = spark.sparkContext.parallelize(List(result_string))
    result_rdd.coalesce(1,true).saveAsTextFile("res2")


    //体重最重的分区中的人之中最轻的
    def seqOp_weight(a: String, b: String): String = (a, b) match {
      case (a, b) if (a.split(" ")(3).toInt > b.split(" ")(3).toInt) => a
      case _ => b
    }

    def combOp_weight(a: String, b: String): String =
      if (a.split(" ")(3).toInt == 0) b
      else
        (a, b) match {
          case (a, b) if (a.split(" ")(3).toInt > b.split(" ")(3).toInt) => b
          case _ => b
        }
    var weight = allsample_rdd_weight.aggregate("0 0 0 0 0")(seqOp_weight, combOp_weight)

    println(weight)

    spark.stop()



}
