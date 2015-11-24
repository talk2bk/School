package ui

import scala.util.parsing.combinator._
import expressions._
import value._
import ui._

class EwokParsers extends RegexParsers {
		def expression: Parser[Expression] = declaration | conditional | disjunction | conjunction | failure("Invalid expression")
		
		def declaration: Parser[Declaration] = "def" ~ identifier ~ "=" ~ expression ^^ {
		  case "def" ~ id ~ "=" ~ exp => Declaration(id, exp)
		}
		
		def identifier: Parser[Identifier] = """[a-zA-Z][0-9a-zA-Z]*""".r ^^ {
		  	case exp => Identifier(exp)
		}
		
		def conditional: Parser[Conditional] = "if" ~ "(" ~ expression ~ ")" ~ expression ~ opt("else" ~ expression) ^^ {
		  case "if" ~ "(" ~ exp1 ~ ")" ~ exp2 ~ None => Conditional(exp1, exp2)
		  case "if" ~ "(" ~ exp1 ~ ")" ~ exp2 ~ Some("else" ~ exp3) => Conditional(exp1, exp2, exp3)
		}
		
		def disjunction: Parser[Expression] = conjunction ~ rep("||" ~> conjunction) ^^ {
		  case exp ~ Nil => exp
		  case exp ~ expList => Disjunction(exp::expList)
		}
		
		def conjunction: Parser[Expression] = equality ~ rep("&&" ~> equality) ^^ {
		  case exp ~ Nil => exp
		  case exp ~ expList => Conjunction(exp::expList)
		}
		
		//USE THIS ONE!!!
		def equality: Parser[Expression] = inequality ~ rep("==" ~> inequality) ^^ {
          case exp ~ Nil => exp
          case exp ~ expList => FunCall(Identifier("equals"), exp::expList)
		}
		//USE THIS ONE!!!
		
		def inequality: Parser[Expression] = sum ~ rep("<" ~> sum) ^^ {
		  case exp ~ Nil => exp
		  case exp ~ (expList) => FunCall(Identifier("less"), exp::expList)
		}
		
		def negate(exp: Expression): Expression = {
				val sub = Identifier("sub")
				val zero = new Number(0)
				FunCall(sub, List(zero, exp))
		}
		
		def sum: Parser[Expression] = product ~ rep(("+"|"-") ~ product ^^ {case "+" ~ s => s case "-" ~ s => negate(s)}) ^^ {
		  case exp ~ Nil => exp
		  case exp ~ expList => FunCall(Identifier("add"), exp::expList)
		}
		
		def invert(exp: Expression): Expression = {
				val div = Identifier("div")
				val inversion = new Number(1)
				FunCall(div, List(inversion, exp))
		}
		
		def product: Parser[Expression] = funcall ~ rep(("*"|"/") ~ funcall ^^ {
		  	case "*" ~ s => s
			case "/" ~ s => invert(s)}) ^^ {
		  case exp ~ Nil => exp
		  case exp ~ expList => FunCall(Identifier("mul"), exp::expList)
		}
		
		def funcall: Parser[Expression] = term ~ opt(operands) ^^ {
		  case foo ~ None => foo
		  case foo ~ Some(opList) => {
			  FunCall(foo.asInstanceOf[Identifier], opList)
		  }
		}
		
		def operands: Parser[List[Expression]] = "(" ~> (opt(expression ~ rep("," ~> expression))) <~ ")" ^^ {
		  case None => List()
		  case Some(exp ~ Nil) => List(exp)
		  case Some(exp ~ expList) => exp::expList
		}
		
		def term: Parser[Expression] = literal | identifier | "(" ~> expression <~ ")" ^^ {
		  case ex => {
			  if(ex.isInstanceOf[Literal]) {
				  ex.asInstanceOf[Literal]
			  } else if(ex.isInstanceOf[Identifier]) {
				  ex.asInstanceOf[Identifier]
			  } else if(ex.isInstanceOf[Expression]) {
				  ex.asInstanceOf[Expression]
			  } else {
				  throw new SyntaxException()
			  }
		  }
		}
		
		def literal: Parser[Expression] = (boole | number) ^^ {
		  case check => {
			  if(check.isInstanceOf[Boole]) {
				  check.asInstanceOf[Boole]
			  } else if(check.isInstanceOf[Number]) {
				  check.asInstanceOf[Number]
			  } else {
				  throw new SyntaxException()
			  }
		  }
		}
		
		def boole: Parser[Boole] = ("true" | "false") ^^ {
		  case x => new Boole(x.toBoolean)
		}
		
		def number: Parser[Number] = """(\+|-)?[0-9]+(\.("[0-9]")+)?""".r ^^ {
		  case x => new Number(x.toDouble)
		}
		
		// def declaration, conditional, disjunction, and other parsers
}