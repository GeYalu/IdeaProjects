import java.io.{FileWriter, _}

import scala.util.Random

/**
  * Created by geyalu on 2016/10/28.
  */
object Generator {

  def main(args: Array[String]): Unit = {

    val fw = new BufferedWriter(new FileWriter("persons.txt"));
    val rand = new Random()

    for (i <- 1 to 10000) {

      if (rand.nextInt(10)>5){
        fw.write((i+" "+"M"+" "+(150+rand.nextInt(50)))+" "+(40+rand.nextInt(40))+" "+(10+rand.nextInt(30)))
      }
      else {
        fw.write((i+" " +"F"+" "+(150+rand.nextInt(50)))+" "+(40+rand.nextInt(40))+" "+(10+rand.nextInt(30)))
      }
        fw.write(System.getProperty("line.separator"))
        fw.flush()
    }
    fw.close()

  }

}
