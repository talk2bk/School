import scala.math._

object mathSession {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
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
solve(4, 0 ,8)                                    //> res0: Any = NaN
}