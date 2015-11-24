package ui
import scala.util.parsing.combinator._

import expression._
import values._


class EwokParsers extends RegexParsers{
def expression: Parser[Expression] = declaration | conditional | disjunction | failure("Invalid Expression")

def declaration: Parser[Expression] = "def"~identifier~"="~expression ^^
{
   case "def"~id~"="~exp => Declaration(id, exp)
}
def identifier: Parser[Identifier] = """[a-zA-z][a-zA-Z0-9]*""".r ^^
{
  case id => Identifier(id)
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
    case Some(t~Nil) => List(t)
    case Some(t~z) => t :: z
    /*
     * some(things~Nil) => List(things)
     * some(things~moreThings) => things :: moreThings
     */
  }
  
  def funcall: Parser[Expression] = term ~ opt(operands) ^^
  {
    case t~None => t
    case t ~ Some(Nil) => FunCall(t.asInstanceOf[Identifier], Nil)
    case t~Some(ops) => FunCall(t.asInstanceOf[Identifier], ops.asInstanceOf[List[Expression]])
    case t~literal => throw new JediException
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
  
  def term: Parser[Expression] = literal | identifier | "("~>expression<~")" | failure("Invalid expression")

  def literal: Parser[Literal] = boole | numeral
  
  def numeral: Parser[Number] = """(\+|-)?[0-9]+(\.[0-9]+)?""".r ^^
  {
    case e => new Number(e.toDouble)
  }
  def boole: Parser[Boole] = """true|false""".r ^^
  {
    case e => new Boole(e.toBoolean)
  }

}