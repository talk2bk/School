package values
import scala.collection.mutable.HashMap
import expression._
import ui._

class Environment(val nextEnv: Environment = null) extends HashMap[Identifier, Value] with Value{
def put(ids: List[Identifier], vals: List[Value]) { 
for(i <- 0 until ids.length){ this.put(ids(i),vals(i)) }
  }
def find(id: Identifier):Value = {
  if(this.contains(id)) this(id)
  else if(nextEnv != null) nextEnv.find(id)
  else Notification.UNKNOWN
}
}