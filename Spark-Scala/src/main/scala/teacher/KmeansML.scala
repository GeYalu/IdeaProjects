package org.dontan

import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.{OneHotEncoder, StringIndexer, VectorAssembler}
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.clustering.KMeans

/**
  * Created by Administrator on 2016/11/12.
  */
object KmeansML {
  case class Person(id:String, gender:String, height:Int, weight:Int, age:Int, salary:Double, score:Double, sd:String)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Kmeans ML")
      .config("spark.sql.warehouse.dir","file:///")
      .getOrCreate()
    import spark.sqlContext.implicits._
    val sc = spark.sparkContext
    val person_df = sc.textFile(args(0)).map(_.split(" "))
      .map(p=>Person(p(0),p(1),p(2).toInt,p(3).toInt,p(4).toInt,p(5).toDouble,p(6).toDouble,p(7))).toDF
    val indexer = new StringIndexer()
      .setInputCol("gender")
      .setOutputCol("genderIndex")
    val indexed_df = indexer.fit(person_df).transform(person_df)
    indexed_df.show()
    val encoder = new OneHotEncoder()
      .setInputCol("genderIndex")
      .setOutputCol("genderVec")
    val encoded = encoder.transform(indexed_df)
    encoded.select("id", "genderVec").show()
    val assembler = new VectorAssembler()
      .setInputCols(Array("genderVec", "height", "weight", "age","salary","score"))
      .setOutputCol("features")
    val training_df = assembler.transform(encoded)
    println(training_df.select("features", "sd").first())
    val kmeans = new KMeans().setK(18)
    val model = kmeans.fit(training_df)
    model.clusterCenters.foreach(println)
    spark.stop()
  }

}
