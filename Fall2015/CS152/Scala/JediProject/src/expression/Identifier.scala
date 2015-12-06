package expression
import values._
import ui._

/**
 * @author CuTs
 */
case class Identifier (value: String) extends Expression with Serializable{
  def execute(env: Environment) = env.find(this)
}