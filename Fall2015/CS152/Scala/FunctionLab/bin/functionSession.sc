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
  
  //DDS
  
  //5.
  def controlLoop[S] (state: S, cycle: Int, halt: (S, Int)=>Boolean, update: (S,Int)=>S): S = {
  if(halt(state,cycle)) state else controlLoop(update(state,cycle+1), cycle+1, halt, update)
  }                                               //> controlLoop: [S](state: S, cycle: Int, halt: (S, Int) => Boolean, update: (
                                                  //| S, Int) => S)S
  
  //6.
  def amoebaHalt[S] (state: Int, cycle: Int): Int = {
  if(state > 10000) cycle else amoebaHalt(state*state, cycle+1)
  }                                               //> amoebaHalt: [S](state: Int, cycle: Int)Int
  
  //7.
  
  def solve(f: Double => Double) =  {
  //guess = guess - f(guess)/f'(guess)
  val delta = 1e-5
  def newtonHalt(guess: Double, cycle: Int) = { math.abs(f(guess)) < delta }
  def derivative(x:Double) = { (f(x+delta) - f(x))/delta  }
  def newtonUpdate(guess: Double, cycle: Int) = { guess - f(guess)/derivative(guess) }
  
  controlLoop(1.0, 0, newtonHalt _, newtonUpdate _)
 
  }                                               //> solve: (f: Double => Double)Double
  
  //8.
  def squareRoot(x: Double) = {
  def root(temp: Double) = math.pow(temp,2)-x
  
  solve(root _)
  }                                               //> squareRoot: (x: Double)Double
  squareRoot(4)                                   //> res7: Double = 2.0000000944796694
  squareRoot(9)                                   //> res8: Double = 3.0000000015508212
  
  //9.
  def cubeRoot(x: Double) = {
  def root(temp: Double) = math.pow(temp,3)-x
  
  solve(root _)
  }                                               //> cubeRoot: (x: Double)Double
  cubeRoot(8)                                     //> res9: Double = 2.000000000036784
  cubeRoot(27)                                    //> res10: Double = 3.000000000001917
  
  //10.
  //math.pow(x, 1.0/n)
  def nthRoot(x: Double, n: Int) = {
  def root(temp: Double) = math.pow(temp, n)-x
  
  solve(root _)
  }                                               //> nthRoot: (x: Double, n: Int)Double
  nthRoot(9,2)                                    //> res11: Double = 3.0000000015508212
  nthRoot(27,3)                                   //> res12: Double = 3.000000000001917

  
  
  
  
  
  
  
  
  
  
  
  
}