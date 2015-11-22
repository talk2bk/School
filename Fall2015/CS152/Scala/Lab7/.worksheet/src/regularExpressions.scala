object regularExpressions {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(135); 
//social security numbers
val ssnPattern = """^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$""";System.out.println("""ssnPattern  : String = """ + $show(ssnPattern ));$skip(68); val res$0 = 
                                 
"123-45-6789".matches(ssnPattern);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(34); val res$1 = 
"000-45-6789".matches(ssnPattern);System.out.println("""res1: Boolean = """ + $show(res$1));$skip(82); 
//simple sentences
val simplePattern = """[A-Z][a-zA-Z0-9]+(\s[a-zA-Z0-9]+)+\.""";System.out.println("""simplePattern  : String = """ + $show(simplePattern ));$skip(48); val res$2 = 
"Hello I am a sentence.".matches(simplePattern);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(44); val res$3 = 
"Hello am sentence?".matches(simplePattern);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(48); val res$4 = 
"this is not a sentence".matches(simplePattern);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(68); 
//hexadecimal numbers
val hexPattern = """(?:0[xX])?[0-9a-fA-F]+""";System.out.println("""hexPattern  : String = """ + $show(hexPattern ));$skip(32); val res$5 = 
"0x3734c98".matches(hexPattern);System.out.println("""res5: Boolean = """ + $show(res$5));$skip(32); val res$6 = 
"0x3734d08".matches(hexPattern);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(29); val res$7 = 
"nothex".matches(hexPattern);System.out.println("""res7: Boolean = """ + $show(res$7));$skip(120); 
//URLs
val urlPattern = """(http://)?www(\.[a-zA-Z0-9]+)+\.(com|org|gov|edu)((/[a-zA-Z0-9]+)+([a-zA-Z0-9]+)\.html?)?""";System.out.println("""urlPattern  : String = """ + $show(urlPattern ));$skip(96); val res$8 = 
"http://www.cs.sjsu.edu/faculty/pearce/modules/courses/F15/cs152/index.htm".matches(urlPattern);System.out.println("""res8: Boolean = """ + $show(res$8));$skip(40); val res$9 = 
"http://www.cs.edu".matches(urlPattern);System.out.println("""res9: Boolean = """ + $show(res$9));$skip(39); val res$10 = 
"www.notaurl.nope".matches(urlPattern);System.out.println("""res10: Boolean = """ + $show(res$10));$skip(62); 
//C identifiers
val cPattern = """[_A-Za-z]{1}[A-Za-z0-9]+""";System.out.println("""cPattern  : String = """ + $show(cPattern ));$skip(25); val res$11 = 
"_yes".matches(cPattern);System.out.println("""res11: Boolean = """ + $show(res$11));$skip(24); val res$12 = 
"Yes".matches(cPattern);System.out.println("""res12: Boolean = """ + $show(res$12));$skip(25); val res$13 = 
"yiss".matches(cPattern);System.out.println("""res13: Boolean = """ + $show(res$13));$skip(25); val res$14 = 
"8=no".matches(cPattern);System.out.println("""res14: Boolean = """ + $show(res$14));$skip(99); 

//US Phone numbers
val phonePattern = """(1|001)?(\([1-9][0-9]{2}\))?[1-9][0-9][0-9]-[0-9]{4}""";System.out.println("""phonePattern  : String = """ + $show(phonePattern ));$skip(38); val res$15 = 
"(408)696-9696".matches(phonePattern);System.out.println("""res15: Boolean = """ + $show(res$15));$skip(39); val res$16 = 
"1(408)696-9696".matches(phonePattern);System.out.println("""res16: Boolean = """ + $show(res$16));$skip(33); val res$17 = 
"696-9696".matches(phonePattern);System.out.println("""res17: Boolean = """ + $show(res$17));$skip(28); val res$18 = 
"911".matches(phonePattern);System.out.println("""res18: Boolean = """ + $show(res$18));$skip(33); val res$19 = 
"911-6969".matches(phonePattern);System.out.println("""res19: Boolean = """ + $show(res$19))}

}
