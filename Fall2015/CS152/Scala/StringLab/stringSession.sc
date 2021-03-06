import scala.util._

object stringSession {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
	
	//1.
	def isPal(word: String) = word.trim.toLowerCase == word.trim.toLowerCase.reverse
                                                  //> isPal: (word: String)Boolean
	isPal("rotator")                          //> res0: Boolean = true
	isPal("mom ")                             //> res1: Boolean = true
	isPal("Racecar")                          //> res2: Boolean = true
	
	
	def isLetter(c: Char) = 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z'
                                                  //> isLetter: (c: Char)Boolean
	//2.
	def isPal2(phrase: String) = isPal(phrase.filter(isLetter _))
                                                  //> isPal2: (phrase: String)Boolean
	
	isPal2("rotator")                         //> res3: Boolean = true
	isPal2("mom ")                            //> res4: Boolean = true
	isPal2("Racecar")                         //> res5: Boolean = true
	isPal2("A man, a plan, a canal, Panama!") //> res6: Boolean = true
	
	//example
	def echo(word: String = "hello") = word   //> echo: (word: String)String
	//default things are neato
	echo("football")                          //> res7: String = football
	echo()                                    //> res8: String = hello
	
	//3.
	def mkPal(word: String) = word+word.reverse
                                                  //> mkPal: (word: String)String
	
	mkPal("mars")                             //> res9: String = marssram
	// => "marssram"
	
	//4.
	def mkWord(length: Int = 10) =  {
	val string = new StringBuilder(length)
	val alphabet = "abcdefghijklmnopqrstuvwxyz"
	for(i <-0 until length){
	string.append(alphabet(Random.nextInt(alphabet.length)))
	}
	string.toString
	}                                         //> mkWord: (length: Int)String

mkWord()                                          //> res10: String = vidnckdxbh
//   => a1  : String = ltikizlrbmx
mkWord()                                          //> res11: String = rbbbpbdqnb
//   => a2  : String = iceqyxdtcqx
mkWord()                                          //> res12: String = gmrimqshck
//   => a3  : String = dcjjqsecegi
mkWord(20)                                        //> res13: String = bvxdieutpwwjnfygtlnf
// => a4  : String = erlazfucnscevefzaaviv

//5.
def mkSentence(length: Int = 10) = {
var result = mkWord().capitalize
for(i <- 1 until length){
result += " " + mkWord(Random.nextInt(10))
}
result += "."
result
}                                                 //> mkSentence: (length: Int)String

mkSentence()                                      //> res14: String = Hxqeoeuarh my mvk mjrqxwqky blmtrqu dzjbjl  fluaglo wyikoyc
                                                  //| nk k.
mkSentence()                                      //> res15: String = Qgfardvfhk ghvnne xbdeph uwkktjnpe ncdxvgku yxz aryua dv ad
                                                  //| oz x.
mkSentence()                                      //> res16: String = Ewciwdklmn mkdxj clqlzyrph hptfcsn qqvpyjpo d kmyylqy i zuz
                                                  //| wxekqr nlj.
mkSentence(5)                                     //> res17: String = Pzcznjierv evjsaw fbxuwhb jrgbknysl vvn.

//6.
def shuffle(word: String) = word.drop(word.length/2)+word.take(word.length/2)
                                                  //> shuffle: (word: String)String


shuffle("abcdefghij")                             //> res18: String = fghijabcde
// = fghijabcde
shuffle("abcdefghijk")                            //> res19: String = fghijkabcde
// = fghijkabcde

//7.
def countSubstrings(piece: String, phrase: String) = piece.r.findAllMatchIn(phrase).length
                                                  //> countSubstrings: (piece: String, phrase: String)Int

countSubstrings("is", "Mississippi")              //> res20: Int = 2
// => 2

//10.
def first(array: Array[String]) = {array.min}     //> first: (array: Array[String])String

first(Array("cat", "rat", "bat"))                 //> res21: String = bat
// = "bat"

}