object listSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(64); 
  println("Welcome to the Scala worksheet");$skip(159); 
  
  //1.
  //iterative
  def sumOfCubes(list: Array[Int]) = {
  var result = 0.0
  for(x <- list){if(x%2 != 0){result = result + math.pow(x,3)}}
  result
  };System.out.println("""sumOfCubes: (list: Array[Int])Double""");$skip(37); val res$0 = 
  
  sumOfCubes(Array(2,0, 3, 4, 5));System.out.println("""res0: Double = """ + $show(res$0));$skip(25); val res$1 = 
  sumOfCubes(Array(1,3));System.out.println("""res1: Double = """ + $show(res$1));$skip(128); 
 	//recursive
 	//tailrecursive
 	//mapfilterreduce
 	
 	//2.
 	//iterative
 	def sumOfSums(list: Array[Array[Int]]) = {
 	
 	};System.out.println("""sumOfSums: (list: Array[Array[Int]])Unit""")}
  
  
  
  
  
  
  
  
  
  
  
  
  
}
