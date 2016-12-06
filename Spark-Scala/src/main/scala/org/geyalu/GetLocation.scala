package org.geyalu

import org.apache.spark.sql.SparkSession

import scala.math._
//import org.apache.spark.sql.functions._

/**
  * Created by geyalu on 2016/11/11.
  */
//object GetLocation {
//  def main(args: Array[String]): Unit = {
//
//  }
//
//  val spark = SparkSession.builder()
//    .config("spark.sql.warehouse.dir", "file:///")
//    .appName("Spark Example: NewDataExploring")
//    .getOrCreate()
//
//  import spark.sqlContext.implicits._
//
//  val sc = spark.sparkContext
//  sc.setLogLevel("WARN")
//
//  case class Person(id: String, gender: String, height: Int, weight: Int, Age: Int)
//
//  case class Location(id: String, lat: Double, lon: Double)
//
//  val person_rdd = sc.textFile("persons.txt")
//  val person_df = person_rdd.map(_.split(" "))
//    .map(p => Person(p(0), p(1), p(2).toInt, p(3).toInt, p(4).toInt)).toDF
//
//  val location_rdd = sc.textFile("location_data.txt")
//  val location_df = location_rdd.map(_.split(" "))
//    .map(location => Location(location(0), location(1).toDouble, location(2).toDouble)).toDF
//
//  person_df.createOrReplaceTempView("person")
//  location_df.createOrReplaceTempView("location")
//  person_df.
//
//  //person_df.show(5)
//  //location_df.show(5)
//
//  //val data_df=spark.sql("select * from person a left join location b on a.id = b.id").count()
//
//  //person_df.describe().show()
//  //spark.sql("select a.* from person a left join location b on a.id = b.id where b.id is null").show()
//  //person_df.filter(person_df("gender").equalTo("M")).agg(Map("height"->"avg")).show()
//
//  //person_df.agg(max("height")).show()
//  //person_df.filter(person_df("gender").equalTo("M")).agg("height"->"min").show()
//
//  //spark.sql("SELECT * from location a,location b where a.id = b.id ").show(5)
//
///*  var center_df = spark.sql("select id, avg(lat) as lat_center, avg(lon) as lon_center from location group by id")
//  center_df.show()
//  center_df.explain(true)
//
//  center_df.createOrReplaceTempView("center")*/
//  spark.udf.register("distance_cal", (x1:Double, y1:Double, x2:Double, y2:Double)=>sqrt(pow(x1-x2,2)+ pow(y1-y2,2)))
//  val sql =
//    """
//      |select a.id, a.lat, a.lon, b.lat_center, b.lon_center,
//      |distance_cal(a.lat, a.lon, b.lat_center, b.lon_center) as distance
//      |from location a join center b on a.id = b.id
//    """.stripMargin
//
//
//  val result_df=spark.sql(sql)
//
//  result_df.sort("distance").show()
//  //result_df.explain()
//
//  result_df.createOrReplaceTempView("distResult")
//
//  spark.sql("select *,row_number() over (partition by id order by distance desc) as rank from distResult ").show()
//
///*  spark.sql(" select t.* from " +
//    "( select *, row_number() over (partition by id order by distance desc) as rank from distResult) t " +
//    "where t.rank <=3").show()*/
//
//  spark.stop()
//}
//
