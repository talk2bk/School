package ui

import value._
import expressions._

object testDriver {
		def main(arg: Array[String]) {
				println("Starting number tests...")
				Number.test()
				println("Starting boole tests...")
				Boole.test()
				println("Starting Environment tests...")
				Environment.test()
				println("Starting System tests...")
				System.test()
				println("END")
		}
}