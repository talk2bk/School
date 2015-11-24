package values

import expressions.Literal

case class Boole(val value: Boolean) extends Literal with Value {

  def getVal() = value
  
  def &&(other: Boole) = new Boole(value.&&(other.getVal))
  
  
  def ||(other: Boole) = new Boole(value.||(other.getVal))
  
 // def !(other: Boole) = new Boole(value.!=(other.getVal))
  
  def !=(other: Boole) = {
    new Boole(value != other.value)
  }
  
  //def !(other: Boole) = new Boole(!this.value)
  def not() = new Boole(!this.value)
  
  override def toString() = value + ""
  
}
/*
object Boole {
  def test() {
    val b1 = new Boole(true)
    val b2 = new Boole(false)
    println("b1 = true. b2 = false")
   // println("b1 != b2 = " + (b1 ! b2))
    println("b1 && b2 = " + (b1 && b2))
    println("b1 or b2 = " + (b1 || b2))
    
  }
}
*/