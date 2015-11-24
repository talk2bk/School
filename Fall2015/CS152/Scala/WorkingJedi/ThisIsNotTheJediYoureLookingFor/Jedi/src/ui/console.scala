package ui

import expressions._
import value._
import ui._

object console {
   val parser = new SithParsers
   val globalEnv = new Environment()
   
   def execute(cmmd: String): String = {
      val tree = parser.parseAll(parser.expression, cmmd)
      tree match {
         case t: parser.Failure => throw new SyntaxException(t)
         case _ => "" + tree.get.execute(globalEnv)
      }
   }
   
   def repl {
	  var more = true
      while(more) {
         try {
            print("-> ")
            val cmmd = readLine();
            
            // read/execute/print
            if(cmmd.toLowerCase().equals("quit")) {
               println("Goodbye.")
               more = false
            } else {
               println(execute(cmmd))
            }
         } catch {
         	case e: SyntaxException => {
               println(e.msg)
               println(e.result.msg)
               println("line # = " + e.result.next.pos.line)
               println("column # = " + e.result.next.pos.column)
               println("token = " + e.result.next.first)
            }
			case e: UndefinedException => {
			  println("Unidentified identifier: " + e.msg)
			}
			case e: TypeException => {
				println("TypeException: " + e.msg)
			}
			case e: JediException => println("Jedi Exception.")
			case e: Throwable => {
				e.printStackTrace()
			}
         } finally {
            Console.flush
         }
      }
   }
    
   def main(args: Array[String]): Unit = { repl }

}

