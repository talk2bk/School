package ui

/**
 * @author CuTs
 */
case class UndefinedException(val error: String) extends JediException("undefined exception: "+error){
  
}