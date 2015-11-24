package expression
import values._

/**
 * @author CuTs
 */
case class Identifier (value: String) extends Expression{
  def execute(env: Environment) = 
    if (env.contains(this)) env.apply(this) else throw new Exception("identifier undefined")
}