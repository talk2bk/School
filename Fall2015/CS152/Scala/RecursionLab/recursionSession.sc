object recursionSession {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  def inc(n: Int) = n + 1                         //> inc: (n: Int)Int
  def dec(n: Int) = n - 1                         //> dec: (n: Int)Int
  
  //5.
  //n + m
  def add(n: Int, m: Int) = {
  
  def addHelp(n: Int, count: Int): Int = if(count <= 0) n else addHelp(inc(n), dec(count))
  
  addHelp(n,m)
  }                                               //> add: (n: Int, m: Int)Int
   
  add(1,2)                                        //> res0: Int = 3
  add(2,3)                                        //> res1: Int = 5
  add(3,4)                                        //> res2: Int = 7
   
  //n * m
  def mul(n: Int, m: Int) = {
  
  def mulHelp(result: Int, count: Int): Int = if(count <= 0) result else mulHelp(add(result,n), dec(count))
  
  mulHelp(0,m)
  }                                               //> mul: (n: Int, m: Int)Int
  
  mul(3,0)                                        //> res3: Int = 0
  mul(1,2)                                        //> res4: Int = 2
  mul(2,3)                                        //> res5: Int = 6
  mul(3,4)                                        //> res6: Int = 12
  
  //2^m, pow(2,m)
  def exp2(m: Int) = {
  
  def expHelp(result: Int, count: Int): Int = if(count <= 0) result else expHelp(mul(result,2), dec(count))
  expHelp(1,m)
  }                                               //> exp2: (m: Int)Int
  
  exp2(0)                                         //> res7: Int = 1
  exp2(1)                                         //> res8: Int = 2
  exp2(2)                                         //> res9: Int = 4
  exp2(3)                                         //> res10: Int = 8
  
  //exp(exp(exp(exp(1))))
  def hyperExp(m: Int) = {
  
 def hyperExpHelper(result: Int, count: Int): Int = if(count <= 0) result else hyperExpHelper(exp2(result), dec(count))
 
 hyperExpHelper(2, m)
  }                                               //> hyperExp: (m: Int)Int
  
  hyperExp(0)                                     //> res11: Int = 2
  hyperExp(1)                                     //> res12: Int = 4
  hyperExp(2)                                     //> res13: Int = 16
  hyperExp(3)                                     //> res14: Int = 65536
  hyperExp(4)                                     //> res15: Int = 0
  
  //7.
  
	def REPL: Double = {
	var cmmd = readLine("-> ").split("\\s+")
	var continue = true;
  if (cmmd.length != 3) {throw new Exception("syntax = NUMBER OPERATOR NUMBER")}
	
	def replHelper(arg1: Double, operation: String, arg2: Double) = {
						if (operation == "+") {println("result = " + (arg1 + arg2)); arg1 + arg2}
						else if (operation == "*") { println("result = " + (arg1 * arg2)); (arg1 * arg2)}
						else if (operation == "-") { println("result = " + (arg1 - arg2)); (arg1 - arg2)}
						else if (operation == "/") { println("result = " + (arg1 / arg2)); (arg1 / arg2)}
						else {throw new Exception("unrecognized operator: " + operation) }
	}
	
	replHelper(cmmd(0).toDouble, cmmd(1).toString, cmmd(2).toDouble)
	REPL
	}                                         //> REPL: => Double
  
  //9.
  //recursive
  def fib1(n: Int): Int = {
  if(n <= 1) 1 else fib1(n-1) + fib1(n-2)
  }                                               //> fib1: (n: Int)Int
  
  //tail
  def fib3(n: Int) = {
  	def helper(fib1: Int, fib2: Int, i: Int): Int = {
  	if(i <= 0) fib2 else helper(fib2, fib2+fib1, i - 1)
  	}
  	helper(1,1,n)
  }                                               //> fib3: (n: Int)Int
 
 for(i <- 0 to 10){
 println(fib3(i))                                 //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 5
                                                  //| 8
                                                  //| 13
                                                  //| 21
                                                  //| 34
                                                  //| 55
                                                  //| 89
                                                  //| 144
   }
  
  
  
  //10.
  def choose(n: Int, m: Int): Int = {
  
 	if(m == 1) n
 	else if(n == m) 1
 	else choose(n-1,m-1) + choose(n-1,m)
 	
  }                                               //> choose: (n: Int, m: Int)Int
  
  /*
  //10.
  def choose(n: Int, m: Int) = {
  if(n == 0) 0
  def fact(n: Int): Int = fact2(n,1)
  def fact2(n: Int, result: Int): Int = if(n==0) result else fact2(n-1, n*result)
 	fact(n)/fact(m)*fact(n-m)
  }
  */
  
  choose(9,3)                                     //> res16: Int = 84
  choose(2,1)                                     //> res17: Int = 2
  choose(4,3)                                     //> res18: Int = 4
  choose(5,4)                                     //> res19: Int = 5
}

object Calculator{
def REPL: Double = {
	var cmmd = readLine("-> ").split("\\s+")
	var continue = true;
	if(cmmd(0).equals("quit")) {println("bye"); System.exit(1)}
  else {if (cmmd.length != 3) {throw new Exception("syntax = NUMBER OPERATOR NUMBER")}}
	
	def replHelper(arg1: Double, operation: String, arg2: Double) = {
						if (operation == "+") {println("result = " + (arg1 + arg2)); arg1 + arg2}
						else if (operation == "*") { println("result = " + (arg1 * arg2)); (arg1 * arg2)}
						else if (operation == "-") { println("result = " + (arg1 - arg2)); (arg1 - arg2)}
						else if (operation == "/") { println("result = " + (arg1 / arg2)); (arg1 / arg2)}
						else {throw new Exception("unrecognized operator: " + operation) }
	}
	
	replHelper(cmmd(0).toDouble, cmmd(1).toString, cmmd(2).toDouble)
	REPL
	}
}