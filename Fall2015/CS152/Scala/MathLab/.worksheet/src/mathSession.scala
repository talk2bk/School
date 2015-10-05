import scala.math._
import scala.util._

object mathSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(105); 
  println("Welcome to the Scala worksheet");$skip(366); 
 //1.
  def solve(a: Double, b: Double, c: Double) = {
  //discriminant is either negative (no answer), 0 (??), positive(calculate square root)
  
  val disc = b*b - 4*a*c
 	if(disc < 0) {
 	Double.NaN
 	} else if (disc == 0){
 		-b / (2*a)
 	} else{
 		val sol1 = (-b + math.sqrt(disc)) / (2*a)
 		val sol2 = (-b - math.sqrt(disc)) / (2*a)
 			(sol1, sol2)
 	}
  };System.out.println("""solve: (a: Double, b: Double, c: Double)Any""");$skip(18); val res$0 = 

solve(1.0, 6, 8);System.out.println("""res0: Any = """ + $show(res$0));$skip(39); val res$1 = 
// Any = (-2.0,-4.0)
solve(1, -8 , 16);System.out.println("""res1: Any = """ + $show(res$1));$skip(29); val res$2 = 
// Any = 4.0
solve(1, 0, 16);System.out.println("""res2: Any = """ + $show(res$2));$skip(214); 
 //Any = NaN

//2.
def dist(point1: (Int,Int), point2: (Int,Int)) = {
	var (point1X, point1Y) = point1
	var (point2X, point2Y) = point2
	Math.sqrt(Math.pow(point2X - point1X,2)+Math.pow(point2Y-point1Y,2))
	
	
	
};System.out.println("""dist: (point1: (Int, Int), point2: (Int, Int))Double""");$skip(22); val res$3 = 

dist((3, 0), (0, 0));System.out.println("""res3: Double = """ + $show(res$3));$skip(27); val res$4 = 
//3.0
dist((1, 1), (0, 0));System.out.println("""res4: Double = """ + $show(res$4));$skip(188); 
//1.4142135623730951

//3.
def dot(matrix1: (Double, Double, Double), matrix2: (Double, Double, Double)) = {
var (v1, v2, v3) = matrix1
var (v4, v5, v6) = matrix2

v1*v4 + v2*v5 + v3*v6
};System.out.println("""dot: (matrix1: (Double, Double, Double), matrix2: (Double, Double, Double))Double""");$skip(31); val res$5 = 

dot((2.0, 3, 4), (2, 2.0, 2));System.out.println("""res5: Double = """ + $show(res$5));$skip(68); 
// = 18.0

//4.
def force(m1: Int, m2: Int, d: Int) = {
(m1*m2)/d
};System.out.println("""force: (m1: Int, m2: Int, d: Int)Int""");$skip(109); 
//5.
def mean(array: Array[Double])= {
var result = 0.0;
for(i <- array){
	result+=i
}
result/array.length
};System.out.println("""mean: (array: Array[Double])Double""");$skip(188); 

def stdDev(array: Array[Double]) = {
var m = mean(array);
var moreMeans = new Array[Double](array.length)
for(i <- array){
moreMeans :+=  Math.pow(i-m,2)


}
Math.sqrt(mean(moreMeans))
};System.out.println("""stdDev: (array: Array[Double])Double""");$skip(27); val res$6 = 

mean(Array(2.0, 3, 4, 5));System.out.println("""res6: Double = """ + $show(res$6));$skip(36); val res$7 = 
//= 3.5
stdDev(Array(2, 3.0, 4, 5));System.out.println("""res7: Double = """ + $show(res$7));$skip(208); 

//6.
def isPrime(n: Int) = {
if(n < 0) throw new IllegalArgumentException("less than 0")
else if(n < 2) false
else if(n==2) true

else{
var result = false
for(i <- 2 until n){
result = n%i == 0
}
result
}
};System.out.println("""isPrime: (n: Int)Boolean""");$skip(12); val res$8 = 

isPrime(0);System.out.println("""res8: Boolean = """ + $show(res$8));$skip(22); val res$9 = 
// = false
isPrime(1);System.out.println("""res9: Boolean = """ + $show(res$9));$skip(22); val res$10 = 
// = false
isPrime(2);System.out.println("""res10: Boolean = """ + $show(res$10));$skip(21); val res$11 = 
// = true
isPrime(8);System.out.println("""res11: Boolean = """ + $show(res$11));$skip(77); 
// = false

//7.
def gcd(a: Int, b: Int): Int = if (b==0) a else gcd(b, a%b);System.out.println("""gcd: (a: Int, b: Int)Int""");$skip(115); 

def phi(n: Int) = {
var count = 0
for(i <- 0 to Math.abs(n)){
if(gcd(i,Math.abs(n)) == 1) count=count+1
}
count
};System.out.println("""phi: (n: Int)Int""");$skip(8); val res$12 = 

phi(9);System.out.println("""res12: Int = """ + $show(res$12));$skip(15); val res$13 = 
// = 6
phi(-9);System.out.println("""res13: Int = """ + $show(res$13));$skip(106); 
// = 6

//8.
def rollDice():(Int,Int) = {
var x = Random.nextInt(6)
var y = Random.nextInt(6)
(x+1,y+1)
};System.out.println("""rollDice: ()(Int, Int)""");$skip(13); val res$14 = 

rollDice();System.out.println("""res14: (Int, Int) = """ + $show(res$14));$skip(11); val res$15 = 
rollDice();System.out.println("""res15: (Int, Int) = """ + $show(res$15));$skip(11); val res$16 = 
rollDice();System.out.println("""res16: (Int, Int) = """ + $show(res$16))}


}
