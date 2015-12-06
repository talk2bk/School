package expression
import ui._
import values._
/**
 * @author CuTs
 */
case class Lambda(ids: List[Identifier], body: Expression) extends SpecialForm{
  def execute(env: Environment):Value = new Closure(ids, body, env)
}