package learn.scala.functional

object TuplesMaps extends App {

  // Tuples = Finite ordered *lists*?
  val aTuple = Tuple2(2, "hello") // companion object
  // same as
  val anotherTuple = (2, "Woah", "Unreal") // also a way to write tuples
  // max 25 elements

  println(aTuple._1) // first element
  println(anotherTuple.copy(_2 = "tata Java"))
  println(aTuple.swap)

  // Maps = Keys -> values
  val aMap: Map[String, Int] = Map()
  val aPhoneBook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // -> is syntactic sugar for tuples
  println(aPhoneBook)

  println(aPhoneBook.contains("Jim"))
  println(aPhoneBook("Jim"))
  println(aPhoneBook("Mary"))

  val newPhoneBook = aPhoneBook + ("Mary" -> 42)
  println(newPhoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // More functions available like filterKeys, mapValues
  println(newPhoneBook.toList)
  println(newPhoneBook.toList.toMap)

  val names = List("Bob", "James", "Angela", "Jim")
  println(names.groupBy(name => name.charAt(0))) // names with same first characters are grouped
}
