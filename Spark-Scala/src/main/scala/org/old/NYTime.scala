
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by geyalu on 2016/10/28.
  */
object NYTime {

  def main(args: Array[String]): Unit = {

    val jar = List(args(0))

    if (args.length != 3){
      println("Usage: java -jar code jar input output")
      System.exit(0)
    }

    //spark://Master.Hadoop:7077


    val conf = new SparkConf()
      conf.setMaster("local[2]")
        .setAppName("NYtime")
        //.setJars(jar)


    val spark = SparkSession.builder()
    val sc = new SparkContext(conf)
    val data = sc.textFile(args(1))

    data.cache()

    println(data.count())

    val res = data.filter(_.split(' ').length == 3)
      .map(_.split(' ')(1)).map((_,1)).reduceByKey(_+_)
      .map(x=>(x._2,x._1)).sortByKey(true).map(x=>(x._2,x._1)).saveAsTextFile(args(2))



    //res.foreach(println)



  }


}
