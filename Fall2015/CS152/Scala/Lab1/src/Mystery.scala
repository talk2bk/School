object Mystery {

  def main(args: Array[String]): Unit = {
    // Enter code here
    Console.print("enter a positive integer: ");
    
    var r = scala.util.Random;
    var k: Int = Console.readInt();
    
    for(i <- 0 until 10){
      println("trial "+ i);
      for(j <- 0 until 100){
        //int k = r.nextInt(100);
        k = (k*k)%100;
        if(k < j/3) {}
        if(k < j/2) return
        println("k = "+ k);
      }
    }
    
  }
}