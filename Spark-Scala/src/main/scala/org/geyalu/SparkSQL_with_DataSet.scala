package org.geyalu

import org.apache.spark.sql.SparkSession
import org.geyalu.NewDataExploring.Person

/**
  * Created by geyalu on 2016/11/8.
  * 创建dataSet，从txt文件读取数据，生成table，进行sql查询
  */
object SparkSQL_with_DataSet {

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
  val p_ds = people_rdd.map(_.split(" ")).map(p=>Person(p(0),p(1),p(2),p(3),p(4))).toDS()

  p_ds.printSchema

  p_ds.createOrReplaceTempView("people")
  p_ds.cache()
  val resultsDF = spark.sql("select * from people limit 100")
  resultsDF.show(10)

}
