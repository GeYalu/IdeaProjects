package org.geyalu

import org.apache.spark.rdd.RDD

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}


object DataExploring {

  private val schema_string = "id,gender,height,weight,age"
  def main(args: Array[String]): Unit = {

    /*if (args.length < 1){
      println("Usage: NewDataExploring datafile")
      System.exit(1)
    }*/


    val spark = SparkSession.builder().appName("Spark Example: NewDataExploring").config("spark.sql.warehouse.dir","file:///").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val people_rdd = spark.sparkContext.textFile("persons.txt")

    val schema_array = schema_string.split(',')

    val schema = StructType(schema_array.map(fieldName => StructField(fieldName, StringType,true)))
    val row_rdd:RDD[Row] = people_rdd.map(_.split(" ")).map(eachRow=>Row(eachRow(0),eachRow(1),eachRow(2),eachRow(3),eachRow(4)))
    val people_df = spark.createDataFrame(row_rdd, schema)

    people_df.createOrReplaceTempView("people")
    spark.sql("select * from people where gender ='F'").show()
    spark.stop()

  }
}
