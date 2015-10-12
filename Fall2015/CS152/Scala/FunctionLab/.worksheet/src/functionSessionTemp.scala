object functionSessionTemp {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(72); 
  println("Welcome to the Scala worksheet");$skip(90); 
  
  def S = {
  var state = 0
  var finalState = false
  
  def isFinal = finalState
  };System.out.println("""S: => Unit""");$skip(53); 
  
  def update[S](currentState: S, cycle: Int) = {};System.out.println("""update: [S](currentState: S, cycle: Int)Unit""");$skip(115); 
  
  def halt[S] (currentState: S, cycle: Int) {if(currentState("isFinal")) true else false};System.out.println("""halt: [S](currentState: S, cycle: Int)Unit""");$skip(105); //if() true else false
  
  def controlLoop[S] (state: S, cycle: Int, half: (S, Int)=>Boolean, update: (S,Int)=>S): S = {state};System.out.println("""controlLoop: [S](state: S, cycle: Int, half: (S, Int) => Boolean, update: (S, Int) => S)S""")}
  
  //5.
  
  //6.
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
