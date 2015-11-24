package ui

import scala.util.parsing.combinator._
import expressions._
import values._

class WookieParsers extends RegexParsers {

  val numb = """(\+|-)?[0-9]+(\.[0-9]+)?""".r
  val trueFalse = """true|false""".r
  val alphaNum = """[a-zA-z][a-zA-Z0-9]*""".r 
  
  
  
  def expression: Parser[Expression] = declaration | conditional | disjunction | failure("Invalid expression")
  
  def declaration: Parser[Expression] = ("def"~identifier~"="~expression) ^^
  {
     case "def"~x~"="~y => Declaration(x.asInstanceOf[Identifier], y.asInstanceOf[Expression])
  }
  
  def identifier: Parser[Identifier] = alphaNum ^^
  {
    case z => Identifier(z)
  }
  

 
  def conditional: Parser[Conditional] = "if"~"("~expression~")"~expression~opt("else"~expression) ^^
  {
     case "if"~"("~e1~")"~e2~None => Conditional(e1, e2)
     case "if"~"("~e1~")"~e2~Some("else"~e3) => Conditional(e1, e2, e3)
  }
  
 
  def conjunction: Parser[Expression] = equality~rep("&&" ~> equality) ^^
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
    case t ~ Some(Nil) => FunCall(t, Nil)
    case t~Some(ops) => FunCall(t, ops)
    //case t~literal => throw new JediException
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
  
  def term: Parser[Expression] =  lambda | block | literal | identifier | "("~>expression<~")" | failure("Invalid expression")

  def literal: Parser[Literal] = boole | numeral
  
  def numeral: Parser[Number] = numb ^^
  {
    case e => Number(e.toDouble)
  }
  def boole: Parser[Boole] = trueFalse ^^
  {
    case e => Boole(e.toBoolean)
  }
  
  def parameters: Parser[List[Identifier]] =  "(" ~> opt(identifier ~ rep(","~>identifier))<~")" ^^
  {
   case None => Nil 
   case Some(e ~ Nil) => List(e) 
   case Some(e ~ exps) => e::exps 
   case _ => Nil
  }
  
  def block: Parser[Expression] = "{" ~>expression ~ rep(";" ~>expression)<~ "}" ^^
  {
    case e ~ stuff => Block(e :: stuff)
  }
  def lambda: Parser[Expression] = "lambda" ~ parameters ~ expression ^^
  {
    case "lambda" ~ p ~ e => Lambda(p, e)
  }
}