/**
  * Created by geyalu on 2016/11/15.
  */


class Person(var name: String, val age: Int) {
  println("this is Class Person")
  var gender: String = _
  val school = "qdu"

  def this(name: String, age: Int, gender: String) {
    this(name, age)
    this.gender = gender
  }

}

class Student(name: String, age: Int, val major: String) extends Person(name, age) {
  println("this is Student")
  println(major)
  override val school = "qust"

  override def toString = "Override toSting"
}


abstract class Person1{
  def speak
  val name:String
    val age:Int
}

class Student1 extends Person1{
  override def speak: Unit = {

      println("Speaking")


  }

  override val name: String = "hello"
  override val age: Int = 123
}


trait Logger{
  def log(): Unit ={
    println("Logger log")
  }
}

trait SecondLogger extends Logger{
  override def log(): Unit = {

    println("Second Logger log")

  }
}

class Test extends SecondLogger{

  def test(): Unit ={
    log()

  }

}

class ApplyTest{
  def apply() = "apply"
  def test: Unit ={
    println("test")
  }
}

object ApplyTest{
  def apply() = new ApplyTest
  def static: Unit ={
    println("static")
  }
}



object Week1 extends App{


  val value  = 3

  val result = value match {
    case 1=>"one"
    case 2=>"two"
    case _=>"other"
  }

  println(result)




}
