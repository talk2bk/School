package values
import expression._
import ui._
/**
 * @author CuTs
 */
class Closure(params: List[Identifier], body: Expression, defEnv: Environment) extends Value{
def apply(args: List[Value]): Value = {
  if(params.length != args.length) throw new TypeException("differing lengths")
  var tempEnv = new Environment(defEnv)
  tempEnv.put(params, args)
  body.execute(tempEnv)
}
}