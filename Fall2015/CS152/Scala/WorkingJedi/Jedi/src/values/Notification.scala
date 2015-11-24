package values

class Notification (msg: String) extends Value {
	override def toString = msg
}

object Notification {
  val UNKNOWN = new Notification("unknown")
  val OK = new Notification("ok")
  val ERROR = new Notification("error")
  val BINDINGCREATED = new Notification("binding created")
  val VARIABLEUPDATED = new Notification("variable updated")
}