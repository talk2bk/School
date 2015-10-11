object functionSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(68); 
  println("Welcome to the Scala worksheet");$skip(96); 
  
  //1.
  def compose[A,B,C] (f: B=>C, g: A=>B): A=>C = {
  	def r(x: A) = f(g(x))
  	r _
  };System.out.println("""compose: [A, B, C](f: B => C, g: A => B)A => C""");$skip(32); 
  
  def square(x: Int) = x * x;System.out.println("""square: (x: Int)Int""");$skip(35); 
  
  def len(s: String) = s.length;System.out.println("""len: (s: String)Int""");$skip(34); 
  
  def h = compose(square, len);System.out.println("""h: => String => Int""");$skip(19); val res$0 = 
  
  h("12345678");System.out.println("""res0: Int = """ + $show(res$0));$skip(43); 
  
  def g = compose(math.sqrt, math.sqrt);System.out.println("""g: => Double => Double""");$skip(11); val res$1 = 
  
  g(81);System.out.println("""res1: Double = """ + $show(res$1));$skip(62); 
  
  //2.
  def selfIter[T] (f: T=> T, n: Int) = compose(f,f);System.out.println("""selfIter: [T](f: T => T, n: Int)T => T""")}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
