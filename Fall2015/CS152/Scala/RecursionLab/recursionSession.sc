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
  
 def hyperExpHelper(result: Int, count: Int): Int =
 hyperExpHelper(0, m)
  }                                               //> hyperExp: (m: Int)Unit
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  //7.
  
  //?????
  
  /*
    //9.
  //recursive
  def fib1(n: Int): Int = {
  if(n <= 1) 1 else fib1(n-1) + fib1(n-2)
  }
  
  //tail
  def fib3(n: Int) = {
  	def helper(fib1: Int, fib2: Int, i: Int): Int = {
  	if(i <= 0) fib2 else helper(fib2, fib2+fib1, i - 1)
  	}
  	helper(1,1,n)
  }
 
 for(i <- 0 to 10){
 println(fib3(i))
   }
  
  
  */
  
  //10.
  
  //?????
  
  
  
  
  
  
  
  
  
  
  
  

}