package org.newExercise

import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.feature.OneHotEncoder
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession
/**
  * Created by Administrator on 2016/11/12.
  */
object KmeansML {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Kmeans ML")
      .config("spark.sql.warehouse.dir","file:///")
      .getOrCreate()
    val sc = spark.sparkContext
    sc.setLogLevel("WARN")
    import spark.sqlContext.implicits._
    val location_rdd = sc.textFile("data20000.txt")

    val training_df = location_rdd.map(_.split(" "))
      .map(people => (people(0),
        if (people(1)=="M") Vectors.dense(0.toDouble,people(2).toDouble,people(3).toDouble,people(4).toDouble,people(5).toDouble)
        else Vectors.dense(1.toDouble,people(2).toDouble,people(3).toDouble,people(4).toDouble,people(5).toDouble)))
      .toDF("label","features")


    val kmeans = new KMeans().setK(18)
    val model = kmeans.fit(training_df)

    model.clusterCenters.foreach(println)
    println(model.computeCost(training_df))
    spark.stop()
  }

}
