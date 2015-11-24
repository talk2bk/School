package values

import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import expressions._

class Environment (val nextEnv: Environment = null) extends HashMap[Identifier, Value] with Value{
 

 def getNext() = nextEnv
 
 def add(id: Identifier, value: Value) { put(id, value) }
 
 def add(ids: List[Identifier], vals: List[Value]) {
   for (p<-ids.zip(vals)) this += p
 }
 
 def put(ids: List[Identifier], vals: List[Value]) {
    //for(p<-names.zip(vals)) this += p
   if (ids.length != vals.length) throw new TypeException("Too few identifiers or values")
   for (i <- 0 until ids.length) put(ids(i), vals(i))
  }
   
  
  def find(id: Identifier): Value = {
    /*
    def findHelp(e: Environment): Value = {
    if (e == null)
       Notification.UNKNOWN
    else {
      if (e.contains(v))
    	e.get(v).get
      else
        findHelp(e.getNext)
    }
   }
    findHelp(this)
    * */
    if (this.contains(id)) this(id)
    else if (nextEnv != null) nextEnv.find(id)
    else throw new UndefinedException("undefined symbol: " + id)
  }
  
}
