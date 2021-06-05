package learn.scala.patternmatching

object PatternsEverywhere extends App {
  // Patterns are an integral part of Scala
  // The pattern fundamentals are available to use anywhere

  // Big Idea # 1
  try {

  } catch {
    case e: NullPointerException =>
    case e2: ArithmeticException =>
    case _ =>
  }
  // Catches are just matches!

  // Big Idea # 2
  // Generators are also based on pattern matching
  val list = List(1,2,3)
  val evenOnes = for {
    x <- list
  } yield 10 * x

  val tuples = List((1,2), (3,5))
  val matchedTuples = for {
    (a,b) <- tuples
  } yield a*b
  // Also available for all case classes etc.

  // Big Idea # 3
  // General codebase can also decompose expressions like in pattern matching
  val tt = (12, 13)
  val (a,b) = tt // a and b are now defined

  // Big Idea # 4
  // Partial functions also use it.
  val mappedList = list.map {
    case 1 => "The one"
    case 2 => "Even"
    case _ => "Too big"
  }
  println(mappedList)
  // As if this was written like this below
  val mappedList2 = list.map {
    x => x match {
      case 1 => "The one"
      case 2 => "Even"
      case _ => "Too big"
    }
  }
  
}
