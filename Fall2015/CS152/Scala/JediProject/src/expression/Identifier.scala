package expression
import values._
import ui._

/**
 * @author CuTs
 */
case class Identifier (value: String) extends Expression with Serializable{
  def execute(env: Environment) = 
    if (env.contains(this)) env(this) else throw new UndefinedException(value)
}