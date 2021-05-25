package learn.scala.oops

object OObasics extends App {
  // Class parameters are NOT class fields
  val person = new Person("John", 25) // class parameters
  val person2 = new Person("Doe", 27)

  // person.name is unavailable (add val or var to make it a class field)
  println(person.age) // works. (remember val is immutable though)

  val writer = new Writer("Charles", "Dickens", 1812)

  val novel = new Novel("Great Expectations", 1820, writer)
  println(s"${writer.fullname} wrote ${novel.name} when he was ${novel.authorAge} years old")
  println(novel.isWrittenBy(writer))
  val counter = new Counter
  counter.inc(10).pp
  counter.dec(5).pp
  counter.inc(10).dec(5).dec().pp

  // method with single arguments can be invoked in
  // INFIX notation or operator notation
  counter inc 10 pp

  // ALL OPERATORS ARE MEHODS ( +, - etc.)
  person.+(person2)
  person + person2

  // PREFIX notations
  val x = -1 // is the same as
  val y = 1.unary_-

  // unary_ works with only a few operators: - + ~ !
  println(!person)

  // Apply keyword
  println(person.apply()) // is treated as
  println(person()) // because apply() is implemented

  // POSTFIX available for only no argument methods
  person learnScala


  class Person(val name: String, val age: Int = 0) {
    // body (is called whenever a new object is created)
    val x = 2 // this is also created as a value
    println("Blah blah expressions")

    // this.name is not a class field, but it is still available
    def greet(name: String): Unit = println(s"${this.name} says What up $name?")

    def greet(): Unit = println(s"Hi, I am $name")
    // same name is allowed

    def this(n: String) = this(n, 0) // Constructors can only call other constructors
    // This can be avoided all together by using default values for the constructor arguments.

    def +(p: Person): Unit = println(s"${this.name} + ${p.name}")

    def unary_! : String = "NOT " + this.name

    def apply(): String = s"Say hello to ${name}"

    def learnScala : Unit = println(s"$name is learning Scala")
  }

  class Writer(val fn: String, val sn: String, val y: Int) {
    def fullname: String = fn + " " + sn
  }

  class Novel(val name: String, val y: Int, val author: Writer) {
    def authorAge: Int = y - author.y
    def isWrittenBy(author: Writer): Boolean = author == this.author
    def copy(y: Int): Novel = new Novel(this.name, y, this.author)
  }

  class Counter(val init: Int = 0) {
    def count(): Int = init
    def inc(v: Int = 1): Counter = new Counter(init + v)
    def dec(v: Int = 1): Counter = new Counter(init - v)
    def pp : Unit = println(init)
  }
}