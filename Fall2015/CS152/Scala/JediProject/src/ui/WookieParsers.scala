package ui
import scala.util.parsing.combinator._

import expression._
import values._


/**
 * @author CuTs
 */
class WookieParsers extends RegexParsers{
  
def expression: Parser[Expression] = declaration | conditional | disjunction | failure("Invalid Expression")

def declaration: Parser[Expression] = "def"~identifier~"="~expression ^^
{
   case "def"~id~"="~exp => Declaration(id, exp)
}

def conditional: Parser[Conditional] = "if"~"("~expression~")"~expression~opt("else"~expression) ^^
  {
     case "if"~"("~exp1~")"~exp2~None => Conditional(exp1, exp2)
     case "if"~"("~exp1~")"~exp2~Some("else"~exp3) => Conditional(exp1, exp2, exp3)
  }
def conjunction: Parser[Expression] = equality ~ rep("&&" ~> equality) ^^
  {
    case x ~ Nil => x
    case x ~ y => Conjunction(x::y)
  }
def disjunction: Parser[Expression] = conjunction ~ rep("||" ~> conjunction) ^^
  {
    case x ~ Nil => x
    case x ~ y => Disjunction(x::y)
  }
def operands: Parser[List[Expression]] = "(" ~> opt(expression ~ rep("," ~> expression)) <~")" ^^
  {
    case None => Nil 
    case Some(e~Nil) => List(e)
    case Some(e~exps) => e::exps
    case _ => Nil
    
  }
  
  def funcall: Parser[Expression] = term ~ opt(operands) ^^
  {
    case t~None => t
    case t ~ Some(Nil) => FunCall(t, Nil)
    case t~Some(ops) => FunCall(t, ops)
  }
 
  
  def equality: Parser[Expression] = inequality~ rep("==" ~>inequality) ^^
  {
    case x ~ Nil => x
    case x ~ y => FunCall(Identifier("equals"), x::y)
  }
  
  def inequality: Parser[Expression] = sum~ rep(("<") ~>sum)^^
  {
    case x ~ Nil => x
    case x ~ y => FunCall(Identifier("less"), x :: y)
  }
  
  def negate(exp: Expression): Expression = {
   val sub = Identifier("sub")
   val zero = new Number(0)
   FunCall(sub, List(zero, exp))
  }
  
   def negateP(exp: Expression): Expression = {
    val div = Identifier("div")
    val one = new Number(1.0)
    FunCall(div, List(one, exp))
   }
    
  def product: Parser[Expression] = funcall ~ rep(("*"|"/") ~ funcall ^^ {case "*"~s=>s case "/"~s=>negateP(s)})^^
  {
    case x ~ Nil => x
    case x ~ y => FunCall(Identifier("mul"), x::y)
  }
  
 def sum: Parser[Expression] = 
   product ~ rep(("+"|"-") ~ product ^^ {case "+"~s=>s case "-"~s=>negate(s)})^^
   {
      case p~Nil=> p
      case p~rest=>FunCall(Identifier("add"), p::rest)
   }
  
  def term: Parser[Expression] = block | lambda | literal | identifier | "("~>expression<~")"

  def literal: Parser[Literal] = boole | numeral
  
  def numeral: Parser[Number] = """(\+|-)?[0-9]+(\.[0-9]+)?""".r ^^
  {
    case e => new Number(e.toDouble)
  }
  def boole: Parser[Boole] = """true|false""".r ^^
  {
    case e => new Boole(e.toBoolean)
  }
  def identifier: Parser[Identifier] = """[a-zA-z][a-zA-Z0-9]*""".r ^^
{
  case id => Identifier(id)
}

def block: Parser[Expression] = "{" ~> (expression ~ rep(";" ~> expression)) <~ "}" ^^ {
      case exp ~ Nil => Block(exp::Nil)
      case exp ~ expList => Block(exp::expList)
    }
    
def lambda : Parser[Expression] = "lambda" ~> parameters ~ expression ^^ {
      case param ~ exp => Lambda(param, exp)
    }

def parameters: Parser[List[Identifier]] =  "(" ~> opt(identifier ~ rep(","~>identifier))<~")" ^^
{
  case None => Nil
  case Some(e ~ Nil) => List(e)
  case Some(e ~ exps) => e::exps
  case _ => Nil
}
}