package expressions
import ui._
import values._

case class Block(locals: List[Expression]) extends SpecialForm {
   def execute(env: Environment) = {
      var temp = new Environment(env)
      val list = locals.map(_.execute(temp))
      list(list.length - 1)
     }
}