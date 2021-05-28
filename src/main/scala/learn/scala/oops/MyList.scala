package learn.scala.oops

import scala.annotation.tailrec

// Singly linked list
abstract class MyList[+A] {
  def head : A
  def tail : MyList[A]
  def isEmpty : Boolean
  def add[B >: A](i: B) : MyList[B] // means b is a superset of A
  def getElements : String
  def map[B](p: A => B): MyList[B]
  def filter(p: A => Boolean): MyList[A]
  def flatMap[B](p: A => MyList[B]): MyList[B]
  def ++[B >: A](l: MyList[B]): MyList[B]
  def +[B >: A](item: B): MyList[B]
  override def toString : String = s"[ $getElements ]"

  // HOFs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B,C](list: MyList[B], f: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

// Empty is the default list for any type so it should specify the type as Nothing
case object EmptyList extends MyList[Nothing] {
  override def isEmpty: Boolean = true
  override def getElements: String = "$"
  override def head = throw new NoSuchElementException
  override def tail = throw new NoSuchElementException
  override def add[A >: Nothing](i: A): MyList[A] = new Cons(i, EmptyList)

  override def ++[B >: Nothing](l: MyList[B]): MyList[B] = l

  override def map[B](p: Nothing => B): MyList[B] = this

  override def filter(p: Nothing => Boolean): MyList[Nothing] = this

  override def flatMap[B](p: Nothing => MyList[B]): MyList[B] = this

  override def foreach(f: Nothing => Unit): Unit = () // This is NoOp
  override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = this

  override def +[B >: Nothing](item: B): MyList[B] = add(item)

  override def zipWith[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Sizes don't match")
    else this

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

// +A makes it covariant

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def isEmpty: Boolean = false
  override def getElements: String = s"$head, ${tail getElements}"
  override def add[B >: A](i: B): MyList[B] = new Cons(i, this) // this says B is a superclass of A

  override def map[B](p: A => B): MyList[B] = new Cons(p(head), tail.map(p))

  override def filter(p: A => Boolean): MyList[A] =
    if (p(head))
      new Cons(head, t.filter(p))
    else
      t.filter(p)

  override def flatMap[B](p: A => MyList[B]): MyList[B] =
    p(head) ++ tail.flatMap(p)

  override def ++[B >: A](l: MyList[B]): MyList[B] = new Cons(head, tail ++ l) // Magic concatenation
  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyList[A] = {
    @scala.annotation.tailrec
    def insert(a: A, sortedList: MyList[A], before: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) before + a
      else if (compare(a, sortedList.head) <= 0) new Cons(a, sortedList)
      else insert(a, sortedList.tail, before + sortedList.head)
    }
    insert(head, tail.sort(compare), EmptyList)
  }

  override def +[B >: A](item: B): MyList[B] =
    new Cons(head, tail + item)

  override def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Sizes don't match")
    else new Cons(f(head, list.head), tail.zipWith(list.tail, f))

  override def fold[B](start: B)(operator: (B, A) => B): B = {
    val acc = operator(start, head)
    tail.fold(acc)(operator)
  }
}

// This can be replaced with Function1
trait MyPredicate[-T] {
  def test(t: T): Boolean
}

// This can be replaced with Function1
trait MyTransformer[-A, B] {
  def transform(a: A): B
}


object ListTest extends App {
  val list : MyList[Int] = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  val listClone : MyList[Int] = new Cons(1, new Cons(2, new Cons(3, EmptyList)))
  println( list toString )
  val myListInt: MyList[Int] = EmptyList
  val myListString: MyList[String] = EmptyList

  println(list.map(_ * 2) toString)

  println(list.filter(_ % 2 == 0) toString)

  println(list.flatMap((a: Int) => new Cons(a, new Cons(a + 1, EmptyList))) toString)
  // can't use underscore multiple times here (because it means a different argument each time)

  println (list == listClone) // true (because "case" implements hashcode and equals properly)

  list.foreach(println)
  println(list + 4)

  println(list.sort((x,y)=>y-x))

  println(list.zipWith(listClone, (a: Int, b: Int) => a * b))

  println(list.fold(0)(_ + _))
}

