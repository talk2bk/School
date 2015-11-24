package value

import scala.collection.mutable.HashMap
import value._
import ui._
import expressions._

class Environment(nextEnv: Environment = null) extends HashMap[Identifier, Value] with Value {
		def put(names: List[Identifier], vals: List[Value]) {
				if(names.length != vals.length) {
						throw new TypeException()
				}
				for(i <- 0 to names.length - 1) {
						super.put(names(i), vals(i))
				}
		}
		
		def find(me: Identifier): Value = {
				if(this.contains(me)) {
						this(me)
				} else {
						if(nextEnv == null) {
								throw new UndefinedException(me.name)
						} else {
								nextEnv.find(me)
						}
				}
		}
}

object Environment {
	def test() {
	  	val n1 = new Environment()
	  	val n2 = new Environment(n1)
	  	val n3 = new Environment(n2)
	  	n1.put(List(new Identifier("Bacon"), new Identifier("Cat"), new Identifier("Science")), List(new Number(3), new Boole(false), new Number(-1)))
	  	println("Finding Bacon: " + n1.find(new Identifier("Bacon")))
	  	println("Finding Cat: " + n1.find(new Identifier("Cat")))
	  	println("Finding Apple: " + n1.find(new Identifier("Apple")))
	}
}