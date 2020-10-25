package learn.scala

object PartialFunctions extends App {


  val aTypicalFunction: Int => Int = (x: Int) => x match {
    case 1 => 10
    case 2 => 100
  }

  // aTypicalFunction(3) throws match error

  /**
   * Partial functions can have only ONE parameter type
   * These are based on pattern matching
   */


  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 10
    case 2 => 100
  }

  println(aPartialFunction.isDefinedAt(1))
  println(aPartialFunction(2))

  val aPartialFunctionChain = aPartialFunction.orElse[Int, Int] {
    case 3 => 1000
  }
  println(aPartialFunctionChain(3))

  /**
   * Partial functions are subtypes of normal functions and can be lifted up.
   */
  val liftedFunction: Int => Option[Int] = aPartialFunctionChain.lift
  println("Lifted", liftedFunction(2))

  // Higher order functions also accept partial functions

  def enriched(f: PartialFunction[Int, Int]) = {
    List(1,2,3,4)
    .filter(x => f.isDefinedAt(x))
    .map(f)
  }

  println(
    enriched(aPartialFunction)
  )
  println(
    enriched(aPartialFunctionChain)
  )

  scala.io.Source.stdin.getLines().foreach(line => {
    println(line)
  })

}
