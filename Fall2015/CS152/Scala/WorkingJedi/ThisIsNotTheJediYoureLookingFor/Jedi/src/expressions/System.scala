package expressions

import value._
import ui._

object System {
  
  def execute(opcode: Identifier, args: List[Value]): Value = {
    opcode.name match {
      case "add" => add(args)
      case "sub" => sub(args)
      case "mul" => mul(args)
      case "div" => div(args)
      case "equals" => equals(args)
      case "less" => less(args)
      case "not" => not(args)
      case "var" => vari(args)
      case "val" => valu(args)
      case "exp" => exp(args)
      case _ => throw new UndefinedException(opcode.name)
    }
  }
  
  private def exp(vals: List[Value]): Value = {
    if (vals.length != 2) throw new TypeException("exponent expect 2 inputs")
    var args1:Number = new Number(0)
    if(vals(0).isInstanceOf[Number]) {
    	args1 = vals(0).asInstanceOf[Number]
    }
    var args2:Number = new Number(0)
    if(vals(1).isInstanceOf[Number]) {
    	args2 = vals(1).asInstanceOf[Number]
    }
    var result = new Number(args1.value)
    if(args1.value > 1 && args2.value > 0) {
    	for(i <- 1 to args2.value.asInstanceOf[Int] - 1) {
    		result = new Number(result.value * args1.value)
    	}
    	result
    } else {
      new Number(1)
    }
  }
  
  private def valu(vals: List[Value]): Value = {
    if (vals.length != 1) throw new TypeException("values expect 1 input")
    val args1 = vals(0)
    if(args1.isInstanceOf[Variable]) {
      val args2 = args1.asInstanceOf[Variable]
      args2.content 
    } else {
      vals(0)
    }
  }
  
  private def vari(vals: List[Value]): Variable = {
    if (vals.length != 1) throw new TypeException("values expect 1 input")
    new Variable(vals(0))
  }
  
  private def add(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("addition expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all addition inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_+_)
  }
  
  private def sub(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("subtraction expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all subtraction inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_-_)
  }
  
  private def mul(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("multiplication expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all multiplication inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_*_)
  }
  
  private def div(vals: List[Value]): Value = {
    if (vals.isEmpty) throw new TypeException("division expects > 0 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all division inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    args2.reduce(_/_)
  }
  
  private def equals(vals: List[Value]): Value = {
    if (vals.length > 1 == false) throw new TypeException("less-than expects > 1 inputs")
    var ret = new Boole(true)
    var ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) {
    	ok = vals.filter(_.isInstanceOf[Boole])
    	if(ok.length < vals.length) {
    		var err = vals.filter(!_.isInstanceOf[Number])
    		err = err.filter(!_.isInstanceOf[Boole])
    		throw new UndefinedException(err.head.asInstanceOf[String])
    	} else {
    		val booles = ok.map(_.asInstanceOf[Boole])
    		for(i <- 0 to booles.length - 1) {
    				if(booles(i).bool != booles(0).bool) {
    					ret = new Boole(false)
    				}
    		}
    	}
    } else {
    	val nums = ok.map(_.asInstanceOf[Number])
    		for(i <- 0 to nums.length - 1) {
    				if(nums(i).value != nums(0).value) {
    					ret = new Boole(false)
    				}
    		}
    }
    ret
  }  
  
  private def less(vals: List[Value]): Value = {
    if (vals.length > 1 == false) throw new TypeException("less-than expects > 1 inputs")
    val ok = vals.filter(_.isInstanceOf[Number])
    if (ok.length < vals.length) throw new TypeException("all less-than inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Number])
    var ret = new Boole(true)
    for(i <- 1 to args2.length-1) {
    	if(args2(i).value <= args2(i-1).value) {
    		ret = new Boole(false)
    	}
    }
    ret
  }
  
  private def not(vals: List[Value]): Value = {
    if(vals.length != 1) {
    	throw new TypeException("not expects 1 input")
    }
    val ok = vals.filter(_.isInstanceOf[Boole])
    if (ok.length < vals.length) throw new TypeException("all less-than inputs must be numbers")
    val args2 = vals.map(_.asInstanceOf[Boole])
    if(args2(0).bool == true) {
      new Boole(false)
    } else {
      new Boole(true)
    }
  }
  
  	def test() {
  	  	val listnum = List(new Number(0), new Number(4), new Number(3), new Number(2), new Number(1), new Number(0))
  	  	val listboole = List(new Boole(true), new Boole(true), new Boole(false), new Boole(true))
  	  	println("add <> listnum: " + execute(new Identifier("add"), listnum))
	  	println("equals <> listnum: " + execute(new Identifier("equals"), listnum))
	  	println("equals <> listboole: " + execute(new Identifier("equals"), listboole))
	  	println("less <> listnum: " + execute(new Identifier("less"), listnum))
	  	println("div <> listmix: " + execute(new Identifier("div"), listnum))
	}
}