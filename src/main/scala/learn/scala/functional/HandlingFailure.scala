package learn.scala.functional

import scala.util.{Try, Success, Failure}

object HandlingFailure extends App {
   // Try is used just like Optional
   // It is so beautiful

   // Complicated business logic with a bunch of nested try catches/null checks is
   // so easy to write with Expressive languages like Scala compared to Imperative Languages

   // scala.util.{Try, Success, Failure}
   val aSuccess = Success("Success")
   val aFailure = Failure(new RuntimeException("Failed here"))
   println(aSuccess, aFailure)

   def unsafeMethod(): String = throw new RuntimeException("No string for you")

   println(Try(unsafeMethod())) // Doesn't crash, returns a Failure object
   val potentialFailure = Try(unsafeMethod())
   potentialFailure.isSuccess
   val x = potentialFailure.orElse(Try("Yellow"))

   // Functions like map flatMap and filter also available
   // Which makes for comprehensions available. Just like with Options
   // Wins: Avoid runtime crashes, avoid endless try-catches
}
