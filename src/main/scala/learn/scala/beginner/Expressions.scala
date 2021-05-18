package learn.scala.beginner

object Expressions extends App {
  // Expressions
  println(1 + 2 * 3)

  // Instructions (DO) vs Expressions (VALUE)
  // Python and Java are imperative and give instructions (Do something)
  // Scala is expressive (Evaluate this for me please)

  // If Expressions
  val aCondition = false
  val x = if (aCondition) 5 else 6

  // Everything in scala is an Expression
  var a = 0
  val weirdVal: Unit = (a = 1) // Unit

  val aCodeBlock = {
    val y = 2 // These variables are only visible here
    val z = 3 + y
    if (z > 2) "Hello" else "Bye" // Last value is returned
  }

  // AVOID IMPERATIVE CODE IN SCALA (Don't use while loops)

  // UGLY SCALA CODE EXAMPLE
  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  }

}
