package learn.scala.oops

object Inheritence {
  println("Defined")
  def main(args: Array[String]): Unit = {
    println("Main is called")
  }

  class Animal {
    protected def eat : Unit = println("nom nom nom")
    // Possible modifiers: public, private, protected
  }
  class Cat extends Animal {
    def crunch: Unit = {
      eat
      println("crunch crunch")
    }

    override def eat: Unit = {
      println("Cat eats")
      super.eat
    }
  }

  val cat = new Cat
  cat.crunch
  cat.eat

  // Constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, id: String) extends Person(name, age)
  // Note that you need to specify the arguments for constructors which don't have the same set of arguments
  class Adult2(name: String, age: Int, id: String) extends Person(name) // this is also allowed because
  // of the presence of auxillary constructor in Person

  // Super
  // super.method(.. to call the super method


  // Use final
  //  - with a class to make it non extendable
  //  - with a member to make it non overridable

  // Use sealed
  //  - To ensure no one else extends your class in another file

  // Abstract classes are made to be extended
  abstract class AnimalAbstract {
    val creatureType: String
    def eat(): Unit
  }
  class Dog extends AnimalAbstract {
    override val creatureType: String = "k9"
    override def eat(): Unit = println("munch munch")
  }
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Croc extends AnimalAbstract with Carnivore {
    override val creatureType: String = "wild"
    override def eat: Unit = println("Crunch")
    override def eat(animal: Animal): Unit = {
      eat
      println("Bone crunch")
    }
  }
  val croc = new Croc
  croc.eat(cat)

  // Traits vs abstract classes
  // - Traits don't have constructor parameters
  // - A class can only extend one class but can implement (with) many traits
  // - Trait = behaviour (verb), Abstract class = thing (noun)

  // Scala type hierarchy
  // Scala.Any > Scala.AnyRef (java.lang.Object) // String, List, Set
  // Scala.Any > Scala.AnyVal (Int, Unit, Boolean, Float)

  // Scala.AnyRef > Scala.Null
  // Scala.Nothing subclasses everything. (Even Null)
}
