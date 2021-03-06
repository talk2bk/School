object ShapeMaker {
  
  def makeRectangle(rows: Int, cols: Int) = {
    // place implementation here
    var result = "";
    for(i <- 1 to rows){
      for(j <- 1 to cols){
        result += "*";
      }
      result += "\n";
    }
    result;
  }
   
  def makeRightTriangle(rows: Int) = {
    // place implementation here
    var result = "";
    for(i <- 1 until rows){
      for(j <- 1 to i){
        result += "*";
      }
      result += "\n";
    }
    result;
  }

  def makeIsoTriangle(rows: Int) = {
    // place implementation here
    var temp = 1;
    var result = "";
    while(temp < rows){
      temp = temp+2;
    }
    for(i <- 1 to temp by 2){
      for(k <- 1 to (rows- i/2)){
        result+=" ";
      }
      for(j <- 1 to i){
        result += "*";
        
      }
      result += "\n";
    }
    result;
  }
   
  def makeInvertedTriangle(rows: Int) = {
    // place implementation here
    var temp = 1;
    var result = "";
    while(temp < rows){
      temp = temp+2;
    }
    for(i <- temp to 1 by -2){
      for(k <- (rows - i/2) to 1 by -1){
        result += " ";
      }
      for(j <- i to 1 by -1){
        result += "*";
        
      }
      result += "\n";
    }
    result;
  }

  def main(args: Array[String]): Unit = {
    print("Enter a positive integer: ")
    var n = readInt
    println(makeRectangle(n, n))
    println(makeRightTriangle(n))
    println(makeIsoTriangle(n))
    println(makeInvertedTriangle(n))
  }

}