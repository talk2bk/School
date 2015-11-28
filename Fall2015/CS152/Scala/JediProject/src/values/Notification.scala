package values


case class Notification(message: String) extends Value{
override def toString = message
}

object Notification{
val OK = Notification("OK")
val UNKNOWN = Notification("UNKNOWN")
val DONE = Notification("DONE")
val UNSPECIFIED = Notification("UNSPECIFIED")
val UNIMPLEMENTED = Notification("UNIMPLEMENTED")
}