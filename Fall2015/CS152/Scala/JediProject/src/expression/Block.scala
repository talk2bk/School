package expression

import ui._
import values._

case class Block(locals: List[Expression]) extends SpecialForm {
   def execute(env: Environment) = {
     var tempEnv = new Environment(env)
     val executed = locals.map(_.execute(tempEnv))
     executed(executed.length-1)
   }
}