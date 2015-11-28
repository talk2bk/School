package expression
import values._
import ui._


case class FunCall(val term: Identifier, val operands: List[Expression]) extends Expression {
  def execute(env: Environment) = {
    val args: List[Value] = operands.map(_.execute(env))
    if(env.contains(term)){
      //apply term to args
     Notification.UNIMPLEMENTED
      
    } else {
      system.execute(term.value, args)
    }
  }
}