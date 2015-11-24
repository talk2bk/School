package ui

import scala.util.parsing.combinator._
import expressions._
import value._
import ui._

class SithParsers extends RegexParsers {
		
		def expression: Parser[Expression] = declaration | conditional | disjunction | conjunction | failure("Invalid expression")
		
		/*def composition: Parser[Expression] = "compose" ~ "(" ~ lambda ~ "," ~ lambda ~ ")" ^^ {
		  case "compose" ~ "(" ~ lam1 ~ "," ~ lam2 ~ ")" => new Composition(List(lam1, lam2))
		}*/
		
		def declaration: Parser[Declaration] = "def" ~ identifier ~ "=" ~ expression ^^ {
		  case "def" ~ id ~ "=" ~ exp => new Declaration(id, exp)
		}
		
		def identifier: Parser[Identifier] = """[a-zA-Z][0-9a-zA-Z]*""".r ^^ {
		  	case exp => Identifier(exp)
		}
		
		def conditional: Parser[Conditional] = "if" ~ "(" ~ expression ~ ")" ~ expression ~ opt("else" ~ expression) ^^ {
		  case "if" ~ "(" ~ exp1 ~ ")" ~ exp2 ~ None => Conditional(exp1, exp2)
		  case "if" ~ "(" ~ exp1 ~ ")" ~ exp2 ~ Some("else" ~ exp3) => new Conditional(exp1, exp2, exp3)
		}
		
		def disjunction: Parser[Expression] = conjunction ~ rep("||" ~> conjunction) ^^ {
		  case exp ~ Nil => exp
		  case exp ~ expList => new Disjunction(exp::expList)
		}
		
		def conjunction: Parser[Expression] = equality ~ rep("&&" ~> equality) ^^ {
		  case exp ~ Nil => exp
		  case exp ~ expList => new Conjunction(exp::expList)
		}
		
		//USE THIS ONE!!!
		def equality: Parser[Expression] = inequality ~ rep("==" ~> inequality) ^^ {
          case exp ~ Nil => exp
          case exp ~ expList => expressions.FunCall(Identifier("equals"), exp::expList)
		}
		//USE THIS ONE!!!
	
		def inequality: Parser[Expression] = sum ~ rep("<" ~> sum) ^^ {
		  case exp ~ Nil => exp
		  case exp ~ (expList) => expressions.FunCall(Identifier("less"), exp::expList)
		}
		
		def negate(exp: Expression): Expression = {
				val sub = Identifier("sub")
				val zero = new Number(0)
				expressions.FunCall(sub, List(zero, exp))
		}
		
		def sum: Parser[Expression] = product ~ rep(("+"|"-") ~ product ^^ {
		  		case "+" ~ s => s
		  		case "-" ~ s => negate(s) }) ^^ {
		  case exp ~ Nil => exp
		  case exp ~ expList => expressions.FunCall(Identifier("add"), exp::expList)
		}
		
		def invert(exp: Expression): Expression = {
				val div = Identifier("div")
				val inversion = new Number(1)
				expressions.FunCall(div, List(inversion, exp))
		}
		
		def product: Parser[Expression] = funcall ~ rep(("*"|"/") ~ funcall ^^ {
		  	case "*" ~ s => s
			case "/" ~ s => invert(s)}) ^^ {
		  case exp ~ Nil => exp
		  case exp ~ expList => expressions.FunCall(Identifier("mul"), exp::expList)
		}
		
		def funcall: Parser[Expression] = term ~ opt(operands) ^^ {
		  case foo ~ None => foo
		  case foo ~ Some(opList) => {
			  expressions.FunCall(foo, opList)
		  }
		}
		
		def operands: Parser[List[Expression]] = "(" ~> (opt(expression ~ rep("," ~> expression))) <~ ")" ^^ {
		  case None => List()
		  case Some(exp ~ Nil) => List(exp)
		  case Some(exp ~ expList) => exp::expList
		}
		
		def term: Parser[Expression] = assignment | iteration | deref | block | lambda | literal | identifier | "(" ~> expression <~ ")"
		
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
		
		def number: Parser[Number] = """(\+|-)?[0-9]+(\.[0-9]+)?""".r ^^ {
		  case x => new Number(x.toDouble)
		}
		
		def exponent: Parser[Expression] = "exp" ~ "(" ~ number ~ "," ~ number ~ ")" ^^ {
		  	case "exp" ~ "(" ~ num1 ~ "," ~ num2 ~ ")" => expressions.FunCall(Identifier("exp"), List(num1,num2))
		}
		
		def block: Parser[Expression] = "{" ~> (expression ~ rep(";" ~> expression)) <~ "}" ^^ {
		  case exp ~ Nil => new Block(exp::Nil)
		  case exp ~ expList => new Block(exp::expList)
		}
		
		def lambda : Parser[Expression] = "lambda" ~> parameters ~ expression ^^ {
		  case param ~ exp => new Lambda(param, exp)
		}
		
		def parameters: Parser[List[Identifier]] =  "(" ~> opt(identifier ~ rep(","~>identifier))<~")" ^^ {
		  case None => Nil 
		  case Some(e ~ Nil) => List(e) 
		  case Some(e ~ exps) => e::exps 
		  case _ => Nil
		}
		
		def assignment: Parser[Expression] = identifier ~ "=" ~ expression ^^ {
		  case id ~ "=" ~ exp => new Assignment(id, exp)
		}
		
		def iteration: Parser[Expression] = "while" ~ "(" ~ expression ~ ")" ~ expression ^^ {
		  case "while" ~ "(" ~ exp ~ ")" ~ exp2 => new Iteration(exp, exp2)
		} 
		
		def deref: Parser[Expression] = "[" ~ expression ~ "]" ^^ {
		  case "[" ~ exp ~ "]" => expressions.FunCall(new Identifier("val"), List(exp))
		}
}