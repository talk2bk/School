package expressions
import values._
import ui._

case class Conjunction (operands: List[Expression]) extends SpecialForm {
 def execute(env: Environment): Value = {
    var result = true
    var i = 0

     while (result && i < operands.length) {
      //if (operands.length <= i) throw new JediException("Index out of Range")
      val v = operands(i).execute(env)
      if (!v.isInstanceOf[Boole]) throw new TypeException()
      val b = v.asInstanceOf[Boole]
      i = i + 1
      result = b.value
    }
    new Boole(result)
  }
}