package ui
import expression._
import scala.util.parsing.combinator._
/**
 * @author CuTs
 */
case class SyntaxException(val result: Parsers#Failure = null) extends JediException("Syntax Error"){
  
}