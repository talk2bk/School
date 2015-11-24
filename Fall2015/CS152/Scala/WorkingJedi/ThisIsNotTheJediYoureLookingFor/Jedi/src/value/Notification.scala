package value

import ui._
import value._
import expressions._

case class Notification(msg: String) extends Value {
		override def toString = msg
		
		def unknown() = new Notification("Unidentified identifier: " + msg)
}

object Notification {
		val UNKNOWN = new Notification("unknown")
		val OK = new Notification("ok")
		val UPDATE = new Notification("variable updated")
		val BIND = new Notification("binding created")
		val ERROR = new Notification("error")
		val DONE = new Notification("done")
}