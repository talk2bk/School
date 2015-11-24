package expressions

import value._
import ui._

case class Declaration(name: Identifier, body: Expression) extends SpecialForm {
		def execute(env: Environment) : Value = {
				env.put(name, body.execute(env))
				Notification.OK
		}
}