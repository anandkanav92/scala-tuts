package learn.scala.patternmatching

import learn.scala.collection.{Cons, EmptyList, MyList}

object AllPatterns extends App {
  // All kinds of patterns that occur in the wild
  // 1. Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "A number"
    case "Scala" => "A string"
    case true => "A truth"
    case AllPatterns => "A singleton object"
  }

  // 2. Wildcard
  val matchAnything = x match {
    case _ => // matches anything
  }

  // 2.1 Variables
  val matchAnything2 = x match {
    case something => // matches anything and $something is available for use here in the return value
  }

  // 3. Tuples

  val aTuple = (1, 2)
  val matchTuple = aTuple match {
    case (1,1) =>
    case (something, 2) => // something is a variable that can be extracted now
  }

  val nestedTuple = (1, (2,3))
  val matchNestedTuple = nestedTuple match {
    case (a, (b, c)) => // all available for use here
  }
  // Soo powerful Patterns can be NESTED

  // 4. Case classes
  val myList: MyList[Int] = Cons(1, Cons(2, EmptyList))
  val matched = myList match {
    case EmptyList =>
    case Cons(_, Cons(head, tail)) => // Nested pattern matching with case classes
  }

  // You can have your class implement the "extractor" to work well with Scala's Pattern matching
  // Example: List class has this implemented
  // 5. Extractors
  val l = List(1,2,3,4,5)
  val matchList = l match {
    case List(1, _, _, _, _) => // extractor -- advanced
    case List(1, _*) => // extractor with vararg pattern
    case 1 :: List(_) => // infix pattern
    case List(1,2,3,4) :+ 5 => // infix pattern
  }

  // 6. Type specifiers
  val unknown: Any = 2
  val unknonMatch = unknown match {
    case lst: List[Int] => // to perform type checking
    case _ =>
  }

  // 7. Name binding
  val nameMatching = myList match {
    case notEmpty @ Cons(_, _) => // notEmpty is the name available to use here
    case Cons(1, rest @ Cons(_, _)) => // Variable naming also available with nesting
    case _ =>
  }

  // 8. Multi-patterns
  val multiPattern = myList match {
    case EmptyList | Cons(0, _) => // chain patterns with a pipe
    case _ =>
  }

  // 9. If Guards (ifs) // filters
  val guardedPattern = myList match {
    case Cons(0, Cons(a, _)) if a % 2 == 0 => // Guarded pattern with a condition
    case _ =>
  }

  val listOfNumbers = List(1,2,3)
  val nmatch = listOfNumbers match {
    case listOfStrings: List[String] => "List of strings"
    case listOfNumbers: List[Int] => "List of numbers"
    case _ => ""
  }
  println(nmatch)
  // Type erasure in JVM causes this match to occur on List, List only.
}
