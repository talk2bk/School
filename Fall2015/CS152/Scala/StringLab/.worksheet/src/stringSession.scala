import scala.util._

object stringSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(87); 
  println("Welcome to the Scala worksheet");$skip(90); 
	
	//1.
	def isPal(word: String) = word.trim.toLowerCase == word.trim.toLowerCase.reverse;System.out.println("""isPal: (word: String)Boolean""");$skip(18); val res$0 = 
	isPal("rotator");System.out.println("""res0: Boolean = """ + $show(res$0));$skip(15); val res$1 = 
	isPal("mom ");System.out.println("""res1: Boolean = """ + $show(res$1));$skip(18); val res$2 = 
	isPal("Racecar");System.out.println("""res2: Boolean = """ + $show(res$2));$skip(74); 
	
	
	def isLetter(c: Char) = 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';System.out.println("""isLetter: (c: Char)Boolean""");$skip(69); 
	//2.
	def isPal2(phrase: String) = isPal(phrase.filter(isLetter _));System.out.println("""isPal2: (phrase: String)Boolean""");$skip(21); val res$3 = 
	
	isPal2("rotator");System.out.println("""res3: Boolean = """ + $show(res$3));$skip(16); val res$4 = 
	isPal2("mom ");System.out.println("""res4: Boolean = """ + $show(res$4));$skip(19); val res$5 = 
	isPal2("Racecar");System.out.println("""res5: Boolean = """ + $show(res$5));$skip(43); val res$6 = 
	isPal2("A man, a plan, a canal, Panama!");System.out.println("""res6: Boolean = """ + $show(res$6));$skip(54); 
	
	//example
	def echo(word: String = "hello") = word;System.out.println("""echo: (word: String)String""");$skip(46); val res$7 = 
	//default things are neato
	echo("football");System.out.println("""res7: String = """ + $show(res$7));$skip(8); val res$8 = 
	echo();System.out.println("""res8: String = """ + $show(res$8));$skip(53); 
	
	//3.
	def mkPal(word: String) = word+word.reverse;System.out.println("""mkPal: (word: String)String""");$skip(17); val res$9 = 
	
	mkPal("mars");System.out.println("""res9: String = """ + $show(res$9));$skip(253); 
	// => "marssram"
	
	//4.
	def mkWord(length: Int = 10) =  {
	val string = new StringBuilder(length)
	val alphabet = "abcdefghijklmnopqrstuvwxyz"
	for(i <-0 until length){
	string.append(alphabet(Random.nextInt(alphabet.length)))
	}
	string.toString
	};System.out.println("""mkWord: (length: Int)String""");$skip(10); val res$10 = 

mkWord();System.out.println("""res10: String = """ + $show(res$10));$skip(44); val res$11 = 
//   => a1  : String = ltikizlrbmx
mkWord();System.out.println("""res11: String = """ + $show(res$11));$skip(44); val res$12 = 
//   => a2  : String = iceqyxdtcqx
mkWord();System.out.println("""res12: String = """ + $show(res$12));$skip(46); val res$13 = 
//   => a3  : String = dcjjqsecegi
mkWord(20);System.out.println("""res13: String = """ + $show(res$13));$skip(213); 
// => a4  : String = erlazfucnscevefzaaviv

//5.
def mkSentence(length: Int = 10) = {
var result = mkWord().capitalize
for(i <- 1 until length){
result += " " + mkWord(Random.nextInt(10))
}
result += "."
result
};System.out.println("""mkSentence: (length: Int)String""");$skip(14); val res$14 = 

mkSentence();System.out.println("""res14: String = """ + $show(res$14));$skip(13); val res$15 = 
mkSentence();System.out.println("""res15: String = """ + $show(res$15));$skip(13); val res$16 = 
mkSentence();System.out.println("""res16: String = """ + $show(res$16));$skip(14); val res$17 = 
mkSentence(5);System.out.println("""res17: String = """ + $show(res$17));$skip(84); 

//6.
def shuffle(word: String) = word.drop(word.length/2)+word.take(word.length/2);System.out.println("""shuffle: (word: String)String""");$skip(24); val res$18 = 


shuffle("abcdefghij");System.out.println("""res18: String = """ + $show(res$18));$skip(39); val res$19 = 
// = fghijabcde
shuffle("abcdefghijk");System.out.println("""res19: String = """ + $show(res$19));$skip(114); 
// = fghijkabcde

//7.
def countSubstrings(piece: String, phrase: String) = piece.r.findAllMatchIn(phrase).length;System.out.println("""countSubstrings: (piece: String, phrase: String)Int""");$skip(38); val res$20 = 

countSubstrings("is", "Mississippi");System.out.println("""res20: Int = """ + $show(res$20));$skip(61); 
// => 2

//10.
def first(array: Array[String]) = {array.min};System.out.println("""first: (array: Array[String])String""");$skip(35); val res$21 = 

first(Array("cat", "rat", "bat"));System.out.println("""res21: String = """ + $show(res$21))}
// = "bat"

}
