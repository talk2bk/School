object stringSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(66); 
  println("Welcome to the Scala worksheet");$skip(84); 
	
	def isPal(word: String) = word.trim.toLowerCase == word.trim.toLowerCase.reverse;System.out.println("""isPal: (word: String)Boolean""");$skip(18); val res$0 = 
	isPal("rotator");System.out.println("""res0: Boolean = """ + $show(res$0));$skip(15); val res$1 = 
	isPal("mom ");System.out.println("""res1: Boolean = """ + $show(res$1));$skip(18); val res$2 = 
	isPal("Racecar");System.out.println("""res2: Boolean = """ + $show(res$2));$skip(74); 
	
	
	def isLetter(c: Char) = 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';System.out.println("""isLetter: (c: Char)Boolean""");$skip(65); 
	
	def isPal2(phrase: String) = isPal(phrase.filter(isLetter _));System.out.println("""isPal2: (phrase: String)Boolean""");$skip(21); val res$3 = 
	
	isPal2("rotator");System.out.println("""res3: Boolean = """ + $show(res$3));$skip(16); val res$4 = 
	isPal2("mom ");System.out.println("""res4: Boolean = """ + $show(res$4));$skip(19); val res$5 = 
	isPal2("Racecar");System.out.println("""res5: Boolean = """ + $show(res$5));$skip(43); val res$6 = 
	isPal2("A man, a plan, a canal, Panama!");System.out.println("""res6: Boolean = """ + $show(res$6));$skip(43); 
	
	def echo(word: String = "hello") = word;System.out.println("""echo: (word: String)String""");$skip(46); val res$7 = 
	//default things are neato
	echo("football");System.out.println("""res7: String = """ + $show(res$7));$skip(8); val res$8 = 
	echo();System.out.println("""res8: String = """ + $show(res$8))}
}
