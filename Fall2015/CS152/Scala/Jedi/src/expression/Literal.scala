package expression
import values._
/**
 * @author CuTs
 */
trait Literal extends Expression with Value {
def execute(env: Environment) = this
}