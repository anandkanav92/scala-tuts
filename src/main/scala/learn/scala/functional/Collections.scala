package learn.scala.functional

import scala.util.Random

object Collections extends App {
  // Scala collections
  // Immutable collections lie in scala.collections.immutable

  // Traversable trait
  // ^Iterable
  // ^Set                   ^Seq                                      ^Map
  // ^HashSet, SortedSet    ^IndexedSeq       ^LinearSeq              ^HashMap SortedMap
  //                        ^Vector, String   ^List, Stack, Queue

  // Mutable collections
  // scala.collections.mutable
  // Traversable trait
  // ^Iterable
  // ^Set                     ^Seq                                      ^Map
  // ^HashSet, LinkedHashset  ^IndexedSeq ^LinearSeq ^Buffer            ^HashMap MultiMap

  // This course will focus only on Immutable collections

  // Sequences
  val aSeq = Seq(1,2,3,4)
  println(aSeq)
  println(aSeq.reverse)
  println(aSeq.apply(2))
  println(aSeq ++ aSeq.reverse)
  println(aSeq.reverse.sorted, "Should be sorted")

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(print)
  // Beautiful way to write script like Scala
  (1 to 10).foreach(_ =>println("x times"))

  // Lists
  // List vs MyList implementations
  // EmptyList = Nil actually
  // Cons = :: actually
  val aList = List(1,2,3)
  val prepended = 42 :: aList
  println(prepended)

  // Prepend and Append
  // colon is on the side of the list
  val prepended2 = 42 +: aList :+ 24
  val apples = List.fill(5)("apple")
  println(apples.mkString("--"))


  // Array equivalent to Java Array
  val numbers = Array(1,2,3,4)
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  // Mutable
  println(numbers)

  val numbersSeq: Seq[Int] = numbers // Implicit conversions
  println(numbersSeq)


  // Vectors
  // These are default implementation for immutable sequences
  // Effectively constant read/write times
  // Append and prepend are fast
  // Fixed branched trie (branch factor 32)
  // Work well for large sizes
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // Performance test Vector vs List
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times: IndexedSeq[Long] = for {
      _ <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), 0)
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // Pro: Keeps reference to tail
  // Con: Updating an element in the middle takes long
  println(getWriteTime(numbersList))

  // Pro: depth of the tree is small
  // Con: Needs to replace an entire 32 element chunk
  println(getWriteTime(numbersVector))
}
