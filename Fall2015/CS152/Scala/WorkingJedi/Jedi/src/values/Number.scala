package values

import expressions.Literal

case class Number(val value: Double) extends Literal with Value {

  def getVal() = value
  
  def this(s:String) {this(s.toDouble)}
  def +(other: Number) = 
    new Number(value.+(other.value))
  
  def -(other: Number) = 
    new Number(value.-(other.value))
  
  def *(other: Number) =
    new Number(value.*(other.value))
  
  def /(other: Number) = 
    new Number(value./(other.value))
 
  def ==(other: Number) = {
    if (value.equals(other.value))
      new Boole(true)
    else
      new Boole(false)
  }
  /*
  def !=(other: Number) = {
    if (value.!=(other.value))
      new Boole(true)
    else
      new Boole(false)
  }*/
  
  def <(other: Number) = {
    if(value.<(other.value)) 
      new Boole(true) 
    else 
      new Boole(false)
  }
  
  def >(other: Number) = {
    if (value.>(other.value))
      new Boole(true)
    else
      new Boole(false)
  }
    
  override def toString() = value + "" 
}
/*
object Number {
  def test() {
    val n1 = new Number(10)
    val n2 = new Number(3.14)
    val n3 = new Number(10)
    println("10 + 3.14 = " + (n1 + n2))
    println("10 - 3.14 = " + (n1 - n2))
    println("10 * 3.14 = " + (n1 * n2))
    println("10 / 3.14 = " + (n1 / n2))
    println("10 == 3.14 = " + (n1 == n2))
    println("10 == 10 = " + (n1 == n3))
    println("10 < 3.14 = " + (n1 < n2))
    println("10 > 3.14 = " + (n1 > n2))
  }
}
*/