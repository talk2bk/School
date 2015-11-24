package expression
import values._

trait Expression {
def execute(env: Environment): Value
}