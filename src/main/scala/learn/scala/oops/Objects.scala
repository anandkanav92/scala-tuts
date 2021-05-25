package learn.scala.oops

object Objects extends App {

  // Scala doesn't know static
  // Objects contain class level functionality (not object level)
  object Person {
    // Static type functionality
    val N_EYES = 2
    def canFly: Boolean = false

    def factoryMethod(p1: Person, p2: Person) : Person = new Person
    // can be re-written as
    def apply(p1: Person, p2: Person) : Person = new Person
  }
  // COMPANIONS
  // companions can access each other's private methods
  class Person {
    // Instance level functionality
  }
  println(Person.N_EYES, Person.canFly)

  // Scala object is a SINGLETON INSTANCE

  // Scala is more object oriented than java
  val p1 = new Person
  val p2 = new Person
  println(p1 == p2) // false
  val p3 = Person // refers to the singleton object
  val p4 = Person
  println(p3 == p4) // true

  val pfactory = Person.factoryMethod(p1, p2)
  // OR
  val pfactory2 = Person(p1, p2) // Looks like a constructor but is apply() method in the object declaration

  // Scala Applications

  // Either "exends App" your scala object
  // OR
  // Implement the main method yourself
  def main2(args: Array[String]): Unit = {
    println("print me")
  }
}
