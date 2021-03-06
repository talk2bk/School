object VectorCalculator {
  
  def add(v1: Array[Int], v2: Array[Int]) = {
    // complete this method
    var result = new Array[Int](v1.length);
    
    for(i <- 0 until v1.length){
      result(i) = v1(i) + v2(i);
    }
    result;
  }
  
  def dot(v1: Array[Int], v2: Array[Int]) = {
    // complete this method
    var result = 0;
    
    for(i <- 0 until v1.length){
      result += v1(i)*v2(i);
    }
    result;
  }
  
  def toString(v: Array[Int]) = {
    var result = "["
    for(e <- v) {
      result = result + " " + e
    }
    result = result + "]"
    result
  }

  def main(args: Array[String]): Unit = {
    try {
      print("Enter 3 integers: ")
      var x = readInt()
      var y = readInt()
      var z = readInt()
      val vec1 = Array(x, y, z)
      val vec2 = Array(1, 2, 3)
      val vec3 = add(vec1, vec2)
      println("sum = " + toString(vec3))
      println("dot = " + dot(vec1, vec2))
    } catch {
         case e: Exception => {println(e)}
    }
    
  }

}