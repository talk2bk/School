package ui

/**
 * @author CuTs
 */
case class UndefinedException(val symbol: String) extends JediException("Undefined Identifier: " + symbol){
  
}