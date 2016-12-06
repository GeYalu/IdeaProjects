package org.dontan
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.{ChiSqSelector, OneHotEncoder, StringIndexer, VectorAssembler}
import org.apache.spark.sql.SparkSession

/**
  * Created by Administrator on 2016/11/13.
  */
object PipelineExample {
  case class Person(id:String, gender:String, height:Int, weight:Int, age:Int, salary:Double, score:Double, sd:Double)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Kmeans ML Pipeline")
      .config("spark.sql.warehouse.dir","file:///")
      .getOrCreate()
    import spark.sqlContext.implicits._
    val sc = spark.sparkContext
    val person_df = sc.textFile(args(0)).map(_.split(" "))
      .map(p=>Person(p(0),p(1),p(2).toInt,p(3).toInt,p(4).toInt,p(5).toDouble,p(6).toDouble,p(7).toDouble)).toDF
    person_df.cache()
    val gender_indexer = new StringIndexer().setInputCol("gender").setOutputCol("genderIndex")
    val gender_encoder = new OneHotEncoder().setInputCol(gender_indexer.getOutputCol).setOutputCol("genderVector")
    val feature_array = Array("genderVector","height","weight","age","salary","score")
    val assembler = new VectorAssembler().setInputCols(feature_array).setOutputCol("vectorFeatures")
    val selector = new ChiSqSelector().setFeaturesCol("vectorFeatures").setLabelCol("sd")
      .setNumTopFeatures(3).setOutputCol("selectedFeatures")
    val lr = new LogisticRegression().setMaxIter(100).setRegParam(0.1)
      .setElasticNetParam(0.0).setFeaturesCol("selectedFeatures").setLabelCol("sd")
    val pipeline = new Pipeline().setStages(Array(gender_indexer,gender_encoder,assembler,selector,lr))
    val model = pipeline.fit(person_df)
    model.transform(person_df).select("selectedFeatures", "sd", "prediction", "rawPrediction", "probability")
      .show(truncate=false)
    spark.stop()
  }

}
