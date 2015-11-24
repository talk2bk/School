package values

import expressions._
import ui._

class TypeException(v: String) extends Exception { }
class UndefinedException(v: String) extends Exception { }

object System {
  
  def execute(opcode: Identifier, args: List[Value]): Value = {
    opcode.name match {
      case "add" => add(args)
      case "sub" => sub(args)
      case "mul" => mul(args)
      case "div" => div(args)
      case "equals" => equals(args)
      case "less" => less(args)
      case "greater" => greater(args)
      case "var" => makeVar(args)
      case "val" => content(args)
      case _ => throw new UndefinedException(opcode.name)
    }
  }

  private def add(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("addition expects > 0 inputs")
    val ok = vals.filter(x => x.isInstanceOf[Number] || x.isInstanceOf[Variable])
    if (ok.length < vals.length) throw new TypeException("all addition inputs must be numbers")
    val args2 = vals.map(x => 
      if (x.isInstanceOf[Number])
        x.asInstanceOf[Number] 
      else 
        x.asInstanceOf[Variable].content.asInstanceOf[Number])
    args2.reduce(_+_)
  }

  private def sub(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("addition expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all addition inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_-_)
  }
  
  private def mul(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("addition expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all addition inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_*_)
  }
  
  private def div(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("addition expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all addition inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_/_)
  }
  
  def differ(x: Value, y: Value) = {
    x != y
    }
 
  private def equals(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("addition expects > 0 inputs")
    var result = true
    var i = 1  
    //for (i <- 1 until vals.length) if (vals(i) != vals(0)) result = false
    while(result && i < vals.length) {
      if (differ(vals(0), vals(i))) 
        result = false;
      i = i + 1; 
     // println("result = " + result)
      }
    new Boole(result)
  }
  
  
  private def less(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("addition expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all addition inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    var check = new Boole(true)
    var i = 0
    while (i < args2.length -1 && check.value) {
      if ( (args2(i) < args2(i+1)).value )
        check = new Boole(true)
      else
        check = new Boole(false)
      i = i + 1
    }
    check
  }  
  
    //fix
    private def greater(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("addition expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all addition inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    var check = new Boole(true)
    for (i <- 0 until args2.length - 1) {
      if ( (args2(i) > args2(i+1)).value )
        check = new Boole(true)
      else
        check = new Boole(false)
    }
    check
  }  
    
   private def content(args: List[Value]) = {
     if (args.head.isInstanceOf[Variable])
       args.head.asInstanceOf[Variable].content
     else
       throw new TypeException("Type Variable Required")
    	   
   }
   
   private def makeVar(x: List[Value]) = new Variable(x.head)
  // etc.
}