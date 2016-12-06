package org.geyalu

import org.apache.spark.sql.SparkSession
import org.geyalu.SparkSQL_with_DataSet.Person

/**
  * Created by geyalu on 2016/11/9.
  */
object SparkSQL_with_Reflection {
  def main(args: Array[String]): Unit = {

  }

  val spark = SparkSession
    .builder()
    .appName("Spark SQL Example")
    .config("spark.some.config.option", "some-value")
    .config("spark.sql.warehouse.dir","file:///")
    .getOrCreate()

  import spark.implicits._

  case class Person(id:String, gender:String, height:String, weight:String, age:String)

  val people_rdd = spark.sparkContext.textFile("persons.txt")
  val p_df = people_rdd.map(_.split(" ")).map(p=>Person(p(0),p(1),p(2),p(3),p(4))).toDF()

  p_df.createOrReplaceTempView("people")

  val result = spark.sql("select * from people limit 20")

  result.show()
  result.map(p=>"id"+p(0)).show()

}
