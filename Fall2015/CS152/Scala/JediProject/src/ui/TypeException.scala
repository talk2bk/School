package ui

/**
 * @author CuTs
 */
case class TypeException(val tmsg: String = "Type error") extends JediException(tmsg){
  
}