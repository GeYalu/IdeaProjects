package org.dontan

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

/**
  * Created by Administrator on 2016/11/12.
  */
object NewDataExploring {
  case class Person(id:String, gender:String, height:Int, weight:Int, age:Int, salary:Double, score:Double, sd:String)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .config("spark.sql.warehouse.dir","file:///")
      .appName("Spark Example: NewDataExploring")
      .getOrCreate()
    val sc = spark.sparkContext
    sc.setLogLevel("WARN")
    val options = Map("sep"->" ")
    import spark.sqlContext.implicits._
    val people_df = spark.read.options(options).csv(args(0))
    val person_df = sc.textFile(args(0)).map(_.split(" "))
      .map(p=>Person(p(0),p(1),p(2).toInt,p(3).toInt,p(4).toInt,p(5).toDouble,p(6).toDouble,p(7))).toDF
    //person_df.printSchema()
    person_df.createTempView("person")
    val age_salary_sql=
      """select * from
        |(select *, row_number() over(partition by age order by salary desc) rank from person) temp
        | where temp.rank <=2"""
      .stripMargin
    //spark.sql(age_salary_sql).show()
    val gender_score_sql =
      """select * from
        |(select *, row_number() over(partition by gender order by score desc) rank from person) temp
        |where temp.rank <=3""".stripMargin
    //spark.sql(gender_score_sql).show()
    val height_weight_sql =
      """select * from (select id,gender, height, weight, age, salary, score, sd,
         salary - lead(salary) over(partition by height order by salary desc) as salary_diff,
          dense_rank() over (partition by height order by salary desc) rank from person) temp
          where temp.rank =1
      """.stripMargin
    spark.sql(height_weight_sql).show()
    spark.stop()
  }

}
