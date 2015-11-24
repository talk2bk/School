package expressions

import value._
import ui._

//if(a) b else c
case class Conditional(condition: Expression, consequent: Expression, alternate: Expression = null) extends SpecialForm {
		def execute(env: Environment): Value = {
					val one = condition.execute(env)
					val two = one.asInstanceOf[Boole]
					if(two.bool == true) {
					  consequent.execute(env)
					} else {
						try {
						  alternate.execute(env)
						} catch {
						  case e: java.lang.NullPointerException => {
							  null
						  }
						}
					}
		}
}