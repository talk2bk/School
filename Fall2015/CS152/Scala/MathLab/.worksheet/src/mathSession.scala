import scala.math._

object mathSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(85); 
  println("Welcome to the Scala worksheet");$skip(363); 
  
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
  };System.out.println("""solve: (a: Double, b: Double, c: Double)Any""");$skip(15); val res$0 = 
solve(4, 0 ,8);System.out.println("""res0: Any = """ + $show(res$0))}
}
