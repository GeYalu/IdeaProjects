package org.dontan

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
  * Created by Administrator on 2016/11/4.
  */
object NewDataExploring {
  private val schema_string = "id,gender,height,age"
  case class Person(id:String, gender:String, height:String, weight:String, age:String)
  def main(args: Array[String]): Unit = {
    if (args.length < 1){
      println("Usage: NewDataExploring datafile")
      System.exit(1)
    }

    val spark = SparkSession.builder()
      .config("spark.sql.warehouse.dir","file:///")
      .appName("Spark Example: NewDataExploring")
      .getOrCreate()
    spark.sparkContext.setLogLevel("WARN")
    val people_rdd = spark.sparkContext.textFile(args(0))
    import spark.sqlContext.implicits._
    val schema_array = schema_string.split(',')
    val schema = StructType(schema_array.map(fieldName => StructField(fieldName, StringType,true)))
    val row_rdd:RDD[Row] = people_rdd.map(_.split(" ")).map(eachRow=>Row(eachRow(0),eachRow(1),eachRow(2),eachRow(3)))
    val people_df = spark.createDataFrame(row_rdd, schema)
    val person_df = people_rdd.map(_.split(" ")).map{case p=> Person(p(0),p(1),p(2),p(3),p(4))}.toDF()
    val p_df = people_rdd.map(_.split(" ")).map(p=>Person(p(0),p(1),p(2),p(3),p(4))).toDF
    people_df.createOrReplaceTempView("people")
    spark.sql("select * from people limit 100").show()

    val options = Map("sep"->" ", "header"->"true", "inferSchema" -> "true")
    val people_with_header_df=spark.read.option("sep"," ")
      .option("header","true").schema(schema)
      .csv("people_data_with_header.csv")
    people_with_header_df.show()
    people_with_header_df.printSchema()
    val person_with_header_df = spark.read.options(options).csv("people_data_with_header.csv")
    person_with_header_df.show()
    person_with_header_df.printSchema()
    spark.stop()
  }
}
