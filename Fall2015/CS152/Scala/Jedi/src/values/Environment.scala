package values

import scala.collection.immutable.HashMap
import expression._

/**
 * @author CuTs
 */
class Environment extends HashMap[Identifier, Value] with Value{
  
}