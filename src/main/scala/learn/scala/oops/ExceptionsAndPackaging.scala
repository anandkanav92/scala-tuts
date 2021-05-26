package learn.scala.oops

object ExceptionsAndPackaging extends App {
  val x: String = null
  // println(x.length) // Throws a Null Pointer Exception

  // throw new ExceptionClass
  // Class needs to extend Throwable so it can be thrown
  // Exception and Error are the major throwable subtypes

  def getInt(x: Int) : Int = {
    if (x < 10) throw new RuntimeException("You're weak") else 42
  }
  try {
    // code that throws
    getInt(9)
  } catch {
    case e:RuntimeException => println("Caught a NP exception : " + e.getMessage)
  } finally {
    // Optional block
    // Does no influence the return type for this expression
    // only use this for logging
    println("finally")
  }

  val xo = try {
    11
  } catch {
    case e:NullPointerException => 13
  }
  // xo is of type Int

  class Lol extends Exception
  val lol = new Lol
  // throw lol // can be thrown like this

  // Out of memory
  //  val arr = Array.ofDim(Int.MaxValue)
  //  println(arr.length)

  // Stack overflow
  // def infinite : Int = 1 + infinite
  // val inf = infinite


  // Packaging
  // 1.
  // import java.lang.{Date, Appendable => Append}
  // This is how you import multiple classes and rename some of them to avoid conflicts

  // 2. You can of course use fully qualified class names in the code directly without importing

  // 3. import java.lang._ imports every class in that package

  // 4. Scala also has package_objects (package level constants that don't need to be imported)
}
