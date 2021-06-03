package learn.scala.functional

object MapFlatmapFilter extends App {

  val lst = List(1,2,3) // Just like our implementation of MyList

  println(lst.head)
  // Has similar functions already implemented
  println(lst.map(_ - 1))

  val toPair = (x:Int) => List(x, x+1)
  println(lst.flatMap(toPair))

  // Print all possible combinations between two lists

  val numbers = List(1,2,3,4)
  val chars = List("a", "b", "c")
  // Trivially we would use two loops

  // In functional programming, we use recursions and these functions flatMap and map
  val combinations = numbers.flatMap((x: Int) => chars.map(a => s"${x}_$a"))
  println(combinations)

  // In case of 3 for loops nested, we would need flatMap => flatMap => map
  // This is how we do iterations in Scala.

  // There is a short hand for all these chains of flatMap, flatMap, map

  val forComprehensionCombinations = for {
    x <- numbers if x % 2 == 0 // If guard
    a <- chars
  } yield s"${x}_$a"

  // All of the above is re-written by the compiler into chains of flatMap, Map, Filter and ForEach
  // If guard adds a filter before the flatMap

  // For functions with side effect, yield is not needed
  for {
    n <- numbers
  } println(n)
  // Is equivalent to
  lst.foreach(println)

  // Implemented a Maybe object.
  // Deals with possible absence of value.
}
