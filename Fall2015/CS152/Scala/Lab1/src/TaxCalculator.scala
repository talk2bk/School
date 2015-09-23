object TaxCalculator {
  
  def tax(income: Double): Double = {
      if(income < 0){ throw new Exception("Income can't be negative.");}
     // enter code here 
      var taxAmount = 0.0;
      var result = 0.0;
      
      if(income < 20000){taxAmount = 0;}
      else if(income < 30000){taxAmount = 0.05;}
      else if(income < 40000){taxAmount = 0.11;}
      else if(income < 60000){taxAmount = 0.23;}
      else if(income < 100000){taxAmount = 0.32;}
      else{taxAmount = 0.50;}
      result = income*taxAmount;
      result;
    
   } 
  
    def main(args: Array[String]): Unit = {
      try {
        println("enter 3 incomes x, y, and z on separate lines:")
        var x = readDouble()
        var y = readDouble
        var z = readDouble
        println("tax(x) = $" + tax(x))
        println("tax(y) = $" + tax(y))
        println("tax(z) = $" + tax(z))
      } catch {
         case e: Exception => {println(e) }
       }
    }

}