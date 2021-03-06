package acornAndFuncall

abstract class Expression {
   def execute: Double
}

class Number(private val value: Double) extends Expression{
  def execute = value
  override def toString = ""+value
}
object Number{
  def apply(value: Double) = new Number(value)
}

class Product(private val operand1: Expression, private val operand2: Expression) extends Expression{
 def execute = operand1.execute * operand2.execute
 override def toString = "(* " +operand1 + " " + operand2 + ")"
}
object Product {
  def apply(operand1: Expression, operand2: Expression) = 
    new Product(operand1, operand2)
}

class Sum(private val operand1: Expression, private val operand2: Expression) extends Expression {
  def execute = operand1.execute + operand2.execute
  override def toString = "(+ " + operand1 + " " + operand2 + ")"
}

object Sum {
  def apply(operand1: Expression, operand2: Expression) =
      new Sum(operand1, operand2)
}

class FunCall(private val function: Double => Double, private val operand: Expression) extends Expression{
  def execute = function(operand.execute)
  override def toString = "(" + function.toString() + " " + operand.toString() + ")"
}
object FunCall {
  def apply(function: Double => Double, operand: Expression) = 
    new FunCall(function, operand)
}

object TestAcorn extends App {
   var exp: Expression = Sum(Number(42), Product(Number(3.14), Number(2.71)))
   println("the value of " + exp + " = " + exp.execute)
   exp = Product(Number(2), Product(Number(3), Number(5)))
   println("the value of " + exp + " = " + exp.execute)
   exp = FunCall(x=>x*x, Sum(Number(3), Number(2)))
   println("the value of " + exp + " = " + exp.execute)
}