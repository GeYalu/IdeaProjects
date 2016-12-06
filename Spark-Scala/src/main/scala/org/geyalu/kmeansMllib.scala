package org.geyalu

import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.sql.SparkSession

/**
  * Created by geyalu on 2016/11/11.
  */
object kmeansMllib {

  def main(args: Array[String]): Unit = {
  }

  val spark = SparkSession
    .builder()
    .appName("Spark kmeans Example")
    .config("spark.some.config.option", "some-value")
    .config("spark.sql.warehouse.dir","file:///")
    .getOrCreate()

  val sc = spark.sparkContext
  sc.setLogLevel("WARN")


  val location_rdd = sc.textFile("location_data.txt")
  val training_rdd = location_rdd.map(_.split(" ")).
    map(location =>Vectors.dense(location(1).toDouble,location(2).toDouble))
    .cache()

  val numIterations = 100
  val numClusters = 10
  val runTimes = 20

  val clusters:KMeansModel = KMeans.train(training_rdd, numClusters, numIterations,runTimes)
  clusters.clusterCenters.foreach(
    x => {
      println(x)
    })

  val ks:Array[Int]=Array(5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)

  ks.foreach(cluster => {
    val model:KMeansModel = KMeans.train(training_rdd, cluster,numIterations,runTimes)
    val ssd = model.computeCost(training_rdd)
    println("sum of squared distances of points to their nearest center when k=" + cluster + " -> "+ ssd)
  })

  spark.stop()
}

