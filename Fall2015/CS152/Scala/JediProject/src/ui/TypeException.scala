package ui

/**
 * @author CuTs
 */
case class TypeException(val symbol: String) extends JediException("Unidentified identifier: " + symbol){
  
}