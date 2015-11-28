package values
import expression._
/**
 * @author CuTs
 */
case class Number(value: Double) extends Literal {
def +(other: Number) = new Number(this.value + other.value)
def -(other: Number) = new Number(this.value - other.value)
def *(other: Number) = new Number(this.value * other.value)
def /(other: Number) = new Number(this.value / other.value)
def ==(other: Number) = new Boole(this.value == other.value)
def <(other: Number) = new Boole(this.value < other.value)

override def toString = ""+value

def this(value: String) = this(value.toDouble)

}

