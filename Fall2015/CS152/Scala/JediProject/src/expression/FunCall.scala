package expression
import values._
import ui._


case class FunCall(val operator: Expression, val operands: List[Expression] = Nil) extends Expression {
  def execute(env: Environment):Value = {
    val args: List[Value] = operands.map(_.execute(env))

    try{    
    if(operator.execute(env).isInstanceOf[Closure]) operator.execute(env).asInstanceOf[Closure].apply(args)
    else throw new UndefinedException("funcall") 
    } catch{
      case e: UndefinedException => {
        system.execute(operator.asInstanceOf[Identifier], args)}
    }
  }
}