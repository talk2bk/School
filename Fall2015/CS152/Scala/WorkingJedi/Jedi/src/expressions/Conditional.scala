package expressions
import values._
import ui._


case class Conditional (exp: Expression, exp2: Expression, alternate: Expression = null) extends SpecialForm {
 def execute(env: Environment): Value = {
    if (exp.execute(env).asInstanceOf[Boole].value)
      exp2.execute(env)
    else if (alternate == null)
      Notification.UNKNOWN
    else
      alternate.execute(env)
  }
}