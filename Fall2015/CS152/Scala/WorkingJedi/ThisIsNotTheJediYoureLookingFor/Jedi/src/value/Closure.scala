package value

import ui._
import expressions._

class Closure(paramsin: List[Identifier], bodyin: Expression, defEnvin: Environment) extends Value {
	val params = paramsin
	val body = bodyin
	val defEnv = defEnvin
	
	def apply(args: List[Value], callEnv: Environment = null) : Value = {
			if(callEnv != null) {
				callEnv.put(params, args)
				body.execute(callEnv)
			} else {
				var tempEnv = new Environment(defEnv);
				tempEnv.put(params, args)
				body.execute(tempEnv)
			}
	}
}