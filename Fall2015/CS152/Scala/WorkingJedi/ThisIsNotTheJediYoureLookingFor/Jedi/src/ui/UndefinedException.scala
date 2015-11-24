package ui

class UndefinedException(input: String) extends JediException("Undefined Error") {
		val msg = input
		def this() = this("Undefined Error")
}