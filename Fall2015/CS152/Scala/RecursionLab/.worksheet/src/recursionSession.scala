object recursionSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(69); 
  println("Welcome to the Scala worksheet");$skip(26); 
  def inc(n: Int) = n + 1;System.out.println("""inc: (n: Int)Int""");$skip(26); 
  def dec(n: Int) = n - 1;System.out.println("""dec: (n: Int)Int""");$skip(167); 
  
  //5.
  //n + m
  def add(n: Int, m: Int) = {
  
  def addHelp(n: Int, count: Int): Int = if(count <= 0) n else addHelp(inc(n), dec(count))
  
  addHelp(n,m)
  };System.out.println("""add: (n: Int, m: Int)Int""");$skip(15); val res$0 = 
   
  add(1,2);System.out.println("""res0: Int = """ + $show(res$0));$skip(11); val res$1 = 
  add(2,3);System.out.println("""res1: Int = """ + $show(res$1));$skip(11); val res$2 = 
  add(3,4);System.out.println("""res2: Int = """ + $show(res$2));$skip(177); 
   
  //n * m
  def mul(n: Int, m: Int) = {
  
  def mulHelp(result: Int, count: Int): Int = if(count <= 0) result else mulHelp(add(result,n), dec(count))
  
  mulHelp(0,m)
  };System.out.println("""mul: (n: Int, m: Int)Int""");$skip(14); val res$3 = 
  
  mul(3,0);System.out.println("""res3: Int = """ + $show(res$3));$skip(11); val res$4 = 
  mul(1,2);System.out.println("""res4: Int = """ + $show(res$4));$skip(11); val res$5 = 
  mul(2,3);System.out.println("""res5: Int = """ + $show(res$5));$skip(11); val res$6 = 
  mul(3,4);System.out.println("""res6: Int = """ + $show(res$6));$skip(174); 
  
  //2^m, pow(2,m)
  def exp2(m: Int) = {
  
  def expHelp(result: Int, count: Int): Int = if(count <= 0) result else expHelp(mul(result,2), dec(count))
  expHelp(1,m)
  };System.out.println("""exp2: (m: Int)Int""");$skip(13); val res$7 = 
  
  exp2(0);System.out.println("""res7: Int = """ + $show(res$7));$skip(10); val res$8 = 
  exp2(1);System.out.println("""res8: Int = """ + $show(res$8));$skip(10); val res$9 = 
  exp2(2);System.out.println("""res9: Int = """ + $show(res$9));$skip(10); val res$10 = 
  exp2(3);System.out.println("""res10: Int = """ + $show(res$10));$skip(207); 
  
  //exp(exp(exp(exp(1))))
  def hyperExp(m: Int) = {
  
 def hyperExpHelper(result: Int, count: Int): Int = if(count <= 0) result else hyperExpHelper(exp2(result), dec(count))
 
 hyperExpHelper(2, m)
  };System.out.println("""hyperExp: (m: Int)Int""");$skip(17); val res$11 = 
  
  hyperExp(0);System.out.println("""res11: Int = """ + $show(res$11));$skip(14); val res$12 = 
  hyperExp(1);System.out.println("""res12: Int = """ + $show(res$12));$skip(14); val res$13 = 
  hyperExp(2);System.out.println("""res13: Int = """ + $show(res$13));$skip(14); val res$14 = 
  hyperExp(3);System.out.println("""res14: Int = """ + $show(res$14));$skip(14); val res$15 = 
  hyperExp(4);System.out.println("""res15: Int = """ + $show(res$15));$skip(750); 
  
  //7.
  
	def REPL(): Double = {
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
	REPL()
	};System.out.println("""REPL: ()Double""");$skip(98); 
  
  //9.
  //recursive
  def fib1(n: Int): Int = {
  if(n <= 1) 1 else fib1(n-1) + fib1(n-2)
  };System.out.println("""fib1: (n: Int)Int""");$skip(169); 
  
  //tail
  def fib3(n: Int) = {
  	def helper(fib1: Int, fib2: Int, i: Int): Int = {
  	if(i <= 0) fib2 else helper(fib2, fib2+fib1, i - 1)
  	}
  	helper(1,1,n)
  };System.out.println("""fib3: (n: Int)Int""");$skip(40); 
 
 for(i <- 0 to 10){
 println(fib3(i))
   };$skip(221); 
  
  
  
  //10.
  def choose(n: Int, m: Int) = {
  if(n == 0) 0
  def fact(n: Int): Int = fact2(n,1)
  def fact2(n: Int, result: Int): Int = if(n==0) result else fact2(n-1, n*result)
 	fact(n)/fact(m)*fact(n-m)
  };System.out.println("""choose: (n: Int, m: Int)Int""");$skip(17); val res$16 = 
  
  choose(1,0);System.out.println("""res16: Int = """ + $show(res$16));$skip(14); val res$17 = 
  choose(2,1);System.out.println("""res17: Int = """ + $show(res$17));$skip(14); val res$18 = 
  choose(4,3);System.out.println("""res18: Int = """ + $show(res$18));$skip(14); val res$19 = 
  choose(5,4);System.out.println("""res19: Int = """ + $show(res$19))}
  
  
  
  
  
  
  
  
  
  
  

}
