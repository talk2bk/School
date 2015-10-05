object recursionSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(69); 
  println("Welcome to the Scala worksheet");$skip(26); 
  def inc(n: Int) = n + 1;System.out.println("""inc: (n: Int)Int""");$skip(26); 
  def dec(n: Int) = n - 1;System.out.println("""dec: (n: Int)Int""");$skip(163); 
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
  exp2(3);System.out.println("""res10: Int = """ + $show(res$10));$skip(137); 
  
  //exp(exp(exp(exp(1))))
  def hyperExp(m: Int) = {
  
 def hyperExpHelper(result: Int, count: Int): Int =
 hyperExpHelper(0, m)
  };System.out.println("""hyperExp: (m: Int)Unit""")}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
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
