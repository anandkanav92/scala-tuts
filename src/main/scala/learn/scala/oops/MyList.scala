package learn.scala.oops

// Singly linked list
abstract class MyList[+A] {
  def head : A
  def tail : MyList[A]
  def isEmpty : Boolean
  def add[B >: A](i: B) : MyList[B] // means b is a superset of A
  def getElements : String
  override def toString : String = s"[ $getElements ]"
}

// Empty is the default list for any type so it should specify the type as Nothing
object EmptyList extends MyList[Nothing] {
  override def isEmpty: Boolean = true
  override def getElements: String = "$"
  override def head = throw new NoSuchElementException
  override def tail = throw new NoSuchElementException
  override def add[A >: Nothing](i: A): MyList[A] = new Cons(i, EmptyList)
}

// +A makes it covariant

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def getElements: String = s"$head, ${tail getElements}"
  override def add[B >: A](i: B): MyList[B] = new Cons(i, this)
  // this says B is a superclass of A
}

object ListTest extends App {
  val list : MyList[Int] = new Cons(1, new Cons(2, EmptyList))
  println( list toString )
  val myListInt: MyList[Int] = EmptyList
  val myListString: MyList[String] = EmptyList
}

