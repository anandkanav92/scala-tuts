package learn.scala.functional

object HOFandCurries extends App {
  val hof : (Int, (String, Int => Int) => Int) => Int => Int = null
  // A function which takes a function as an argument or returns a function is a HOF

  // map, flatmap, filter are all hof because they expect functions as input

  def ntimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else ntimes(f, n-1, f(x))

  println(ntimes(_+1, 10, 1))

  def ntimesbetter(f: Int => Int, n: Int): Int => Int =
    if (n <= 0) (a:Int) => a
    else (a:Int) => ntimesbetter(f, n-1)(f(a))
  val adder10 = ntimesbetter(_+1, 10)
  println(adder10(1))

  // Curried functions

  // Apart from defining curried function as lambdas
  val curriedLambda: String => Double => String = s => d => s.format(d)
  // you can also define them like this
  def curriedFormatter(s: String)(d: Double): String = s.format(d)

  val standardFormat: Double => String = curriedFormatter("%4.2f")
  println(standardFormat(5.456679))

  // Absract math for compose and andThen
  // already available in Function
  def compose[A,B,C](f: A=>B, g: C=>A): C=>B = {
    x => f(g(x))
  }

  def andThen[A,B,C](f: A=>B, g: B=>C): A=>C = {
    x => g(f(x))
  }
}
