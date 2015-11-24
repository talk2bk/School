package value

import expressions.Literal

class Number(valuein: Double) extends Literal {
		val value = valuein
		def +(other: Number): Number = new Number(this.value + other.value)
		def *(other: Number): Number = new Number(this.value * other.value)
		def -(other: Number): Number = new Number(this.value - other.value)
		def /(other: Number): Number = new Number(this.value / other.value)
		def ==(other:Number) = if((this.value - other.value) == 0) {new Boole(true)} else {new Boole(false)}
		def <(other: Number) = if((this.value - other.value) > 0) {new Boole(false)} else {new Boole(false)}
		override def toString = ("" + value + "")
		def this(s: String) = this(s.toDouble)
}

object Number {
	def test() {
	  	val n1 = new Number(10)
	  	val n2 = new Number(3.14)
	  	println(n1 + n2)
	  	println(n1 * n2)
	  	println(n1 - n2)
	  	println(n1 / n2)
	  	println(n1 == n2)
	  	println(n1 < n2)
	}
}