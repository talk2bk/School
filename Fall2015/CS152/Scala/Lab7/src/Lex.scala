object Lex extends App {
  trait Expression
  case class Number(val value: Double) extends Expression
  case class FunCall(val operand1: Expression, val operation: String, val operand2: Expression) extends Expression

  val plus = "+"
  val times = "*"
  def execute(e: Expression): Double = {
    e match {
      case e: Number => e.value
      case e: FunCall => {
          e.operation match {
        case "+" => execute(e.operand1) + execute(e.operand2)
        case "*" => execute(e.operand1) * execute(e.operand2)
        case _   => throw new Exception("operator isn't + or *")
          }
      }
    }

  }

  val exp1 = FunCall(Number(3.14), plus, Number(2.71)) // 3.14 + 2.71
  println(execute(exp1)) // prints 5.85

  val exp2 = FunCall(Number(5.0), times, exp1) // 5.0 * (3.14 + 2.71)
  println(execute(exp2)) // prints 29.25

}