package ui
import expression._
import values._

object console {
   val parser = new Parser
   val globalEnv = new Environment()
   // etc.
   def repl {
      while(more) {
         try {
            print("-> ")
            val cmmd = readLine();
           // handle meta commands: print, execute, save, open, etc. else
            val exp = parse cmmd
            println(exp.execute(globalEnv))
         }
         catch {
            // handle each type of exception separately
         } finally {
            // flush input stream
         }
      }
   } // repl
    
  def main(args: Array[String]): Unit = { repl }

} // console