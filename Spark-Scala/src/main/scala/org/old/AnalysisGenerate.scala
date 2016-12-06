package Spark


import org.apache.spark.sql.SparkSession


/**
  * Created by geyalu on 2016/10/28.
  */
object AnalysisGenerate {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("AG").getOrCreate()
    val data = spark.sparkContext.textFile("persons.txt")

    data.cache()

    val male_rdd = data.filter(line=>line.contains("M"))
    val female_rdd = data.filter(line=>line.contains("F"))
    val male_height_avg = male_rdd.map(line=>line.split(" ")(2).toInt).mean()
    val female_height_avg = female_rdd.map(line=>line.split(" ")(2).toInt).mean()
    val male_height_minimun = male_rdd.map(_.split(" ")(2)).min()
    val male_height_top_5_array = male_rdd.map(line=>line.split(" ")).sortBy(_(2).toInt,false).take(5)



    //male_rdd.foreach(println)
    //female_rdd.foreach(println)

    //println(male_height_avg.toInt)
    //println(male_height_minimun)

    //println(male_height_top_5_array(0))
    //println(male_height_top_5_array(1)(2))

    for (i <- 0 until male_height_top_5_array.length){

      println(i + ": " + male_height_top_5_array(i)(2))
    }




/*
    //Top 5 M Sort by 身高
    val res = data.filter(x=>(x.split(" ").length == 5) && (x.split(" ")(1).contains("M")))
      .map(line=>(line.split(" ")(2),line.trim))
      .sortByKey(false).take(5)

    var mean = data.map(line=>(line.split(" ")(1),line.split(" ")(2).toInt)).reduceByKey(_+_)
    var count = data.map(line=>(line.split(" ")(1),1)).reduceByKey(_+_)

    res.foreach(println)
    mean.foreach(println)
    count.foreach(println)*/

  }

}
