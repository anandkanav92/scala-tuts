package learn.scala.beginner

import scala.annotation.tailrec

object Recursion extends App {
  // stack and tail recursions

  // This is not a tail recursive function
  // All the stack frames need to be preserved for intermediate calculations
  def factorial(a: Int): BigInt =
    if (a <= 1) 1 else a * factorial(a-1)
  // println(factorial(50000)) // Will result in stack overflow

  // If the last expression of a function is a recursive call to itself,
  // the function doesn't need to preserve all stack frames and can be iterative
  // These recursive functions are called TAIL-RECURSIVE
  def factorialTailRecursive(a: Int): BigInt = {
    @tailrec
    def factHelper(n: Int, accum: BigInt): BigInt =
      if (n<=1) accum else factHelper(n-1, accum*n)  // TAIL RECURSION
    factHelper(a, 1)
  }

  println(factorialTailRecursive(5))
  // println(factorialTailRecursive(50000)) // This works now.
  // ALWAYS TRY TO MAKE RECURSIVE FUNCTIONS TAIL RECURSIVE

  // You could also rewrite the above with default value for accumulator
  // making the nested function unnecessary :)
  @tailrec
  def factHelper(n: Int, accum: BigInt = 1): BigInt =
    if (n<=1) accum else factHelper(n-1, accum*n)  // TAIL RECURSION

  // Tail recursive fibbonacci function
  def fibbo(n: Int): Int  = {
    @tailrec
    def fibboHelper(a: Int, accum1: Int, accum2: Int): Int  =
      if (a >= n) accum1 + accum2 else fibboHelper(a + 1, accum2, accum2 + accum1)
    fibboHelper(2, 0, 1)
  }

  // To convert a regular recursive function to tail recursive,
  // You'll need to use this accumulator pattern
  // Number of accumulators = number of recursive calls.

  println(fibbo(n=4)) // You can also send value to a function with the name
  // (and send arguments out of order like in python)
}
