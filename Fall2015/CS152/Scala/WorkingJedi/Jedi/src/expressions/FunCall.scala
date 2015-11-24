package expressions

import values._
import ui._


class TypeException extends Exception { }

case class FunCall(val operator: Expression, val operands: List[Expression]) extends Expression {

  def execute(env: Environment): Value = {
   val args = operands.map(_.execute(env))
   try {
   if (operator.execute(env).isInstanceOf[Closure]) {
     val temp = operator.execute(env).asInstanceOf[Closure]
     temp.apply(args)
   }
   else throw new TypeException
  }
   catch {
     case e: UndefinedException => if (operator.isInstanceOf[Identifier]) System.execute(operator.asInstanceOf[Identifier], args) else
       throw new TypeException
     }
   }

}