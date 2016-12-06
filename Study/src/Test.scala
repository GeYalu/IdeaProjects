import java.util.Date

object Test {

  def main(args: Array[String]): Unit = {


    val list = (1 to 10000).toList

    val list1=list


    var beg = System.currentTimeMillis()

    list.map(_ + 42)



print("------------------")
    var end = System.currentTimeMillis()
    println(end-beg)

/*    var beg1 = System.currentTimeMillis()
    list1.par.map(_ + 12)
    var end1 = System.currentTimeMillis()
    println(end1-beg1)*/



  }

}
