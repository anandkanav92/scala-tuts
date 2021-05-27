package learn.scala.beginner

object Strings extends App {
  val str: String = "Hello Scala"
  println(str.charAt(4))
  println(str.substring(3, 5)) // inclusive , exclusive
  println(str.split(" ").toList)

  println(str.length) // size of string

  val aNumber: String = "45"
  println(aNumber.toInt) // Simple toInt call
  println('X' + str + 'Y')
  println(str.take(2))
  println(str.reverse)

  // S-interpolated strings
  val name = "Dhruv"
  val age = 28
  println(s"My name is $name and age is $age and I'll be ${age + 1} next year") // Note the "s" at the beginning

  // F-interpolated strings
  val ff = 1.2f
  // It is like the printf standard
  // Each variabled $ is followed by a percent representation (like in printf)
  println(f"$name%s eats at $ff%2.2f speed")
  // Can also check for type correctness

  // Raw-interpolated strings
  val injected = "moves to a \nnewline"
  println(raw"This is a raw \n but the variable $injected") // Prints the raw value (the injected variables are escaped though)
}
