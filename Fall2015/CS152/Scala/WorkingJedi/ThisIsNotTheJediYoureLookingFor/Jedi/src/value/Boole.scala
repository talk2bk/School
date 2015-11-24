package value

import expressions.Literal

class Boole(valuein: Boolean) extends Literal {
		val bool = valuein
		def &&(other: Boole) =
		if(bool == false) {
			new Boole(false)
		} else {
			if(other.bool == false) {
				new Boole(false)
			} else {
				new Boole(true)
			}
		}
		def ||(other: Boole) =
		if(bool == true) {
			new Boole(true)
		} else {
			if(other.bool == true) {
				new Boole(true)
			} else {
				new Boole(false)
			}
		}
		def !(other: Boole) = if(this.bool == true) {
			new Boole(false)
		} else {
			new Boole(true)
		}
		override def toString = ("" + bool + "")
		
		def this(s: String) = this(s.toBoolean)
}

object Boole {
	def test() {
	  	val n1 = new Boole(true)
	  	val n2 = new Boole(false)
	  	val n3 = new Boole(true)
	  	println(n1)
	  	println(n1 && n2)
	  	println(n1 || n2)
	  	println(n1 && n3)
	}
}