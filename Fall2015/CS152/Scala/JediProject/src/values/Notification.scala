package values


case class Notification(message: String) extends Value{
override def toString = message
}

object Notification{
val OK = new Notification("OK")
}