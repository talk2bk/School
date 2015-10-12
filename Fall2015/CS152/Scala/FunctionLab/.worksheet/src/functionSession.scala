object functionSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(68); 
  println("Welcome to the Scala worksheet");$skip(97); 
  
  //1.
  def compose[A,B,C] (f: B=>C, g: A=>B): A=>C = {
  	def r(x: A) = f(g(x))
  	r _
  };System.out.println("""compose: [A, B, C](f: B => C, g: A => B)A => C""");$skip(33); 
  
  def square(x: Int) = x * x;System.out.println("""square: (x: Int)Int""");$skip(36); 
  
  def len(s: String) = s.length;System.out.println("""len: (s: String)Int""");$skip(35); 
  
  def h = compose(square, len);System.out.println("""h: => String => Int""");$skip(20); val res$0 = 
  
  h("12345678");System.out.println("""res0: Int = """ + $show(res$0));$skip(44); 
  
  def g = compose(math.sqrt, math.sqrt);System.out.println("""g: => Double => Double""");$skip(12); val res$1 = 
  
  g(81);System.out.println("""res1: Double = """ + $show(res$1));$skip(102); 
  
  //2.
  def selfIter[T] (f: T=> T, n: Int): T=>T = if(n == 0) f else selfIter(compose(f,f), n-1);System.out.println("""selfIter: [T](f: T => T, n: Int)T => T""");$skip(32); 
  
  def inc(x: Double) = x + 1;System.out.println("""inc: (x: Double)Double""");$skip(32); 
  def double(x: Double) = 2 * x;System.out.println("""double: (x: Double)Double""");$skip(22); val res$2 = 
  
  selfIter(inc, 2);System.out.println("""res2: Double => Double = """ + $show(res$2));$skip(19); val res$3 = 
  selfIter(inc, 0);System.out.println("""res3: Double => Double = """ + $show(res$3));$skip(22); val res$4 = 
  selfIter(double, 2);System.out.println("""res4: Double => Double = """ + $show(res$4));$skip(22); val res$5 = 
  selfIter(double, 0);System.out.println("""res5: Double => Double = """ + $show(res$5));$skip(151); 
  
  //3.
  def countPass[T] (pred: T=>Boolean, array: Array[T]) = {
  var pass = 0;
  for(item <- array){ if(pred(item)){pass = pass + 1}}
  pass
  };System.out.println("""countPass: [T](pred: T => Boolean, array: Array[T])Int""");$skip(72); 
  
  def greaterThan2(num: Int) = {
  if(num > 2) true
  else false
  };System.out.println("""greaterThan2: (num: Int)Boolean""");$skip(52); 
  
  val numbers = Array(0,-1,2, 1, 2, 3, 4,5,6,3);System.out.println("""numbers  : Array[Int] = """ + $show(numbers ));$skip(34); val res$6 = 
  countPass(greaterThan2,numbers);System.out.println("""res6: Int = """ + $show(res$6))}
  
  //5.
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
