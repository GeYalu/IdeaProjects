package org.dontan

import java.io.FileWriter
import java.io.File

import scala.util.Random

/**
  * Created by Administrator on 2016/10/28.
  */
object SimpleDataFileGenerator {
  def main(args: Array[String]): Unit = {
    val writer = new FileWriter(new File("sample_age_data.txt"),false)
    val rand = new Random()
    for (i <- 1 to 10000) {
      writer.write(i +" "+ rand.nextInt(100))
      writer.write(System.getProperty("line.separator"))
    }
    writer.flush()
    writer.close()
  }

}
