package learn.scala.oops

// Singly linked list
abstract class MyList[+A] {
  def head : A
  def tail : MyList[A]
  def isEmpty : Boolean
  def add[B >: A](i: B) : MyList[B] // means b is a superset of A
  def getElements : String
  def map[B](p: MyTransformer[A,B]): MyList[B]
  def filter(p: MyPredicate[A]): MyList[A]
  def flatMap[B](p: MyTransformer[A, MyList[B]]): MyList[B]
  def ++[B >: A](l: MyList[B]): MyList[B]
  override def toString : String = s"[ $getElements ]"
}

// Empty is the default list for any type so it should specify the type as Nothing
object EmptyList extends MyList[Nothing] {
  override def isEmpty: Boolean = true
  override def getElements: String = "$"
  override def head = throw new NoSuchElementException
  override def tail = throw new NoSuchElementException
  override def add[A >: Nothing](i: A): MyList[A] = new Cons(i, EmptyList)

  override def map[B](p: MyTransformer[Nothing, B]): MyList[B] = this

  override def filter(p: MyPredicate[Nothing]): MyList[Nothing] = this

  override def flatMap[B](p: MyTransformer[Nothing, MyList[B]]): MyList[B] = this

  override def ++[B >: Nothing](l: MyList[B]): MyList[B] = l
}

// +A makes it covariant

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def getElements: String = s"$head, ${tail getElements}"
  override def add[B >: A](i: B): MyList[B] = new Cons(i, this) // this says B is a superclass of A

  override def map[B](p: MyTransformer[A, B]): MyList[B] = new Cons(p.transform(head), tail.map(p))

  override def filter(p: MyPredicate[A]): MyList[A] =
    if (p.test(head))
      new Cons(head, t.filter(p))
    else
      t.filter(p)

  override def flatMap[B](p: MyTransformer[A, MyList[B]]): MyList[B] =
    p.transform(head) ++ tail.flatMap(p)

  override def ++[B >: A](l: MyList[B]): MyList[B] = new Cons(head, tail ++ l) // Magic concatenation
}

trait MyPredicate[-T] {
  def test(t: T): Boolean
}
trait MyTransformer[-A, B] {
  def transform(a: A): B
}


object ListTest extends App {
  val list : MyList[Int] = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  println( list toString )
  val myListInt: MyList[Int] = EmptyList
  val myListString: MyList[String] = EmptyList

  println(list.map(new MyTransformer[Int, Int] {
    override def transform(a: Int): Int = a * 2
  }) toString)

  println(list.filter(new MyPredicate[Int] {
    override def test(t: Int): Boolean = t % 2 == 0
  }) toString)

  println(list.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(a: Int): MyList[Int] = new Cons(a, new Cons(a+1, EmptyList))
  }) toString)
}

