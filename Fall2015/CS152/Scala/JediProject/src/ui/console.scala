package ui
import expression._
import values._

object console {
   val parsers = new EwokParsers // for now
   val globalEnv = new Environment()

   def execute(cmmd: String): String = {
      val tree = parsers.parseAll(parsers.expression, cmmd)
      tree match {
         case t: parsers.Failure => throw new SyntaxException(t)
         case _ => "" + tree.get.execute(globalEnv)
      }
   }
  
    def repl {
      // declare locals
      var more = true
      var cmmd = ""
      while(more) {
         try {
            print("-> ")
            cmmd = readLine()
            if(cmmd == "help")println("help")
            else if(cmmd == "quit") {println("Bye"); more = false}
            else println(execute(cmmd))
         }
         catch {
            case e: SyntaxException => {
               println(e.msg)
               println(e.result.msg)
               println("line # = " + e.result.next.pos.line)
               println("column # = " + e.result.next.pos.column)
               println("token = " + e.result.next.first)
            }
            // handle other types of exceptions
         } finally {
            Console.flush
         }
      }
   }
    
   def main(args: Array[String]): Unit = { repl }
}