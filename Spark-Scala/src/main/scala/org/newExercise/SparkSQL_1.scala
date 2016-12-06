package org.newExercise

import org.apache.spark.sql.SparkSession

/**
  * Created by geyalu on 2016/11/12.
  */
object SparkSQL_1 {

  def main(args: Array[String]): Unit = {
  }

  val spark = SparkSession
    .builder()
    .appName("Spark SQL New Ex")
    .config("spark.some.config.option", "some-value")
    .config("spark.sql.warehouse.dir", "file:///")
    .getOrCreate()

  val sc = spark.sparkContext
  sc.setLogLevel("WARN")

  import spark.implicits._

  case class Person(id: String, gender: String, height: Int, weight: Int, age: Int, score: Float, salary: Int, single: Int)

  val people_rdd = spark.sparkContext.textFile("data20.txt")
  people_rdd.cache()

  val p_df = people_rdd.map(_.split(" ")).map(p => Person(p(0), p(1), p(2).toInt, p(3).toInt, p(4).toInt, p(5).toFloat, p(6).toInt,p(7).toInt)).toDF()
  p_df.createOrReplaceTempView("people")

  //val result = spark.sql("select * from people limit 20").show(5)

/*  val MaxSalary = spark.sql("select t.* from (select *, row_number() over (partition by age order by salary desc) as rank from people) t " +
    "where t.rank<=2 order by t.age " ).show()*/


/*
  val MaxSalary1 = spark.sql("select t.*  from (select *, row_number() over (partition by height order by salary desc) as rank from people) t " +
    "where t.rank=1 " ).toDF().createOrReplaceTempView("salary1")
  val MaxSalary2 = spark.sql("select t.*  from (select *, row_number() over (partition by height order by salary desc) as rank from people) t " +
    "where t.rank=2 " ).toDF().createOrReplaceTempView("salary2")
  spark.sql("select * from salary1").show()
  spark.sql("select * from salary2").show()
  val Max_Second = spark.sql("select a.* ,b.*,a.salary-b.salary as salarya_b from salary1 a join salary2 b where a.height = b.height").show()
*/


  //对Salary 进行sort 取最高的5个
/*  val sort_result = p_df.sort($"salary".desc).take(5)
  sort_result.foreach(println)*/

  val male_df =  p_df.filter(x=>x(1)=="M").sort($"score".desc).show(5)
  val female_df =  p_df.filter(x=>x(1)=="F").sort($"score".desc).show(5)



  //val male_rdd = people_rdd.map(_.split(" ")).filter(_2=="M")

  //male_rdd.foreach(println)
  //val female_rdd = people_rdd.map(_.split(" ").filter(line => line.contains("F"))

}
