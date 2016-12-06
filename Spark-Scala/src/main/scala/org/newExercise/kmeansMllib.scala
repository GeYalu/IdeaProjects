package org.newExercise

import org.apache.spark.sql.SparkSession
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

/**
  * Created by Administrator on 2016/11/11.
  */
object kmeansMllib {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("spark mllib kmeans").getOrCreate()
    val sc = spark.sparkContext
    sc.setLogLevel("WARN")
    val location_rdd = sc.textFile("data20000.txt")
    val training_rdd = location_rdd.map(_.split(" ")).
      map(people =>if (people(1)=="M")
        Vectors.dense(0.toDouble,people(2).toDouble,people(3).toDouble,people(4).toDouble,people(5).toDouble)
      else Vectors.dense(1.toDouble,people(2).toDouble,people(3).toDouble,people(4).toDouble,people(5).toDouble)
      ).cache()

    val numIterations = 100
    val numClusters = 18
    val runTimes = 20

    val clusters:KMeansModel = KMeans.train(training_rdd, numClusters, numIterations,runTimes)
    clusters.clusterCenters.foreach(println)

    val ks:Array[Int]=Array(5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)

/*    ks.foreach(cluster => {
      val model:KMeansModel = KMeans.train(training_rdd, cluster,numIterations,runTimes)
      val ssd = model.computeCost(training_rdd)
      println("sum of squared distances of points to their nearest center when k=" + cluster + " -> "+ ssd)
    })*/

    spark.stop()
  }
}
