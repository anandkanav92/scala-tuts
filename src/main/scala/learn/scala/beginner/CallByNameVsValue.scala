package learn.scala.beginner

object CallByNameVsValue extends App {
  def callByValue(x: Long): Unit = {
    println("By value", x)
    println("By value", x)
  }

  def callByName(x: => Long): Unit = {
    println("By name", x)
    println("By name", x)
  }

  callByValue(System.nanoTime()) // The expression is evaluated first and then is passed

  callByName(System.nanoTime()) // The expression is passed in as it is and is evaluated every time
  // Hence this makes expression evaluation lazy (and repetitive)

  // It is not evaluated at all till its used
  // It can be used for generator like use cases (infinite streams)
}
