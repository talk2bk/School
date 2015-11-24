package expressions

import value._

case class Literal() extends Expression with Value {
		def execute(env: Environment): Value = this
}