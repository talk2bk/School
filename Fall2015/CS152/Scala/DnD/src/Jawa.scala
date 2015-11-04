class Jawa {
  trait Value {}
  trait Expression { def execute: Value}
  trait Literal extends Value with Expression { def execute = this}
  
  class Number(val value: Double) extends Literal { }
  class Boole(val value: Boolean) extends Literal { }
  
  class Sum(operand1: Expression, operand2: Expression) extends Expression {
    def execute = {
      val arg1 = operand1.execute
      val arg2 = operand2.execute
      if(!arg1.isInstanceOf[Number] || !arg2.isInstanceOf[Number]){
        throw new Exception("sum inputs must be numbers")
      }
      
      val num1 = arg1.asInstanceOf[Number]
      val num2 = arg2.asInstanceOf[Number]
      new Number(num1.value+num2.value)
    }
    
  }
  class Product(operand1: Expression, operand2: Expression) extends Expression {
    def execute = {
      val arg1 = operand1.execute
      val arg2 = operand2.execute
      if(!arg1.isInstanceOf[Number] || !arg2.isInstanceOf[Number]){
        throw new Exception("product inputs must be numbers")
      }
      
      val num1 = arg1.asInstanceOf[Number]
      val num2 = arg2.asInstanceOf[Number]
      new Number(num1.value*num2.value)
    }
    
  }
  
  class And(operand1: Expression, operand2: Expression) extends Expression {
    def execute = {
      val arg1 = operand1.execute
      val arg2 = operand2.execute
      if(!arg1.isInstanceOf[Boole] || !arg2.isInstanceOf[Boole]){
        throw new Exception("and inputs must be booleans")
      }
      
      val bool1 = arg1.asInstanceOf[Boole]
      val bool2 = arg2.asInstanceOf[Boole]
      new Boole(bool1.value & bool2.value)
    }
    
  }
    class Or(operand1: Expression, operand2: Expression) extends Expression {
    def execute = {
      val arg1 = operand1.execute
      val arg2 = operand2.execute
      if(!arg1.isInstanceOf[Boole] || !arg2.isInstanceOf[Boole]){
        throw new Exception("or inputs must be booleans")
      }
      
      val bool1 = arg1.asInstanceOf[Boole]
      val bool2 = arg2.asInstanceOf[Boole]
      new Boole(bool1.value | bool2.value)
    }
    
  }
      class Not(operand: Expression) extends Expression {
    def execute = {
      val arg1 = operand.execute
      if(!arg1.isInstanceOf[Boole]){
        throw new Exception("not input must be boolean")
      }
      
      val bool = arg1.asInstanceOf[Boole]
      new Boole(!bool.value)
    }
    
  }
  
}