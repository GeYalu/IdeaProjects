def classic_multiply(x:Int, y:Int): Int = x*y
classic_multiply(3,4)
val p_multiply_3 = classic_multiply(3,_:Int)
p_multiply_3(4)



def curry_multiply_1(x:Int) = (y:Int) => x*y
def curry_multiply(x:Int)(y:Int) = x*y

val curried_multiply_x_3 = curry_multiply (3) _
curried_multiply_x_3(4)

val curried_multiply_1 = curry_multiply_1(2)

curried_multiply_1(3)//2*3
//curried_multiply(4)

curry_multiply(2)(4)//


object Timer {
  var count = 0
  def currentCount():Long = {
    count +=1
    count //return
  }
  object t{
    val count=1
  }
}

Timer.currentCount()


class Calculator(brand:String)(color:String) {
  val Brand = brand
  val Color = color
  def p() = {
    println(Brand)
  }
}

val cal = new Calculator("HP")("GREEN")
cal.p()


val cal1 = new Calculator("HP")(_)

val time = Timer


val cal2 = new Calculator("TI")(_)


trait Car {
  val brand:String
  def p() = println(brand)
}

trait Color{
  val color:String
}

class BMW extends Car with Color with Shiny {
  val brand = "BMW"
  override def p() = println(brand(0))
  val color = "green"
  val shine = "ok"
}

val bmw = new BMW
bmw.p()

trait Shiny {
  val shine:String
}

abstract class Height (Height:Int){
  val height:Int
}

class People(Height:Int) extends Height (Height:Int){
  val height = Height
}



abstract class Weight{
  val weight:Int
}

class Man(Weight:Int) extends Weight{
  val weight = Weight
}



object Person{
  var name = "tom"
  var age = 20
  var height = 180
  def apply(): Int = height
}

Person.height

class Person{
  var name:String = "I have no name"
  private var age: Int = _

  def this(Age:Int)(Name:String){
    this()
    this.name = Name
    this.age = Age
  }
  private var height = Person.height
}

var person = new Person

var tom = new Person(10)("tom")

Person
val student = new Person
student

object Student extends Person {
  def apply(): Unit ={
    println("Oh! I have to go to class")
  }
}

Student.name
Student()

