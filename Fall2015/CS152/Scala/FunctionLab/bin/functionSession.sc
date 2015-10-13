object functionSession {
  println("Welcome to the Scala worksheet")
  
  //1.
  def compose[A,B,C] (f: B=>C, g: A=>B): A=>C = {
  	def r(x: A) = f(g(x))
  	r _
  }
  
  def square(x: Int) = x * x
  
  def len(s: String) = s.length
  
  def h = compose(square, len)
  
  h("12345678")
  
  def g = compose(math.sqrt, math.sqrt)
  
  g(81)
  
  //2.
  def selfIter[T] (f: T=> T, n: Int): T=>T = if(n == 0) f else selfIter(compose(f,f), n-1)
  
  def inc(x: Double) = x + 1
  def double(x: Double) = 2 * x
  
  selfIter(inc, 2)
  selfIter(inc, 0)
  selfIter(double, 2)
  selfIter(double, 0)
  
  //3.
  def countPass[T] (pred: T=>Boolean, array: Array[T]) = {
  var pass = 0;
  for(item <- array){ if(pred(item)){pass = pass + 1}}
  pass
  }
  
  def greaterThan2(num: Int) = {
  if(num > 2) true
  else false
  }
  
  val numbers = Array(0,-1,2, 1, 2, 3, 4,5,6,3)
  countPass(greaterThan2,numbers)
  
  //DDS
  def S = {
  var state = 0
  var finalState = false
  
  def isFinal = finalState
  }
  
  def update[S](currentState: S, cycle: Int) = {}
  
  def halt[S] (currentState: S, cycle: Int) {if(currentState("isFinal")) true else false}//if() true else false
  
  def controlLoop[S] (state: S, cycle: Int, half: (S, Int)=>Boolean, update: (S,Int)=>S): S = {state}
  
  //5.
  
  //6.
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}