package learn.scala.beginner

object ValsAndVars extends App {
  // VALS ARE IMMUTABLE
  val x: Int = 42

  // type is inferred @ compile time
  val y = if (x < 40) true else false
  val z = if (y) 50 else 100

  // code blocks
  val codeBlock = {
    if (y) {
      50
    }
    100 // last expression is the return value of the block
  }

  // Semi colons are only needed when multiple expressions in one line.
  // Basic types
  val aString: String = "hello"
  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val aShort: Short = 123
  val aLong: Long = 123999L
  val aFloat: Float = 2.0f
  val aDouble: Double = 2.0

  // VARIABLES
  var aVariable: Int = 4
  aVariable = 5 // Side effects - can't be completely avoided

}
