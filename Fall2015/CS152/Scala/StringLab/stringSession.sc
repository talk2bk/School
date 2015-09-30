object stringSession {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
	
	def isPal(word: String) = word.trim.toLowerCase == word.trim.toLowerCase.reverse
                                                  //> isPal: (word: String)Boolean
	isPal("rotator")                          //> res0: Boolean = true
	isPal("mom ")                             //> res1: Boolean = true
	isPal("Racecar")                          //> res2: Boolean = true
	
	
	def isLetter(c: Char) = 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z'
                                                  //> isLetter: (c: Char)Boolean
	
	def isPal2(phrase: String) = isPal(phrase.filter(isLetter _))
                                                  //> isPal2: (phrase: String)Boolean
	
	isPal2("rotator")                         //> res3: Boolean = true
	isPal2("mom ")                            //> res4: Boolean = true
	isPal2("Racecar")                         //> res5: Boolean = true
	isPal2("A man, a plan, a canal, Panama!") //> res6: Boolean = true
	
	def echo(word: String = "hello") = word   //> echo: (word: String)String
	//default things are neato
	echo("football")                          //> res7: String = football
	echo()                                    //> res8: String = hello
}