package expressions

import value._
import expressions._

case class Identifier(name: String) extends Expression {
		override def toString = name
		def execute(env: Environment): Value = env.find(this)
}