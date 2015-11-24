
package expressions

import value._
import expressions._
import ui._

case class FunCall(val operator: Expression, val operands: List[Expression] = Nil) extends Expression {
  def execute(env: Environment): Value = {
    val useStatic = false
    val args: List[Value] = operands.map(_.execute(env))
    try {
      val f = operator.execute(env)
      if(!f.isInstanceOf[Closure]) {
         throw new TypeException()
      }
      val foo = f.asInstanceOf[Closure]
      if(useStatic == true) {
    	  foo.apply(args)
      } else {
    	  foo.apply(args, env)
      }
    } catch {
      case e: UndefinedException => System.execute(operator.asInstanceOf[Identifier], args)
    }
  }    
}