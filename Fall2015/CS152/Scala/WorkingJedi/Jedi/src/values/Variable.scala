package values

import expressions._

class Variable(var content: Value) extends Literal with Value {

  def getValue = content
  
  override def toString = "Variable(" + content + ")"
}