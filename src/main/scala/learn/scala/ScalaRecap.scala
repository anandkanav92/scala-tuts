package learn.scala

/**
 * Read inline comments for basic functionality
 */
object ScalaRecap extends App {
  // Extending App Helps it run like an application

  override def main(args: Array[String]): Unit = {
    val aCondition: Boolean = false
    val aTernary = if (aCondition) 45 else 65
    val aBlock = {
      if (aCondition) 45
      32
    } // The last expression is assigned to the value

    // Unit = void
    val x: Unit = println(aBlock, aTernary)

    // Function
    def f(x: Int): Int = x + 1

    // Last item in block is returned in a function as well
    def multipliedBy2(x: Int): Int = {
      if (x % 2 == 0) println("Even") else println("Odd")
      x * 2
    }

    println(multipliedBy2(f(2)))

    // Object oriented programming

    class Animal
    class Dog extends Animal {
      override def toString: String = super.toString
    }
    val aDog: Animal = new Dog
    // var is variable and can be mutated, name is a value and immutable
    class Cat(var isPurring: Boolean, val name: String) extends Animal

    val aCat: Animal = new Cat(false, "Pepper")
    aCat.asInstanceOf[Cat].isPurring = true

    // Just like interfaces in Java
    trait Carnivore {
      def eat(a: Animal): Unit
    }
    class Lion extends Animal with Carnivore {
      override def eat(a: Animal): Unit = println("Lion ate ", a)
    }
    val aLion = new Lion
    aLion.eat(aDog)
    aLion eat aDog
    // Just like natural language
    // 1 + 2 == 1.+(2)

    // Singletons
    object Blah

    // case classes
    case class Person(name: String, age: Int) // Serializable and other bunch of functionality etc.

    // Exceptions
    val aPotentialFailure = try {
      val a = 5 / 0
      println(a)
    } catch {
      case e: RuntimeException => ("Caught Exception", e)
      case d: Exception => ("Weird Unknown Exception", d)
    } finally {
      println("Some Logs")
    }
    println(aPotentialFailure)

    // Functional Programming
    val incrementor = new Function[Int, Int] {
      override def apply(v1: Int): Int = v1 + 1
    }
    val incrementor2 = (x: Int) => x + 1

    val adder = new Function2[Int, Int, Int] {
      override def apply(v1: Int, v2: Int): Int = v1 + v2
    }

    println("1 increm = ", incrementor(1), incrementor2(1), List(1,2,3).map(incrementor2))


    // For comprehension
    for {n <- List(1, 2, 3, 4)
         c <- List("a", "b", "c")} {
      // println(n + "_" + c)
    }
    // For comprehension
    val pairs = for {n <- List(1, 2, 3, 4)
         c <- List("a", "b", "c")} yield n + "_" + c
    println(pairs)

    // Scala Collections: Map, Tuple, Seq, Array, Vectors
    val aMap = Map[String, Int] (
      "Yellow"-> 1,
      "Blue"  -> 2
    )

    // Pattern Matching
    val bob = Person("Wire", 25)

    val greeting = bob match {
      case Person(n, _) => s"Hello $n!"
      case default => "Hello Stranger!"
    }
    println(greeting)
  }
}
