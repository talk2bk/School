import Array._

object MatrixCalculator {
  
  // converts matrix to a string
  def toString(matrix: Array[Array[Int]]) = {
    // place code here
    var result = "";
    for(i <- matrix){
      for(j <- i){
        result += j + " ";
      }
     
      result += "\n";
      
    }
    
    result;
  }
  
  // returns the sum of the diagonal entries of a matrix
  def trace(m: Array[Array[Int]]) = {
    // place code here
    var result = 0;
    for(i <- 0 until m.length){
      for(j <- 0 until m.length){
        result += m(i)(i);
      }
    }
    result;
  }
  
  // returns a dim x dim matrix with i/j entry = 3 * i + 2 * j % cap
  def makeArray(dim: Int, cap: Int = 100) = {
    // place code here
    var array = ofDim[Int](dim,dim);
    for(i <- 0 until dim){
      for(j <- 0 until dim){
        array(i)(j) = 3*i + (2*j)%cap;
      }
    }
    
    array;
  }

  def main(args: Array[String]): Unit = {
    print("Enter a positive integer: ");
    var n = readInt();
    var m = makeArray(n);
    println(toString(m));
    println("trace = " + trace(m));
  }

}