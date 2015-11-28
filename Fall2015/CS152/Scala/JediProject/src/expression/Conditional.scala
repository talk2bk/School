package expression
import values._
import ui._
/*
 * represents expressions of the forms:
 *      if(a) b else c
 *      if(a) b 
 */
case class Conditional(condition: Expression, consequence: Expression, alternative: Expression = null)  extends SpecialForm {
  //conditional execution
  def execute(env: Environment) = {
    condition.execute(env) match {
      case Boole(value) =>
        if(value) consequence.execute(env)
        else if (alternative != null) alternative.execute(env) 
        else Notification.UNSPECIFIED
      case _ => throw new TypeException("if condition must be Boole")
    }
  }

}

