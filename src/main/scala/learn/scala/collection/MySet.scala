package learn.scala.collection

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean) {
  def apply(v1: A): Boolean = contains(v1)
  def contains(x: A): Boolean
  def +(x: A): MySet[A]
  def ++(a: MySet[A]): MySet[A]

  def map[B](f: A=>B): MySet[B]
  def flatMap[B](f: A=>MySet[B]): MySet[B]
  def filter(condition: A=>Boolean): MySet[A]
  def foreach(f: A=>Unit): Unit
  def -(a: A): MySet[A]
  def &(b: MySet[A]): MySet[A]
  def --(b: MySet[A]): MySet[A]

  /**
   * Remove
   * Intersection
   * Difference
   */
}

class EmptySet[A] extends MySet[A] {
  def contains(x: A): Boolean = false
  def +(x: A): MySet[A] = new NonEmptySet[A](x, this)
  def ++(a: MySet[A]): MySet[A] = a

  def map[B](f: A=>B): MySet[B] = new EmptySet[B]
  def flatMap[B](f: A=>MySet[B]): MySet[B] = new EmptySet[B]
  def filter(condition: A=>Boolean): MySet[A] = this
  def foreach(f: A=>Unit): Unit = ()
  def -(a: A): MySet[A] = this
  def &(b: MySet[A]): MySet[A] = this
  def --(b: MySet[A]): MySet[A] = this
}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A] {
  def contains(x: A): Boolean = if (head == x) true else tail.contains(x)
  def +(x: A): MySet[A] = {
    if (this contains(x))
      this
    else
      new NonEmptySet[A](x, this)
  }

  def ++(a: MySet[A]): MySet[A] = tail ++ a + head // Magic

  def map[B](f: A=>B): MySet[B] = (tail map f) + f(head)
  def flatMap[B](f: A=>MySet[B]): MySet[B] = (tail flatMap f) ++ f(head)
  def filter(condition: A=>Boolean): MySet[A] = {
    val filteredTail = tail filter condition
    if (!condition(head)) filteredTail
    else filteredTail + head
  }
  def foreach(f: A=>Unit): Unit = {
    f(head)
    tail foreach f
  }
  def -(a: A): MySet[A] = {
    if (a == head) tail
    else (tail - a) + head
  }
  def &(b: MySet[A]): MySet[A] = {
    if (b contains head) tail & b + head
    else tail & b
  }
  def --(b: MySet[A]): MySet[A] = {
    if (b contains head) tail -- b
    else (tail -- b) + head
  }

}

// Companion object to create MySets!
object MySet {
  def apply[A](args: A*): MySet[A] = {

    // Don't need results of old recursions? Use tailrec
    @tailrec
    def builder(items: Seq[A], acc: MySet[A]): MySet[A] = {
      if (items.isEmpty) acc
      else builder(items.tail, acc + items.head)
    }
    builder(args.toSeq, new EmptySet[A])
  }
}

object Playground extends App {
  val s = MySet(1,2,3,4,4,5)
  s foreach println
  s - 4 foreach println

}