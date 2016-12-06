package org.dontan

import org.apache.spark.sql.SparkSession

/**
  * Created by Administrator on 2016/11/11.
  */
object JsonExploring {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .config("spark.sql.warehouse.dir", "file:///")
      .appName("Spark Example: JsonExploring")
      .getOrCreate()
    val data_df = spark.read.json(args(0))
    data_df.printSchema()
    data_df.createTempView("home_output")
    //spark.sql("select regeocode.pois[0].name as name from home_output").show()
    //spark.sql("select regeocode.addressComponent.city, count(1) as num from home_output group by regeocode.addressComponent.city").show()
    spark.sql("select regeocode.addressComponent.city from home_output").show()
    spark.sql("select if(length(regeocode.addressComponent.city) = 2," +
      " regeocode.addressComponent.province, " +
      "regeocode.addressComponent.city) as city from home_output").show()
    spark.stop()
  }
}
