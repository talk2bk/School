package ui
import expressions._
import value._

/**
 * @author CuTs
 */
object NoOp extends Expression{
  def execute(env: Environment) = Notification.UNSPECIFIED
}

object codeTools {
  def conditionExtractor(exp: Expression): Expression = {
    exp match {
      case exp: Conditional => exp.condition
      case exp: Iteration => exp.condition
      case _ => NoOp
    }
  }
  
}