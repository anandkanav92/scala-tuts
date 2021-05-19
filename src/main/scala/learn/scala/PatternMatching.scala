package learn.scala

object PatternMatching extends App {
  val n = List(3)
  val description = n match {
    case head :: Nil => "Only element is $head"
    case _ => //default case do nothing
  }
  /**
   * Allowed pattern types:
   * constants
   * wildcards (like underscore)
   * tuples
   * and magic like above
   */

  // Case classes are easily used with Pattern matching

  case class Oinkers(name: String, animal: String)
  val oinker = Oinkers("peppa", "pig")
  val sound: String = oinker match {
    case Oinkers(_, animal) => s"grunt $animal"
    case _ => "UNKNOWN"
  }
  
  object singleDigit {
    def unapply(arg: Int): Boolean = arg < 10 && arg > -10
  }
  object even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  val checkMe: Int = 5
  val greeting: String = checkMe match {
    case even() => "Even"
    case singleDigit() => "SingleDigit"
    case _ => "_"
  }
  println(s"checkMe is $greeting")


  // Infix Patterns
  case class Or[A, B](a: A, b: B)
  val either = Or(2, "two")
  println(either match {
    case n Or s => s"$n is written as $s"
  })

}
