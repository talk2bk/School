package value

import ui._
import expressions._

case class Iteration(condition: Expression, exp: Expression) extends SpecialForm {
	def execute(env: Environment): Value = {
		 var result : Value = Notification.UNKNOWN
		 var dowhile = false
		 val temp = condition.execute(env)
		 if(temp.isInstanceOf[Boole]) {
		   val temp2 = temp.asInstanceOf[Boole]
		   dowhile = temp2.bool
		 } else {
		   throw new TypeException("iteration condition must be a Boole")
		 }
		 while(dowhile) {
		   result = exp.execute(env)
		   
		   val condtemp = condition.execute(env)
		   if(condtemp.isInstanceOf[Boole]) {
			 val temp3 = condtemp.asInstanceOf[Boole]
		     dowhile = temp3.bool
		   } else {
			 false
		   }
		 }
		 result
	}
}