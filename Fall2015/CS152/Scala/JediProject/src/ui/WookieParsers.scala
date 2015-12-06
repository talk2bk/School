package ui
import scala.util.parsing.combinator._

import expression._
import values._


/**
 * @author CuTs
 */
class WookieParsers extends ui.EwokParsers{
  
override def term: Parser[Expression] = block | lambda | literal | identifier | "("~>expression<~")"

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