package expressions

import ui._
import values._

case class Iteration(cond: Expression, body: Expression) extends SpecialForm {

  def execute(env: Environment): Value = {
     var res: Value = Notification.UNKNOWN
     
     while (cond.execute(env).asInstanceOf[Boole].value) {
       res = body.execute(env)
     }
     res
     
  }
}