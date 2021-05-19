package learn.scala.beginner

object Functions extends App {
  def aFunction(a: String, b: String): String =
    a + " " + b

  def aParameterLessFunction(): Int = 42

  // You can call this parameter-less function in both ways (with or without parenthesis
  println(aParameterLessFunction())
  println(aParameterLessFunction)

  def aLoop(i: Int, n: Int): Unit = {
    println(i)
    if (i < n) aLoop(i+1, n)
  }
  aLoop(1, 10) // inclusive
  // Use recursive functions instead of loops

  def factorial(n: Int): Int = if (n <= 0) 1 else n * factorial(n-1)
  println("Factorial of " + 5 + " is " + factorial(5))

  def fibbo(n: Int): Int = if (n <= 2) 1 else fibbo(n-1) + fibbo(n-2)
  println("Fibbo of " + 8 + " is " + fibbo(8))

  def isPrime(n: Int): Boolean = {
    def loop(i: Int): Boolean = // Auxiliary function
      if (i <= 1) true else n%i != 0 && loop(i-1)
    loop(n-1)
  }
  println("1223 isPrime? = " + isPrime(1223))
}
