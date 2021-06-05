package learn.scala.patternmatching

import scala.util.Random

object PatternMatching extends App {
  val rgen = new Random
  val x = rgen.nextInt(10)

  // First use case is like a switch statement
  val description = x match {
    case 1 => "The one"
    case 2 => "Double or nothing"
    case _ => "Default case" // _ = WILDCARD
  }
  println(x, description)

  // 1. It can decompose values
  // In conjunction with case classes

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n,a) if a < 21 => s"Hi I'm a minor and my name is $n and I am $a years old" // Pattern match with a GUARD
    case Person(n,a) => s"Hi my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }
  // Return value of the expression is a unification of the types. (Lowest common ancestor of the types in all the cases)
  // In case of no matches, you get a MatchError Exception

  println(greeting)

  // 2. On Sealed class hierarchies

  sealed class Animal // sealed classes help you cover your ass. Makes sure the pattern match is EXHAUSTIVE
  case class Parrot(greeting: String) extends Animal
  case class Dog(breed: String) extends Animal
  val animal: Animal = Dog("Chihuahua")
  println( animal match {
    case Dog(someBreed) => s"Matched a dog of $someBreed"
    case Parrot(greet) => s"Parrot says $greet"
    case _ => "Unknown animal"

  } )

  // Exercise
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def humanReadable(e: Expr): String = e match {
      case Number(n) => s"$n"
      case Sum(a, b) => s"${humanReadable(a)} + ${humanReadable(b)}"
      case Prod(a, b) => { // Prod is slightly tricky as we need parenthesis only when one of the expressions is a Sum
        def maybeShowParan(exp: Expr) = exp match {
            case Prod(_, _) => humanReadable(exp)
            case Number(_) => humanReadable(exp)
            case _ => "( " + humanReadable(exp) + " )"
          }
        maybeShowParan(a) + " * " + maybeShowParan(b)
      }
      case _ => "UNKNOWN"
    }
  println(humanReadable(Sum(Prod(Sum(Number(2), Number(5)), Number(3)), Sum(Number(4), Number(5)))))
}
