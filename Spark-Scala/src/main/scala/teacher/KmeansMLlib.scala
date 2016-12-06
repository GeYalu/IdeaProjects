package org.dontan

import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.SparkSession

/**
  * Created by Administrator on 2016/11/12.
  */
object KmeansMLlib {
  def main(args: Array[String]): Unit = {
    val spark=SparkSession.builder()
      .config("spark.sql.warehouse.dir","file:///")
      .appName("Spark Example: NewDataExploring")
      .getOrCreate()
    val sc = spark.sparkContext
    val gender_to_int = (x:String)=> x match{
      case "M" => 1.0
      case "F" => 0.0
    }
    val person_rdd = sc.textFile(args(0))
    val training_rdd = person_rdd.map(_.split(" "))
      .map(p=> Vectors.dense(gender_to_int(p(1)),p(2).toDouble, p(3).toDouble,
        p(4).toDouble,p(5).toDouble,p(6).toDouble,p(7).toDouble))
    training_rdd.cache()
    val numIterations = 100
    val numClusters = 10
    val runTimes = 20
    val clusters:KMeansModel = KMeans.train(training_rdd, numClusters, numIterations,runTimes)
    clusters.clusterCenters.foreach(println)
    spark.stop()
  }
}
