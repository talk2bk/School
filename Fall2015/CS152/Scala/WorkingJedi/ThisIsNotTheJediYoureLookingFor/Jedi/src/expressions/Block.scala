package expressions

import value._
import ui._

case class Block(locals: List[Expression]) extends SpecialForm {
	def execute(env: Environment) : Value = {
			val tempEnv = new Environment(env)
			val ret = locals.map(_.execute(tempEnv))
			ret(ret.length-1)
	}
}