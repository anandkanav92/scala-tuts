package learn.scala.functional

object Functions extends App {
  // JVM expects everything to be an instance of a class
  // So that's what we do for functions
  // Create an Action class

  val action = new Action[Int, Int] {
    override def execute(a: Int): Int = a * 2
  }
  println(action.execute(2))

  // Advantage of using apply()
  val doubler = new MyFunction[Int,Int] {
    override def apply(a: Int): Int = a * 2
  }
  println(doubler(2)) // now doubler looks and behaves just like a function

  // ALL SCALA FUNCTIONS ARE OBJECTS
  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  } // can be written as
  val adder2 = (v1: Int, v2: Int) => v1 + v2
  // This is an anonymous function or LAMBDA
  val adder3: (Int, Int) => Int = (v1,v2) => v1 + v2

  // Lambdas need to be called, just specifying the name of function is not enough!
  println(adder3)

  // MORE SYNTAX SUGAR
  val adder4: (Int, Int) => Int = _ + _ // Each _ is a different argument. Same as (a: Int, b: Int) => a + b
  val doubler2: Int => Int = _ * 2 // same as (a: Int) => a * 2

  // Type information is needed for using underscores.
}

trait Action[A,B] {
  def execute(a: A): B
}

trait MyFunction[A,B] {
  def apply(a: A): B
}
