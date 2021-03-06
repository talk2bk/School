import scala.math._
import scala.util._

object mathSession {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
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
  }                                               //> solve: (a: Double, b: Double, c: Double)Any

solve(1.0, 6, 8)                                  //> res0: Any = (-2.0,-4.0)
// Any = (-2.0,-4.0)
solve(1, -8 , 16)                                 //> res1: Any = 4.0
// Any = 4.0
solve(1, 0, 16)                                   //> res2: Any = NaN
 //Any = NaN

//2.
def dist(point1: (Int,Int), point2: (Int,Int)) = {
	var (point1X, point1Y) = point1
	var (point2X, point2Y) = point2
	Math.sqrt(Math.pow(point2X - point1X,2)+Math.pow(point2Y-point1Y,2))
	
	
	
}                                                 //> dist: (point1: (Int, Int), point2: (Int, Int))Double

dist((3, 0), (0, 0))                              //> res3: Double = 3.0
//3.0
dist((1, 1), (0, 0))                              //> res4: Double = 1.4142135623730951
//1.4142135623730951

//3.
def dot(matrix1: (Double, Double, Double), matrix2: (Double, Double, Double)) = {
var (v1, v2, v3) = matrix1
var (v4, v5, v6) = matrix2

v1*v4 + v2*v5 + v3*v6
}                                                 //> dot: (matrix1: (Double, Double, Double), matrix2: (Double, Double, Double))
                                                  //| Double

dot((2.0, 3, 4), (2, 2.0, 2))                     //> res5: Double = 18.0
// = 18.0

//4.
def force(m1: Int, m2: Int, d: Int) = {
(m1*m2)/d
}                                                 //> force: (m1: Int, m2: Int, d: Int)Int
//5.
def mean(array: Array[Double])= {
var result = 0.0;
for(i <- array){
	result+=i
}
result/array.length
}                                                 //> mean: (array: Array[Double])Double

def stdDev(array: Array[Double]) = {
var m = mean(array);
var moreMeans = new Array[Double](array.length)
for(i <- array){
moreMeans :+=  Math.pow(i-m,2)


}
Math.sqrt(mean(moreMeans))
}                                                 //> stdDev: (array: Array[Double])Double

mean(Array(2.0, 3, 4, 5))                         //> res6: Double = 3.5
//= 3.5
stdDev(Array(2, 3.0, 4, 5))                       //> res7: Double = 0.7905694150420949

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
}                                                 //> isPrime: (n: Int)Boolean

isPrime(0)                                        //> res8: Boolean = false
// = false
isPrime(1)                                        //> res9: Boolean = false
// = false
isPrime(2)                                        //> res10: Boolean = true
// = true
isPrime(8)                                        //> res11: Boolean = false
// = false

//7.
def gcd(a: Int, b: Int): Int = if (b==0) a else gcd(b, a%b)
                                                  //> gcd: (a: Int, b: Int)Int

def phi(n: Int) = {
var count = 0
for(i <- 0 to Math.abs(n)){
if(gcd(i,Math.abs(n)) == 1) count=count+1
}
count
}                                                 //> phi: (n: Int)Int

phi(9)                                            //> res12: Int = 6
// = 6
phi(-9)                                           //> res13: Int = 6
// = 6

//8.
def rollDice():(Int,Int) = {
var x = Random.nextInt(6)
var y = Random.nextInt(6)
(x+1,y+1)
}                                                 //> rollDice: ()(Int, Int)

rollDice()                                        //> res14: (Int, Int) = (1,3)
rollDice()                                        //> res15: (Int, Int) = (2,4)
rollDice()                                        //> res16: (Int, Int) = (1,6)


}