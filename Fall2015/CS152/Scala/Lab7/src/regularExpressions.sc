object regularExpressions {
//social security numbers
val ssnPattern = """^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9]{4}$"""
                                                  //> ssnPattern  : String = ^(?!000|666)[0-8][0-9]{2}-(?!00)[0-9]{2}-(?!0000)[0-9
                                                  //| ]{4}$
                                 
"123-45-6789".matches(ssnPattern)                 //> res0: Boolean = true
"000-45-6789".matches(ssnPattern)                 //> res1: Boolean = false
//simple sentences
val simplePattern = """[A-Z][a-zA-Z0-9]+(\s[a-zA-Z0-9]+)+\."""
                                                  //> simplePattern  : String = [A-Z][a-zA-Z0-9]+(\s[a-zA-Z0-9]+)+\.
"Hello I am a sentence.".matches(simplePattern)   //> res2: Boolean = true
"Hello am sentence?".matches(simplePattern)       //> res3: Boolean = false
"this is not a sentence".matches(simplePattern)   //> res4: Boolean = false
//hexadecimal numbers
val hexPattern = """(?:0[xX])?[0-9a-fA-F]+"""     //> hexPattern  : String = (?:0[xX])?[0-9a-fA-F]+
"0x3734c98".matches(hexPattern)                   //> res5: Boolean = true
"0x3734d08".matches(hexPattern)                   //> res6: Boolean = true
"nothex".matches(hexPattern)                      //> res7: Boolean = false
//URLs
val urlPattern = """(http://)?www(\.[a-zA-Z0-9]+)+\.(com|org|gov|edu)((/[a-zA-Z0-9]+)+([a-zA-Z0-9]+)\.html?)?"""
                                                  //> urlPattern  : String = (http://)?www(\.[a-zA-Z0-9]+)+\.(com|org|gov|edu)((/[
                                                  //| a-zA-Z0-9]+)+([a-zA-Z0-9]+)\.html?)?
"http://www.cs.sjsu.edu/faculty/pearce/modules/courses/F15/cs152/index.htm".matches(urlPattern)
                                                  //> res8: Boolean = true
"http://www.cs.edu".matches(urlPattern)           //> res9: Boolean = true
"www.notaurl.nope".matches(urlPattern)            //> res10: Boolean = false
//C identifiers
val cPattern = """[_A-Za-z]{1}[A-Za-z0-9]+"""     //> cPattern  : String = [_A-Za-z]{1}[A-Za-z0-9]+
"_yes".matches(cPattern)                          //> res11: Boolean = true
"Yes".matches(cPattern)                           //> res12: Boolean = true
"yiss".matches(cPattern)                          //> res13: Boolean = true
"8=no".matches(cPattern)                          //> res14: Boolean = false

//US Phone numbers
val phonePattern = """(1|001)?(\([1-9][0-9]{2}\))?[1-9][0-9][0-9]-[0-9]{4}"""
                                                  //> phonePattern  : String = (1|001)?(\([1-9][0-9]{2}\))?[1-9][0-9][0-9]-[0-9]{
                                                  //| 4}
"(408)696-9696".matches(phonePattern)             //> res15: Boolean = true
"1(408)696-9696".matches(phonePattern)            //> res16: Boolean = true
"696-9696".matches(phonePattern)                  //> res17: Boolean = true
"911".matches(phonePattern)                       //> res18: Boolean = false
"911-6969".matches(phonePattern)                  //> res19: Boolean = true

}