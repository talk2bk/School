package expressions

import value._
import ui._

case class Lambda(params: List[Identifier], body: Expression) extends SpecialForm {
		def execute(env: Environment) : Value = {
				new value.Closure(params, body, env)
		}
}