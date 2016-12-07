package org.json_12306

import org.apache.spark.sql.SparkSession

/**
  * Created by geyalu on 2016/11/13.
  */
object json_12306 {


  def main(args: Array[String]): Unit = {
  }

  val spark = SparkSession
    .builder()
    .appName("Spark SQL Example")
    .config("spark.some.config.option", "some-value")
    .config("spark.sql.warehouse.dir","file:///")
    .getOrCreate()

  import spark.implicits._

  //val path ="home_output.json.sample";
  val path ="out.txt";
  //val options = Map("sep"->" ", "header"->"true", "inferSchema" -> "true")
  val home_output_df = spark.read.json(path)

  home_output_df.show(10)
  home_output_df.printSchema()

  home_output_df.createOrReplaceTempView("output")
  home_output_df.cache()
  //val resultsDF = spark.sql("select regeocode.addressComponent.city, count(1) as num from output group by regeocode.addressComponent.city").show(10)

  /*
    val resultsDF = spark.sql("select if(length(regeocode.addressComponent.city) = 2," +
      " regeocode.addressComponent.province, " +
      "regeocode.addressComponent.city) as city from output")


    resultsDF.groupBy("city").count().show(50)*/

  val resu = spark.sql("select data.datas[0].station_train_code as train_no from output limit 10")

  resu.show()
  resu.toDF()

/*  resu.write.parquet("12306js.parquet")
  val parquetFileDF = spark.read.parquet("12306js.parquet")
  parquetFileDF.createOrReplaceTempView("parquet")
  parquetFileDF.printSchema()
  spark.sql("select * from parquet").show()*/




  spark.stop()
}

