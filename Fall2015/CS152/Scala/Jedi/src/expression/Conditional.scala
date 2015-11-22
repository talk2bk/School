package expression
import values._
/*
 * represents expressions of the forms:
 *      if(a) b else c
 *      if(a) b 
 */
class Conditional(condition: Expression, consequence: Expression, alternative: Expression = null)  extends SpecialForm {
  //conditional execution
  def execute(env: Environment): Value  = {
    val a = condition.execute(env)
    //type checking
    if(!a.isInstanceOf[Boole]) throw new Exception("Condition must be a Boole")
    val aVal = a.asInstanceOf[Boole]
    if(aVal.value) consequence.execute(env)
    else if (alternative != null) alternative.execute(env)
    else Notification("unspecified")
    
  }
}