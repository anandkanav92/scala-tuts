package learn.scala.collection

abstract class Maybe[+A] {
  def map[B](p: A=>B): Maybe[B]
  def flatMap[B](t: A=>Maybe[B]): Maybe[B]
  def filter(p: A=>Boolean): Maybe[A]
}

case object MaybeNot extends Maybe[Nothing] {
  override def map[B](p: Nothing => B): Maybe[B] = this

  override def flatMap[B](t: Nothing => Maybe[B]): Maybe[B] = this

  override def filter(p: Nothing => Boolean): Maybe[Nothing] = this
}

case class MaybeYes[+A](item: A) extends Maybe[A] {
  override def map[B](p: A => B): Maybe[B] = new MaybeYes[B](p(item))

  override def flatMap[B](t: A => Maybe[B]): Maybe[B] = t(item)

  override def filter(p: A => Boolean): Maybe[A] =
    if (p(item))
      this
    else
      MaybeNot
}

object MaybeTester extends App {
  val maybe: Maybe[Int] = new MaybeYes(5)
  println(maybe.filter(_%5 != 0))
  println(maybe.map(_*5))
  println(maybe.flatMap(x=>MaybeYes(x+3)))
}