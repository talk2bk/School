package expressions

import value._
import ui._

case class Assignment(id: Identifier, exp: Expression) extends SpecialForm {
	def execute(env: Environment): Value = {
	  val temp = env.find(id)
	  if(!temp.isInstanceOf[Variable]) throw new TypeException("assignments must be done to Variables")
	  val temp2 = temp.asInstanceOf[Variable]
	  temp2.change(exp.execute(env))
	  Notification.DONE
	}
}