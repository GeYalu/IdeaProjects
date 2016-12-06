package org.geyalu

/**
  * Created by geyalu on 2016/10/29.
  */
object Aggregate {

  def main(args: Array[String]): Unit = {

    val rdd = List(1.1,2.1,3.0)
    val res = rdd.aggregate(0.0)(_+_,_+_)

    print(res)

    val folkres = rdd.fold(0.0)(_+_)

    print(folkres)
  }

}
