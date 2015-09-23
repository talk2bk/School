object Calculator {

   def fact(n: Integer) = {
     // place iterative solution here
     var result = 1;
     for(i <- 1 to n){
       result *= i;
     }
     result;
    }
   
   def tri(n: Integer) = {
      // place iterative solution here
     var result = 0;
     for(i <- 1 to n){
       result += i;
     }
     result;
    }
   
   def exp(n: Integer) = {
       // place iterative solution here
      var result = 1;
      for(i <- 1 to n){
        result *= 2;
      }
      result;
    }
   
   def isPrime(n: Integer) = {
     var result = true;
      // place iterative solution here
     if(n == 2){result = true;}
     else{
       for(i <- 2 until n){
       if(n%i == 0){result =  false;}
     }
     }
     
     result;
   }
   
   def main(args: Array[String]): Unit = {
     println("enter 3 integers x, y, and z on separate lines: ")
     var x = readInt()
     var y = readInt()
     var z = readInt()
     println("fact(x) = " + fact(x))
     println("fact(y) = " + fact(y))
     println("fact(z) = " + fact(z))
     println("tri(x) = " + tri(x))
     println("tri(y) = " + tri(y))
     println("tri(z) = " + tri(z))
     println("exp(x) = " + exp(x))
     println("exp(y) = " + exp(y))
     println("exp(z) = " + exp(z))
     println("isPrime(x) = " + isPrime(x))
     println("isPrime(y) = " + isPrime(y))
     println("isPrime(z) = " + isPrime(z))
   }

}
