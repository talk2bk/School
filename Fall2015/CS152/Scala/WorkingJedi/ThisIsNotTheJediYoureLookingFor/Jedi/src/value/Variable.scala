package value

case class Variable(input: Value = null) extends Value {
	var content = input

	def Variable(vals: List[Value]) = new Variable(vals(0))
	
	def execute(env: Environment): Value = this.content
	
	def change(in: Value) = {this.content = in}
	
	override def toString() = "Variable(" + this.content + ")"
}