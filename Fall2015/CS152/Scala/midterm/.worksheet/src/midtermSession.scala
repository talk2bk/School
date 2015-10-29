object midtermSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(67); 
  println("Welcome to the Scala worksheet");$skip(153); 
 //2.
 def approxZero(num: Any): Boolean = num.asInstanceOf[Comparable[Any]].compareTo(0.1) < 0 && num.asInstanceOf[Comparable[Any]].compareTo(-0.1) > 0;System.out.println("""approxZero: (num: Any)Boolean""");$skip(37); 
 def self(num: Double): Double = num;System.out.println("""self: (num: Double)Double""");$skip(98); 
 
def countRoots[T](f: T => Double, inputs: List[T]) = inputs.map(f).filter(approxZero _).length;System.out.println("""countRoots: [T](f: T => Double, inputs: List[T])Int""");$skip(52); 
val numbers = List(0.01, 0.1, 1, 0.001, -0.001, -1);System.out.println("""numbers  : List[Double] = """ + $show(numbers ));$skip(50); 
val numbers2 = List(0.001, 0.00001, 0.0001, 0.01);System.out.println("""numbers2  : List[Double] = """ + $show(numbers2 ));$skip(33); 
val numbers3 = List(1.0,2,3,4,5);System.out.println("""numbers3  : List[Double] = """ + $show(numbers3 ));$skip(26); val res$0 = 
countRoots(self, numbers);System.out.println("""res0: Int = """ + $show(res$0));$skip(27); val res$1 = 
countRoots(self, numbers2);System.out.println("""res1: Int = """ + $show(res$1));$skip(27); val res$2 = 
countRoots(self, numbers3);System.out.println("""res2: Int = """ + $show(res$2));$skip(59); 

//3.
def fact(n: Int): Int = if(n ==0) 1 else n*fact(n-1);System.out.println("""fact: (n: Int)Int""");$skip(149); 

def recur(base: Int, combiner: (Int, Int) => Int): Int => Int = {
def func(n : Int): Int = { if(n == 0) base else combiner(n, func(n-1)) }
func _
};System.out.println("""recur: (base: Int, combiner: (Int, Int) => Int)Int => Int""");$skip(50); 

def mult(first: Int, second: Int) = first*second;System.out.println("""mult: (first: Int, second: Int)Int""");$skip(31); 
val recurFact = recur(1, mult);System.out.println("""recurFact  : Int => Int = """ + $show(recurFact ));$skip(14); val res$3 = 

recurFact(1);System.out.println("""res3: Int = """ + $show(res$3));$skip(13); val res$4 = 
recurFact(2);System.out.println("""res4: Int = """ + $show(res$4));$skip(13); val res$5 = 
recurFact(3);System.out.println("""res5: Int = """ + $show(res$5));$skip(13); val res$6 = 
recurFact(4);System.out.println("""res6: Int = """ + $show(res$6));$skip(269); 

//4.
//a.
def some(vals: List[Int], test: Int => Boolean): Boolean = {
def helper(vals2: List[Int], test2: Int =>Boolean, pass: Boolean): Boolean = {
if(vals2 == Nil) pass else if(pass) true else helper(vals2.tail, test2, test2(vals2.head))}
helper(vals,test,false)
};System.out.println("""some: (vals: List[Int], test: Int => Boolean)Boolean""");$skip(47); val res$7 = 

some(List(1, 3, 5, 7), (n: Int)=> n % 2 == 0);System.out.println("""res7: Boolean = """ + $show(res$7));$skip(46); val res$8 = 
some(List(1, 3, 5, 8), (n: Int)=> n % 2 == 0);System.out.println("""res8: Boolean = """ + $show(res$8));$skip(94); 

//b.
def some2(vals:List[Int], test: Int => Boolean): Boolean = vals.filter(test).length > 0;System.out.println("""some2: (vals: List[Int], test: Int => Boolean)Boolean""");$skip(47); val res$9 = 
some2(List(1, 3, 5, 7), (n: Int)=> n % 2 == 0);System.out.println("""res9: Boolean = """ + $show(res$9));$skip(47); val res$10 = 
some2(List(1, 3, 5, 8), (n: Int)=> n % 2 == 0);System.out.println("""res10: Boolean = """ + $show(res$10))}

}
