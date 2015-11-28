package values
import expression._
/**
 * @author CuTs
 */
case class Boole(value: Boolean) extends Literal {
def &&(other: Boole) = new Boole(this.value && other.value)
def ||(other: Boole) = new Boole(this.value || other.value)
def ! = new Boole(!this.value)

override def toString = ""+value
def this(value: String) = this(value.toBoolean)
}