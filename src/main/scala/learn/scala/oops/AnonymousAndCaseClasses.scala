package learn.scala.oops

object AnonymousAndCaseClasses extends App {
  abstract class Animal {
    def eat : Unit
  }

  // Class is implemented inline
  // Hence is ANONYMOUS
  val animalInline = new Animal {
    override def eat: Unit = println("Crunch munch")
  }
  println(animalInline.getClass) // class learn.scala.oops.AnonymousAndCaseClasses$$anon$1

  // Anonymous instantiation works for abstract, as well as regular classes
  class RegularAnimal {
    def eat: Unit = println("Munchies")
  }
  val animalInline2 = new RegularAnimal {
    override def eat: Unit = {
      super.eat
      println("More munchies")
    }
  }
  animalInline2.eat

  // CASE CLASSES
  case class Person(name: String, age: Int)
  // Differences compared to a simple class:
  // 1. arguments are promoted to members
  val person = new Person("John", 29)
  println(person.age)

  // 2. Sensible toString representation
  println(person.toString) // is the same as
  println(person)

  // 3. Equals and hashCode already implemented
  val person2 = new Person("John", 29)
  println(person == person2) // is actually true (unlike regular classes)

  // 4. Handy copy methods
  val person3 = person2.copy(age=45)

  // 5. Have companion objects with apply(..) method implemented
  val mary = Person("mary", 29)

  // 6. Serializable - Useful in distributed systems

  // 7. Have extractor patterns - Can be used in Pattern Matching
}
