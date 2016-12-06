package org.geyalu

import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession

/**
  * Created by geyalu on 2016/11/11.
  */
object SparkSQL_Hive {

  def main(args: Array[String]): Unit = {
  }

  case class Person(id:String, gender:String, height:String, weight:String, age:String)

  val spark = SparkSession
    .builder()
    .appName("Spark SQL Example")
    .config("spark.some.config.option", "some-value")
    .config("spark.sql.warehouse.dir","file:///")
    .getOrCreate()

  import spark.implicits._
  import spark.sql

  sql("CREATE TABLE IF NOT EXISTS person (id String, gender String, height String, weight String, age String)")
  sql("LOAD DATA LOCAL INPATH 'persons.txt' INTO TABLE person")

  // Queries are expressed in HiveQL
  sql("SELECT * FROM person").show(5)
  // +---+-------+
  // |key|  value|
  // +---+-------+
  // |238|val_238|
  // | 86| val_86|
  // |311|val_311|
  // ...

  // Aggregation queries are also supported.
  //sql("SELECT COUNT(*) FROM src").show()
  // +--------+
  // |count(1)|
  // +--------+
  // |    500 |
  // +--------+

  // The results of SQL queries are themselves DataFrames and support all normal functions.
  //val sqlDF = sql("SELECT key, value FROM src WHERE key < 10 ORDER BY key")

  // The items in DaraFrames are of type Row, which allows you to access each column by ordinal.
  //val stringsDS = sqlDF.map {
   // case Row(key: Int, value: String) => s"Key: $key, Value: $value"
  //}
  //stringsDS.show()
  // +--------------------+
  // |               value|
  // +--------------------+
  // |Key: 0, Value: val_0|
  // |Key: 0, Value: val_0|
  // |Key: 0, Value: val_0|
  // ...

  // You can also use DataFrames to create temporary views within a SparkSession.
  //val recordsDF = spark.createDataFrame((1 to 100).map(i => Record(i, s"val_$i")))
  //recordsDF.createOrReplaceTempView("records")

  // Queries can then join DataFrame data with data stored in Hive.
  //sql("SELECT * FROM records r JOIN src s ON r.key = s.key").show()
  // +---+------+---+------+
  // |key| value|key| value|
  // +---+------+---+------+
  // |  2| val_2|  2| val_2|
  // |  4| val_4|  4| val_4|
  // |  5| val_5|  5| val_5|
  // ...

}