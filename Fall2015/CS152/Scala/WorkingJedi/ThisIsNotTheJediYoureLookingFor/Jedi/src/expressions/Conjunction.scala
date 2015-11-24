package expressions

import value._
import ui._

case class Conjunction (operands: List[Expression]) extends SpecialForm {
	def execute (env: Environment): Value = {
		var i = 0
		var result = true
		while(result && i < operands.length) {
			if (operands.length <= i) { throw new JediException("Index out of range")}
			val v = operands(i).execute(env)
			if(v.isInstanceOf[Boole] == false) { throw new TypeException("Inputs to && must be Booles") }
			val b = v.asInstanceOf[Boole]
			i = i + 1
			result = b.bool
		}
		new Boole(result)
	}
}