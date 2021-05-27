package learn.scala.oops

object Generics extends App {
  class NewMyList[A] { // Generic Class
    // A can be used as a type here
  }
  class MyMap[K, V] {
    // As many type parameters as you want
  }
  trait WorksOnTraitsToo[K, L, M]

  val listOfIntegers = new NewMyList[Int]
  val listOfStrings = new NewMyList[String]

  // Generic Methods
  object NewMyList {
    def empty[A]: NewMyList[A] = ??? // This is how you define generic methods
  }


  // Variance problem
  class Animal
  class Dog extends Animal
  class Cat extends Animal

  // Possible solutions to variance problem
  // Option 1.
  // List[Cat] extends List[Animal] => COVARIANCE
  class CovariantList[+A]
  val animal:Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add( new Dog ) // ?? ?? Hard question
  // Solution we return a list of Animals now instead of a list of Cats

  // Option 2 = INVARIANCE
  class InvariantList[A]
  val animal2: Animal = new Cat
  // val animalList: InvariantList[Animal] = new InvariantList[Cat] // is not allowed! EASY PEASY

  // Option 3 = CONTRAVARIANCE

  class ContraVariantList[-A]
  val animal3: Animal = new Cat
  val animalList2: ContraVariantList[Cat] = new ContraVariantList[Animal] // Reversed order :|

  // Update my list to make it covariant and generic
  // Make your generic classes bounded
  // Use A <: B if A is subset of B
  // or
  // A >: B if A is superset of B
}
