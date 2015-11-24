package ui

import value._
import expressions._
import scala.util.parsing.combinator.Parsers

class SyntaxException(val result: Parsers#Failure = null) extends JediException("Syntax Error") {
		val msg = result
}