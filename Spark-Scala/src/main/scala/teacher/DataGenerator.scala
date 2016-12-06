package org.dontan

import java.io.{File, FileWriter}

import scala.util.Random

/**
  * Created by Administrator on 2016/10/28.
  */
object DataGenerator {
  def main(args: Array[String]): Unit = {
    val writer = new FileWriter(new File("people_data.txt"),false)
    val rand = new Random()
    for (i <- 1 to 10000) {
      if (rand.nextDouble()<0.3){
        writer.write(i+" F "+ (155+rand.nextInt(20)) + " "+ (45+rand.nextInt(5)) +" " + (20+rand.nextInt(20)))
        writer.write(System.getProperty("line.separator"))
      } else {
        writer.write(i+" M " + (160+rand.nextInt(30)) + " " + (60+rand.nextInt(30)) + " " + (25+rand.nextInt(25)))
        writer.write(System.getProperty("line.separator"))
      }
    }
    writer.flush()
    writer.close()
    println("Job Done!")
  }

}
