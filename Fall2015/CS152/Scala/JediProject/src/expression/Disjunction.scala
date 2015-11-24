package expression
import values._
import ui._

case class Disjunction(val operands: List[Expression]) extends SpecialForm{
  def execute(env: Environment) = {
    var result = false
    for(thing <- operands){
      var temp = thing.execute(env)
      if(!temp.isInstanceOf[Boole]) throw new TypeException(temp.toString())
      result = temp.asInstanceOf[Boole].value
    }
    new Boole(result)
  }
}