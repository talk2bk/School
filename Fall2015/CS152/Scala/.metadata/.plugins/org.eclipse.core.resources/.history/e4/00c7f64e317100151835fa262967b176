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
  def selfIter[T] (f: T=> T, n: Int): T=>T = if(n == 0) f else selfIter(compose(f,f), n-1)
                                                  //> selfIter: [T](f: T => T, n: Int)T => T
  
  def inc(x: Double) = x + 1                      //> inc: (x: Double)Double
  def double(x: Double) = 2 * x                   //> double: (x: Double)Double
  
  selfIter(inc, 2)                                //> res2: Double => Double = <function1>
  selfIter(inc, 0)                                //> res3: Double => Double = <function1>
  selfIter(double, 2)                             //> res4: Double => Double = <function1>
  selfIter(double, 0)                             //> res5: Double => Double = <function1>
  
  //3.
  def countPass[T] (pred: T=>Boolean, array: Array[T]) = {
  var pass = 0;
  for(item <- array){ if(pred(item)){pass = pass + 1}}
  pass
  }                                               //> countPass: [T](pred: T => Boolean, array: Array[T])Int
  
  def greaterThan2(num: Int) = {
  if(num > 2) true
  else false
  }                                               //> greaterThan2: (num: Int)Boolean
  
  val numbers = Array(0,-1,2, 1, 2, 3, 4,5,6,3)   //> numbers  : Array[Int] = Array(0, -1, 2, 1, 2, 3, 4, 5, 6, 3)
  countPass(greaterThan2,numbers)                 //> res6: Int = 5
  
  //5.
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}