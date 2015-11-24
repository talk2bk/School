package expression
import values._
import ui._


case class FunCall(val term: Expression, val operands: List[Expression]) extends Expression {
  def execute(env: Environment) = {
    
  }
}