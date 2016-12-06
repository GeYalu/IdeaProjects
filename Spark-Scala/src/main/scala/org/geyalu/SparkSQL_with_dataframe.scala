package org.geyalu

import org.apache.spark.sql.SparkSession

/**
  * Created by geyalu on 2016/11/7.
  * 创建dataframe，从CSV文件读取数据，生成table，进行sql查询
  */
object SparkSQL_with_dataframe {

  def main(args: Array[String]): Unit = {

    }

  val spark = SparkSession
    .builder()
    .appName("Spark SQL Example")
    .config("spark.some.config.option", "some-value")
    .config("spark.sql.warehouse.dir","file:///")
    .getOrCreate()

  import spark.implicits._

  val options = Map("sep"->" ", "header"->"true", "inferSchema" -> "true")
  val person_with_header_df = spark.read.options(options).csv("people_data_with_header.csv")

  person_with_header_df.show(10)
  person_with_header_df.printSchema()

  person_with_header_df.createOrReplaceTempView("people")
  person_with_header_df.cache()
  val resultsDF = spark.sql("select * from people limit 100")
  resultsDF.show(10)

  spark.stop()
}
