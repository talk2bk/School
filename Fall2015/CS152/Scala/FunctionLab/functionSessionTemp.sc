object functionSessionTemp {
  println("Welcome to the Scala worksheet")
  
  def S = {
  var state = 0
  var finalState = false
  
  def isFinal = finalState
  }
  
  def update[S](currentState: S, cycle: Int) = {}
  
  def halt[S] (currentState: S, cycle: Int) {if(currentState("isFinal")) true else false}//if() true else false
  
  def controlLoop[S] (state: S, cycle: Int, half: (S, Int)=>Boolean, update: (S,Int)=>S): S = {state}
  
  //5.
  
  //6.
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}