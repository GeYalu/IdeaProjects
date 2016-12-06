package org.geyalu

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
  * Created by geyalu on 2016/11/9.
  */
object SparkSQL_with_Schema {

  def main(args: Array[String]): Unit = {

  }

  val spark = SparkSession
    .builder()
    .appName("Spark SQL Example")
    .config("spark.some.config.option", "some-value")
    .config("spark.sql.warehouse.dir","file:///")
    .getOrCreate()


  val schema_string = "id,gender,height,weight,age"

  val people_rdd = spark.sparkContext.textFile("persons.txt")

  val fields = schema_string.split(',')
    .map(fieldName => StructField(fieldName,StringType,nullable = true))

  val schema = StructType(fields)

  val rowRDD = people_rdd
    .map(_.split(" "))
    .map(attributes => Row(attributes(0),attributes(1),attributes(2),attributes(3),attributes(4)))


  val peopleDF = spark.createDataFrame(rowRDD,schema)

  peopleDF.createOrReplaceTempView("people")

  val result = spark.sql("select * from people limit 20")

  result.show()




}
