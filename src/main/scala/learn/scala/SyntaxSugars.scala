package learn.scala

object SyntaxSugars extends App {

  println("No need of main method?")

  def singleArgMethod(string: String): String = s"$string was supplied."
  val singleArgMethod2 = (s: String) => s"$s was supplied."

  // Curly braces are just expressions. Last of which is returned.
  println( singleArgMethod {
    // Complex logic here
    "blah"
  })

  List(1,2,3).map(x=>x+1)
  List(1,2,3).map {
    val a = 12
    x=>x+a // returns a function as expected by map
  }

  // Just like single method interfaces in Java, Single method Scala traits can be implemented by using lambdas
  trait Action {
    def act(i: Int): Unit
  }
  val boringAction: Action = new Action {
    override def act(i: Int): Unit = println(i)
  }
  val lambdaAction: Action = (i: Int) => println(i)

  // This pattern also works with abstract classes that have one unimplemented method
  abstract class Abstraction {
    def i(it:Int): Int = 3
    def unimplemented(it:Int): Int
  }
  val anAbstraction: Abstraction = (it: Int) => 4

  // Last character decides associativity of method
  // If a method name ends in a ":", it is right associative
   println(List(3,4).::(2).::(1) == 1 :: 2 :: List(3, 4))

  class Meow(var a: Int) {
    def ---:(i : Int): Meow = {
      this.a += i
      this
    }
    def -->(i: Int): Meow = {
      this.a -= i
      this
    }

    override def toString: String = this.a.toString
  }
  println((1 ---: 2 ---: 3 ---: new Meow(2)) --> 3 --> 4) // outputs 1

  // Multiword function names
  def `say my name`(name: String): Unit = println(s"My name is $name")
  `say my name`("Sheela")
  // Rarely used in practice

  // Generics use square brackets instead of triangular brackets like in Java
  class MyClass[T] {
    def printme(t: T): MyClass[T] = this
  }

  // update(...) is special just like apply(...)
  // Used for mutable collections
  val anArray: Array[Int] = Array(1,2,3)
  anArray(2) = 4 // gets rewritten to anArray.update(2, 4)
  // Two special functions in Scala so far = update, apply

  // Getters and Setters
  class MutableContainer {
    private var x: Int = 3 // members added to constructor are public
    def xval: Int = x // Getter
    def xval_=(newXVal: Int): Unit = x = newXVal // Setter
  }
  val m = new MutableContainer
  m.xval = 4 // Setter is called
  print(s"Getter is called and value set = ${m.xval}") // Getter is called
}
