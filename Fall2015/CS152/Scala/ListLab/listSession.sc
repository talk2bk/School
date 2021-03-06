object listSession {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  //1.
  def isOdd(x: Int) = x%2 != 0                    //> isOdd: (x: Int)Boolean
  def cube(x: Int) = x * x * x                    //> cube: (x: Int)Int
  //iterative
  def sumOddCubes(list: List[Int]) = {
  var result = 0
  for(x <- list){ if(isOdd(x))result += cube(x) }
  result
  }                                               //> sumOddCubes: (list: List[Int])Int
  
  sumOddCubes(List(2,0, 3, 4, 5))                 //> res0: Int = 152
  sumOddCubes(List(1,3))                          //> res1: Int = 28
  
 	//recursive
 	def sumOddCubes2(nums: List[Int]): Int = {
 	if(nums == Nil) 0 else {
 	val butFirst = sumOddCubes2(nums.tail)
 	if(isOdd(nums.head)) cube(nums.head) + butFirst else butFirst
 	}
 	}                                         //> sumOddCubes2: (nums: List[Int])Int
 	sumOddCubes2(List(2,0, 3, 4, 5))          //> res2: Int = 152
  sumOddCubes2(List(1,3))                         //> res3: Int = 28
 	//tailrecursive
 	def sumOddCubes3(nums: List[Int]) = {
 	def helper(result: Int, unseen: List[Int]): Int = {
 	if(unseen == Nil) result
 	else{
 	val newResult = if(isOdd(unseen.head)) result + cube(unseen.head) else result
 	helper(newResult, unseen.tail)
 	}
 	}
 	helper(0, nums)
 	}                                         //> sumOddCubes3: (nums: List[Int])Int
 	sumOddCubes3(List(2,0, 3, 4, 5))          //> res4: Int = 152
  sumOddCubes3(List(1,3))                         //> res5: Int = 28
 	//mapfilterreduce
 	def sumOddCubes4(nums: List[Int]) = nums.filter(isOdd).map(cube).reduce(_ + _)
                                                  //> sumOddCubes4: (nums: List[Int])Int
  sumOddCubes4(List(2,0, 3, 4, 5))                //> res6: Int = 152
  sumOddCubes4(List(1,3))                         //> res7: Int = 28
  
//sumOddCubes3(List(1, 3).filter(isOdd))
 	
 	//2.
 	//iterative
 	def sumOfSums(list: List[List[Int]]) = {
 	var result = 0
 	for(x <- list){
 	for(y <- x){
 	result +=  y
 	}
 	}
 	result
 	}                                         //> sumOfSums: (list: List[List[Int]])Int
 	val test = List(List(1,2,3),List(4,5,6),List(7,8,9))
                                                  //> test  : List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
                                                  //| 
 	sumOfSums(test)                           //> res8: Int = 45
 	
  //recursive
  def sumOfSums2(list: List[List[Int]]): Int = {
  if(list == Nil) 0
  else list.head.sum + sumOfSums2(list.tail)
  }                                               //> sumOfSums2: (list: List[List[Int]])Int
  
  sumOfSums2(test)                                //> res9: Int = 45
  
  //tailrecursive
  def sumOfSums3(list: List[List[Int]]) = {
  def helper(result: Int, unseen: List[List[Int]]): Int = {
  if(unseen == Nil) result
  else{
  helper(unseen.head.sum+result,unseen.tail)
  }
  }
 	helper(0, list)
  }                                               //> sumOfSums3: (list: List[List[Int]])Int
  
  sumOfSums3(test)                                //> res10: Int = 45
  //mapfilterreduce
  def sum(list: List[Int]) = list.sum             //> sum: (list: List[Int])Int
  def sumOfSums4(list: List[List[Int]]) = list.map(sum _).reduce(_ + _)
                                                  //> sumOfSums4: (list: List[List[Int]])Int
  sumOfSums4(test)                                //> res11: Int = 45
  
  //3.
  //recursive
  //depth = max(depth(head) +1, depth (tail))
  def depth (list: List[Any]):Int = {
  if(list.asInstanceOf[List[Any]].size == 0) 1
  else if(list.head.isInstanceOf[List[Any]]){
  math.max(depth(list.head.asInstanceOf[List[Any]]) + 1, depth(list.tail))
  } else{depth(list.tail)}
  }                                               //> depth: (list: List[Any])Int
  //tailrecursive
  def isList(thing: Any) = thing.isInstanceOf[List[Any]]
                                                  //> isList: (thing: Any)Boolean
  def depth2(list: List[Any]) = {
  def helper(list2: List[Any], count: Int): Int = {
  if(list2 == Nil) count
  else if (list2.head.isInstanceOf[List[Any]]){
  math.max(helper(list2.head.asInstanceOf[List[Any]],count +1), helper(list2.tail,count))
  } else{helper(list2.tail, count)}
  }
  helper(list,1)
  }                                               //> depth2: (list: List[Any])Int
  
    //should return 4
  depth(List(List(List(1, 2, List(3)))))          //> res12: Int = 4
  depth2(List(List(List(1, 2, List(3)))))         //> res13: Int = 4
  
  //4.
  val numbers = List(0.0,1.0,2.0,3.0,4.0,5.0)     //> numbers  : List[Double] = List(0.0, 1.0, 2.0, 3.0, 4.0, 5.0)
  //iterative
  def average(list: List[Double]) = {
  var result = 0.0
  for(x <- list) result += x
  result/list.length
  }                                               //> average: (list: List[Double])Double
  //mapfilterreduce
  def average2(list: List[Double]) = {
  list.reduce(_ + _)/list.length
  }                                               //> average2: (list: List[Double])Double
   average(numbers)                               //> res14: Double = 2.5
   average2(numbers)                              //> res15: Double = 2.5
   
  //5.
  //iterative
  def max(list: List[Any]) = {
  var maximum = list.head
  for(i <- list){if( i.asInstanceOf[Comparable[Any]].compareTo(maximum) > 0) maximum = i }
  maximum
  }                                               //> max: (list: List[Any])Any
  max(numbers)                                    //> res16: Any = 5.0
  //tailrecursion
  def max2(list: List[Any]) = {
  if(list == Nil) 0
  def helper(maximum: Any, list2: List[Any]): Any = {
  if(list2 == Nil) maximum else{
  if(maximum.asInstanceOf[Comparable[Any]].compareTo(list2.head) > 0) helper(maximum, list2.tail)
  else helper(list2.head,list2.tail)
  }
  }
  helper(list(0), list.tail)
  }                                               //> max2: (list: List[Any])Any
  
  max2(numbers)                                   //> res17: Any = 5.0
 
 
  //6.
  //iterative
  def countPass[T] (list: List[T], pred: T=> Boolean): Int = {
  var count = 0
  for(x <- list){ if (pred(x)) {count += 1 }}
  count
  }                                               //> countPass: [T](list: List[T], pred: T => Boolean)Int
  //mapfilterreduce
  
  def countPass2[T] (list: List[T], pred: T=>Boolean) = list.filter(pred).length
                                                  //> countPass2: [T](list: List[T], pred: T => Boolean)Int
  
    def greaterThan2(num: Int) = {
  if(num > 2) true
  else false
  }                                               //> greaterThan2: (num: Int)Boolean
  
  val numbers2 = List(0,-1,2, 1, 2, 3, 4,5,6,3)   //> numbers2  : List[Int] = List(0, -1, 2, 1, 2, 3, 4, 5, 6, 3)
  countPass(numbers2,greaterThan2)                //> res18: Int = 5
  countPass2(numbers2,greaterThan2)               //> res19: Int = 5
  
  //7.
  //iterative
  def allPass[T] (list: List[T], pred: T=> Boolean): Boolean = {
  var result = true
  for(x <- list) {if(!pred(x)) result = false}
  result
  }                                               //> allPass: [T](list: List[T], pred: T => Boolean)Boolean
  
  //mapreducefilter
  def allPass2[T] (list: List[T], pred: T=> Boolean): Boolean = {
  val list2 = list.filter(pred)
  list2.length == list.length
  }                                               //> allPass2: [T](list: List[T], pred: T => Boolean)Boolean
  
  val numbers3 = List(0,-1,2, 1, 2, 3, 4,5,6,3)   //> numbers3  : List[Int] = List(0, -1, 2, 1, 2, 3, 4, 5, 6, 3)
  val numbers4 = List( 3, 4,5,6,3)                //> numbers4  : List[Int] = List(3, 4, 5, 6, 3)
  allPass(numbers3,greaterThan2)                  //> res20: Boolean = false
  allPass2(numbers3,greaterThan2)                 //> res21: Boolean = false
    allPass(numbers4,greaterThan2)                //> res22: Boolean = true
  allPass2(numbers4,greaterThan2)                 //> res23: Boolean = true
  //8.
  //iterative
  def somethingPass[T] (list: List[T], pred: T=>Boolean): Boolean = {
  var result = false
  for(x <- list) {if(pred(x)) return true}
  result
  }                                               //> somethingPass: [T](list: List[T], pred: T => Boolean)Boolean
  //mapreducefilter
  def somethingPass2[T] (list: List[T], pred: T=>Boolean): Boolean = list.filter(pred).length > 0
                                                  //> somethingPass2: [T](list: List[T], pred: T => Boolean)Boolean
  
    val numbers5 = List(0,-1,2, 1, 2, 3, 4,5,6,3) //> numbers5  : List[Int] = List(0, -1, 2, 1, 2, 3, 4, 5, 6, 3)
  val numbers6 = List( 0,-1,2,1,2)                //> numbers6  : List[Int] = List(0, -1, 2, 1, 2)
  somethingPass(numbers5,greaterThan2)            //> res24: Boolean = true
  somethingPass2(numbers5,greaterThan2)           //> res25: Boolean = true
    somethingPass(numbers6,greaterThan2)          //> res26: Boolean = false
  somethingPass2(numbers6,greaterThan2)           //> res27: Boolean = false
  
  
  //part 2
  var cs152 = List(List(93.0, 89.0, 90.0), List(75.0, 76.0, 68.0), List(88.0, 82.0, 78.0))
                                                  //> cs152  : List[List[Double]] = List(List(93.0, 89.0, 90.0), List(75.0, 76.0,
                                                  //|  68.0), List(88.0, 82.0, 78.0))
  //1.
	def avg(scores: List[Double]): Double = scores.reduce(_ + _)/scores.length
                                                  //> avg: (scores: List[Double])Double
	
	def avgAvg(scores: List[List[Double]]): List[Double] = {
	var result = List()
	for(list <- scores){ result:+avg(list) }
	result
	}                                         //> avgAvg: (scores: List[List[Double]])List[Double]
	
	def passing(scores: List[List[Double]]) : List[Int] = {
	val avgScores = avgAvg(scores)
	var result = List()
	for(i <- 0 until avgScores.length) {
	if(avgScores(i) >= 70) result :+ i
	}
	result
	}                                         //> passing: (scores: List[List[Double]])List[Int]
	
	def sumSums(scores: List[List[Double]]): Double = scores.map(_ sum).reduce(_ + _)
                                                  //> sumSums: (scores: List[List[Double]])Double

	avg(cs152.head)                           //> res28: Double = 90.66666666666667
	avgAvg(cs152)                             //> res29: List[Double] = List()
	passing(cs152)                            //> res30: List[Int] = List()
	sumSums(cs152)                            //> res31: Double = 739.0
	
	//3.
	
	def spellCheck(doc: List[String], dictionary: List[String]) : List[String] = {
	def isIn(word: String) = dictionary.contains(word)
	
	doc.filter(!isIn(_))
	}                                         //> spellCheck: (doc: List[String], dictionary: List[String])List[String]

  val words = List("Hello", "World", "correc")    //> words  : List[String] = List(Hello, World, correc)
  val dict = List("Hello", "World", "correct")    //> dict  : List[String] = List(Hello, World, correct)
  
  spellCheck(words,dict)                          //> res32: List[String] = List(correc)

//4.
val monomial = List((3.0, 2.0), (-5.0, 0.0))      //> monomial  : List[(Double, Double)] = List((3.0,2.0), (-5.0,0.0))

def evalMono(mono: (Double,Double), x: Double): Double = {
val coef = mono._1
val pow = mono._2
(math.pow(x,pow))*coef
}                                                 //> evalMono: (mono: (Double, Double), x: Double)Double

def evalPoly(poly: List[(Double,Double)], x: Double): Double = {
var result = 0.0
for(dude <- poly){ result += evalMono((dude._1,dude._2), x) }
result
}                                                 //> evalPoly: (poly: List[(Double, Double)], x: Double)Double

evalPoly(monomial,1)                              //> res33: Double = -2.0
evalPoly(monomial,0)                              //> res34: Double = -5.0

}