package ui
import values._
/**
 * @author CuTs
 */
object system {
    // the dispatcher
  def execute(operator: String, args: List[Value]):Value = {
    operator match {
      case "sum" => sum(args)
      case "sub" => sub(args)
      case "mul" => mul(args)
      case "div" => div(args)
      case "equals" => equals(args)
      case "less" => less(args)
      case "not" => not(args)
      case "add" => sum(args)
       //
      case _ => throw new UndefinedException(operator)
    }
  }
  
  def sum(args: List[Value]) = {new Number(args.map(_.asInstanceOf[Number]).map(_.value).reduce (_ + _))}
  def sub(args: List[Value]) = {new Number(args.map(_.asInstanceOf[Number]).map(_.value).reduce(_ - _))}
  def mul(args: List[Value]) = {new Number(args.map(_.asInstanceOf[Number]).map(_.value).reduce(_ * _))}
  def div(args: List[Value]) = {new Number(args.map(_.asInstanceOf[Number]).map(_.value).reduce(_ / _))}
  
  def equals(args: List[Value]) = {
    var result = new Boole(true)
    if(args.head.isInstanceOf[Boole]) result = new Boole(args.map(_.asInstanceOf[Boole]).map(_.value).reduce(_ == _))
    else {
     val numargs = args.map(_.asInstanceOf[Number]).map(_.value)
     for (thing <- numargs) if(thing != numargs.head) result = new Boole(false)
    }
    result
    }
  
  def less(args: List[Value]) = {
    var result = new Boole(false)
    val numargs = args.map(_.asInstanceOf[Number]).map(_.value)
    for(thing <- numargs.tail) if(numargs.head < thing) result = new Boole(true)
    result
  }
  def not(args: List[Value]) = {new Boole(!args.head.asInstanceOf[Boole].value)}
  
}