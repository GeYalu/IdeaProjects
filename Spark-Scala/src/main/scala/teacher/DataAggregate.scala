package org.dontan
import org.apache.spark.sql.SparkSession

/**
  * Created by Administrator on 2016/10/29.
  */
object DataAggregate {
  def main(args: Array[String]): Unit = {
    if (args.length < 1){
      println("Usage: DataAggregate dataFile")
      sys.exit(1)
    }
    val spark = SparkSession.builder().appName("Spark Exercise").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    val people_rdd = spark.sparkContext.textFile(args(0))
    val male_rdd = people_rdd.filter(line=> line.contains("M"))
    val female_rdd = people_rdd.filter(line => line.contains("F"))
    val sample_male_rdd = spark.sparkContext.parallelize(male_rdd.takeSample(false, 70,30),5)
    val sample_female_rdd = spark.sparkContext.parallelize(female_rdd.takeSample(false,30,30),5)
    val sample_rdd = (sample_male_rdd++sample_female_rdd).coalesce(5,true)
    val result_string = sample_rdd.aggregate("0 0 0 0 0")(seqOp,combOp)
    println(result_string)
    spark.stop()
  }

  def seqOp(a:String, b:String):String=(a,b) match {
    case (a,b) if (a.split(" ")(3).toInt == b.split(" ")(3).toInt && a.split(" ")(2).toInt < b.split(" ")(2).toInt) => b
    case (a,b) if (a.split(" ")(3).toInt > b.split(" ")(3).toInt) => a
    case _ => b
  }

  def combOp(a:String, b:String):String = (a,b) match {
    case (a,b) if (a.split(" ")(2).toInt > b.split(" ")(2).toInt) => a
    case _ => b
  }
}
