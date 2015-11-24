package values

import scala.collection.mutable.HashMap
import expression._

/**
 * @author CuTs
 */
class Environment(val nextEnv: Environment = null) extends HashMap[Identifier, Value] with Value{
  
}