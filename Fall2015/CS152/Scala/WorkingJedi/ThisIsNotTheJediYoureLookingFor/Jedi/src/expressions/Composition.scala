package expressions

import value._
import ui._

case class Composition(params: List[expressions.Lambda]) extends SpecialForm {
		def execute(env: Environment) : Value = {
				new Number(1)
		}
}