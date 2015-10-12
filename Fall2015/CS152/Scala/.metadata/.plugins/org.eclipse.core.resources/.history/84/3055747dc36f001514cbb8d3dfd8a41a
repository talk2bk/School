object functionSession {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //1.
  def compose[A,B,C] (f: B=>C, g: A=>B): A=>C = {
  	def r(x: A) = f(g(x))
  	r _
  }                                               //> compose: [A, B, C](f: B => C, g: A => B)A => C
  
  def square(x: Int) = x * x                      //> square: (x: Int)Int
  
  def len(s: String) = s.length                   //> len: (s: String)Int
  
  def h = compose(square, len)                    //> h: => String => Int
  
  h("12345678")                                   //> res0: Int = 64
  
  def g = compose(math.sqrt, math.sqrt)           //> g: => Double => Double
  
  g(81)                                           //> res1: Double = 3.0
  
  //2.
  def selfIter[T] (f: T=> T, n: Int) = compose(f,f)
                                                  //> selfIter: [T](f: T => T, n: Int)T => T
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}