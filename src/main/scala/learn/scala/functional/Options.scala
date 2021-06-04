package learn.scala.functional

import scala.util.Random

object Options extends App {

  // Just implemented like Maybe
  // Heavily used to avoid Null pointer exceptions

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  // Unsafe APIs
  def unsafeMethod(): String = null

  val result = Some(unsafeMethod()) // WRONG
  val resultCorrect = Option(unsafeMethod())
  println(resultCorrect)

  // chained methods
  def backupMethod(): String = "Valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // Design unsafe APIs
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("Valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // Functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DON'T USE

  // map flatMap filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(_ > 10))
  println(myFirstOption.flatMap(x => Option(x*10)))

  // for comprehensions also available

  // These values might not be present
  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected"
  }
  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }


  // Try to establish a connection, if you can, print the connect method
  val host = config.get("host")
  val port = config.get("port")
  println("Options using flatMap and Map")
  host.flatMap(h =>
        port.flatMap(p=>
          Connection(h,p))
        .map(c=>c.connect))
      .foreach(println)

  println("For comprehensions")
  val connStatus: Option[String] = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  connStatus.foreach(println)
}
