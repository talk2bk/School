package expressions
import ui._
import values._

case class Assignment(x: Identifier, update: Expression) extends SpecialForm{
	def execute(env: Environment): Value = {
	val tmp =  update.execute(env).asInstanceOf[Value]
	x.execute(env).asInstanceOf[Variable].content = tmp
	Notification.OK
  }
}