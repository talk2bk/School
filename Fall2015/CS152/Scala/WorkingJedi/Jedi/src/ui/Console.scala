package ui
import values._
import expressions._

object Console {
	val parser = new SithParsers
	val globalEnv = new Environment()
	var verbose = false
	
	def execute(cmmd: String): String = {
	  val tree = parser.parseAll(parser.expression, cmmd)
	  tree match {
	    case t: parser.Failure => throw new SyntaxException(t)
	    case _ => "" + tree.get.execute(globalEnv)
	  }
	}
	def repl {
	  var cmmd: String = ""
	  var more: Boolean = true
	  
	  while(more) {
	    try {
	      print("-> ")
	      cmmd = readLine()
	      if (cmmd == "help") {
	        println("help")
	      }
	      else if (cmmd == "quit") {
	        println("Bye")
	        more = false
	      } else {
	        println(execute(cmmd))
	      }
	    }
	    catch {
	      case e: SyntaxException => {
	        println(e.msg)
	        println(e.result.msg)
	        println("line # = " + e.result.next.pos.line)
	        println("column # = " + e.result.next.pos.column)
	        println("token = " + e.result.next.first)
	      }
	      case e: TypeException => {print(e.msg); if (verbose) more = false}
	      case e: UndefinedException => {println("error " + e); if (verbose) more = false}
	      case e: JediException => {println(e.msg); }
	      case e: Throwable => {println ("error: " + e); more = false}
	    }
	  }
	}
	
	
  def main(args: Array[String]): Unit = {repl}
}