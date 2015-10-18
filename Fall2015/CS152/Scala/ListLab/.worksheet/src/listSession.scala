object listSession {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(64); 
  println("Welcome to the Scala worksheet");$skip(41); 
  
  //1.
  def isOdd(x: Int) = x%2 != 0;System.out.println("""isOdd: (x: Int)Boolean""");$skip(31); 
  def cube(x: Int) = x * x * x;System.out.println("""cube: (x: Int)Int""");$skip(133); 
  //iterative
  def sumOddCubes(list: List[Int]) = {
  var result = 0
  for(x <- list){ if(isOdd(x))result += cube(x) }
  result
  };System.out.println("""sumOddCubes: (list: List[Int])Int""");$skip(38); val res$0 = 
  
  sumOddCubes(List(2,0, 3, 4, 5));System.out.println("""res0: Int = """ + $show(res$0));$skip(25); val res$1 = 
  sumOddCubes(List(1,3));System.out.println("""res1: Int = """ + $show(res$1));$skip(203); 
  
 	//recursive
 	def sumOddCubes2(nums: List[Int]): Int = {
 	if(nums == Nil) 0 else {
 	val butFirst = sumOddCubes2(nums.tail)
 	if(isOdd(nums.head)) cube(nums.head) + butFirst else butFirst
 	}
 	};System.out.println("""sumOddCubes2: (nums: List[Int])Int""");$skip(35); val res$2 = 
 	sumOddCubes2(List(2,0, 3, 4, 5));System.out.println("""res2: Int = """ + $show(res$2));$skip(26); val res$3 = 
  sumOddCubes2(List(1,3));System.out.println("""res3: Int = """ + $show(res$3));$skip(290); 
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
 	};System.out.println("""sumOddCubes3: (nums: List[Int])Int""");$skip(35); val res$4 = 
 	sumOddCubes3(List(2,0, 3, 4, 5));System.out.println("""res4: Int = """ + $show(res$4));$skip(26); val res$5 = 
  sumOddCubes3(List(1,3));System.out.println("""res5: Int = """ + $show(res$5));$skip(101); 
 	//mapfilterreduce
 	def sumOddCubes4(nums: List[Int]) = nums.filter(isOdd).map(cube).reduce(_ + _);System.out.println("""sumOddCubes4: (nums: List[Int])Int""");$skip(35); val res$6 = 
  sumOddCubes4(List(2,0, 3, 4, 5));System.out.println("""res6: Int = """ + $show(res$6));$skip(26); val res$7 = 
  sumOddCubes4(List(1,3));System.out.println("""res7: Int = """ + $show(res$7));$skip(199); 
  
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
 	};System.out.println("""sumOfSums: (list: List[List[Int]])Int""");$skip(55); 
 	val test = List(List(1,2,3),List(4,5,6),List(7,8,9));System.out.println("""test  : List[List[Int]] = """ + $show(test ));$skip(18); val res$8 = 
 	sumOfSums(test);System.out.println("""res8: Int = """ + $show(res$8));$skip(136); 
 	
  //recursive
  def sumOfSums2(list: List[List[Int]]): Int = {
  if(list == Nil) 0
  else list.head.sum + sumOfSums2(list.tail)
  };System.out.println("""sumOfSums2: (list: List[List[Int]])Int""");$skip(23); val res$9 = 
  
  sumOfSums2(test);System.out.println("""res9: Int = """ + $show(res$9));$skip(236); 
  
  //tailrecursive
  def sumOfSums3(list: List[List[Int]]) = {
  def helper(result: Int, unseen: List[List[Int]]): Int = {
  if(unseen == Nil) result
  else{
  helper(unseen.head.sum+result,unseen.tail)
  }
  }
 	helper(0, list)
  };System.out.println("""sumOfSums3: (list: List[List[Int]])Int""");$skip(23); val res$10 = 
  
  sumOfSums3(test);System.out.println("""res10: Int = """ + $show(res$10));$skip(58); 
  //mapfilterreduce
  def sum(list: List[Int]) = list.sum;System.out.println("""sum: (list: List[Int])Int""");$skip(72); 
  def sumOfSums4(list: List[List[Int]]) = list.map(sum _).reduce(_ + _);System.out.println("""sumOfSums4: (list: List[List[Int]])Int""");$skip(19); val res$11 = 
  sumOfSums4(test);System.out.println("""res11: Int = """ + $show(res$11));$skip(308); 
  
  //3.
  //recursive
  //depth = max(depth(head) +1, depth (tail))
  def depth (list: List[Any]):Int = {
  if(list.asInstanceOf[List[Any]].size == 0) 1
  else if(list.head.isInstanceOf[List[Any]]){
  math.max(depth(list.head.asInstanceOf[List[Any]]) + 1, depth(list.tail))
  } else{depth(list.tail)}
  };System.out.println("""depth: (list: List[Any])Int""");$skip(75); 
  //tailrecursive
  def isList(thing: Any) = thing.isInstanceOf[List[Any]];System.out.println("""isList: (thing: Any)Boolean""");$skip(310); 
  def depth2(list: List[Any]) = {
  def helper(list2: List[Any], count: Int): Int = {
  if(list2 == Nil) count
  else if (list2.head.isInstanceOf[List[Any]]){
  math.max(helper(list2.head.asInstanceOf[List[Any]],count +1), helper(list2.tail,count))
  } else{helper(list2.tail, count)}
  }
  helper(list,1)
  };System.out.println("""depth2: (list: List[Any])Int""");$skip(66); val res$12 = 
  
    //should return 4
  depth(List(List(List(1, 2, List(3)))));System.out.println("""res12: Int = """ + $show(res$12));$skip(42); val res$13 = 
  depth2(List(List(List(1, 2, List(3)))));System.out.println("""res13: Int = """ + $show(res$13));$skip(56); 
  
  //4.
  val numbers = List(0.0,1.0,2.0,3.0,4.0,5.0);System.out.println("""numbers  : List[Double] = """ + $show(numbers ));$skip(125); 
  //iterative
  def average(list: List[Double]) = {
  var result = 0.0
  for(x <- list) result += x
  result/list.length
  };System.out.println("""average: (list: List[Double])Double""");$skip(96); 
  //mapfilterreduce
  def average2(list: List[Double]) = {
  list.reduce(_ + _)/list.length
  };System.out.println("""average2: (list: List[Double])Double""");$skip(20); val res$14 = 
   average(numbers);System.out.println("""res14: Double = """ + $show(res$14));$skip(21); val res$15 = 
   average2(numbers);System.out.println("""res15: Double = """ + $show(res$15));$skip(187); 
   
  //5.
  //iterative
  def max(list: List[Any]) = {
  var maximum = list.head
  for(i <- list){if( i.asInstanceOf[Comparable[Any]].compareTo(maximum) > 0) maximum = i }
  maximum
  };System.out.println("""max: (list: List[Any])Any""");$skip(15); val res$16 = 
  max(numbers);System.out.println("""res16: Any = """ + $show(res$16));$skip(333); 
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
  };System.out.println("""max2: (list: List[Any])Any""");$skip(19); val res$17 = 
  
  max2(numbers);System.out.println("""res17: Any = """ + $show(res$17));$skip(162); 
 
 
  //6.
  //iterative
  def countPass[T] (list: List[T], pred: T=> Boolean): Int = {
  var count = 0
  for(x <- list){ if (pred(x)) {count += 1 }}
  count
  };System.out.println("""countPass: [T](list: List[T], pred: T => Boolean)Int""");$skip(105); 
  //mapfilterreduce
  
  def countPass2[T] (list: List[T], pred: T=>Boolean) = list.filter(pred).length;System.out.println("""countPass2: [T](list: List[T], pred: T => Boolean)Int""");$skip(75); 
  
    def greaterThan2(num: Int) = {
  if(num > 2) true
  else false
  };System.out.println("""greaterThan2: (num: Int)Boolean""");$skip(52); 
  
  val numbers2 = List(0,-1,2, 1, 2, 3, 4,5,6,3);System.out.println("""numbers2  : List[Int] = """ + $show(numbers2 ));$skip(35); val res$18 = 
  countPass(numbers2,greaterThan2);System.out.println("""res18: Int = """ + $show(res$18));$skip(36); val res$19 = 
  countPass2(numbers2,greaterThan2);System.out.println("""res19: Int = """ + $show(res$19));$skip(170); 
  
  //7.
  //iterative
  def allPass[T] (list: List[T], pred: T=> Boolean): Boolean = {
  var result = true
  for(x <- list) {if(!pred(x)) result = false}
  result
  };System.out.println("""allPass: [T](list: List[T], pred: T => Boolean)Boolean""");$skip(156); 
  
  //mapreducefilter
  def allPass2[T] (list: List[T], pred: T=> Boolean): Boolean = {
  val list2 = list.filter(pred)
  list2.length == list.length
  };System.out.println("""allPass2: [T](list: List[T], pred: T => Boolean)Boolean""");$skip(52); 
  
  val numbers3 = List(0,-1,2, 1, 2, 3, 4,5,6,3);System.out.println("""numbers3  : List[Int] = """ + $show(numbers3 ));$skip(35); 
  val numbers4 = List( 3, 4,5,6,3);System.out.println("""numbers4  : List[Int] = """ + $show(numbers4 ));$skip(33); val res$20 = 
  allPass(numbers3,greaterThan2);System.out.println("""res20: Boolean = """ + $show(res$20));$skip(34); val res$21 = 
  allPass2(numbers3,greaterThan2);System.out.println("""res21: Boolean = """ + $show(res$21));$skip(35); val res$22 = 
    allPass(numbers4,greaterThan2);System.out.println("""res22: Boolean = """ + $show(res$22));$skip(34); val res$23 = 
  allPass2(numbers4,greaterThan2);System.out.println("""res23: Boolean = """ + $show(res$23));$skip(168); 
  //8.
  //iterative
  def somethingPass[T] (list: List[T], pred: T=>Boolean): Boolean = {
  var result = false
  for(x <- list) {if(pred(x)) return true}
  result
  };System.out.println("""somethingPass: [T](list: List[T], pred: T => Boolean)Boolean""");$skip(118); 
  //mapreducefilter
  def somethingPass2[T] (list: List[T], pred: T=>Boolean): Boolean = list.filter(pred).length > 0;System.out.println("""somethingPass2: [T](list: List[T], pred: T => Boolean)Boolean""");$skip(54); 
  
    val numbers5 = List(0,-1,2, 1, 2, 3, 4,5,6,3);System.out.println("""numbers5  : List[Int] = """ + $show(numbers5 ));$skip(35); 
  val numbers6 = List( 0,-1,2,1,2);System.out.println("""numbers6  : List[Int] = """ + $show(numbers6 ));$skip(39); val res$24 = 
  somethingPass(numbers5,greaterThan2);System.out.println("""res24: Boolean = """ + $show(res$24));$skip(40); val res$25 = 
  somethingPass2(numbers5,greaterThan2);System.out.println("""res25: Boolean = """ + $show(res$25));$skip(41); val res$26 = 
    somethingPass(numbers6,greaterThan2);System.out.println("""res26: Boolean = """ + $show(res$26));$skip(40); val res$27 = 
  somethingPass2(numbers6,greaterThan2);System.out.println("""res27: Boolean = """ + $show(res$27));$skip(108); 
  
  
  //part 2
  var cs152 = List(List(93.0, 89.0, 90.0), List(75.0, 76.0, 68.0), List(88.0, 82.0, 78.0));System.out.println("""cs152  : List[List[Double]] = """ + $show(cs152 ));$skip(83); 
  //1.
	def avg(scores: List[Double]): Double = scores.reduce(_ + _)/scores.length;System.out.println("""avg: (scores: List[Double])Double""");$skip(134); 
	
	def avgAvg(scores: List[List[Double]]): List[Double] = {
	var result = List()
	for(list <- scores){ result:+avg(list) }
	result
	};System.out.println("""avgAvg: (scores: List[List[Double]])List[Double]""");$skip(200); 
	
	def passing(scores: List[List[Double]]) : List[Int] = {
	val avgScores = avgAvg(scores)
	var result = List()
	for(i <- 0 until avgScores.length) {
	if(avgScores(i) >= 70) result :+ i
	}
	result
	};System.out.println("""passing: (scores: List[List[Double]])List[Int]""");$skip(85); 
	
	def sumSums(scores: List[List[Double]]): Double = scores.map(_ sum).reduce(_ + _);System.out.println("""sumSums: (scores: List[List[Double]])Double""");$skip(18); val res$28 = 

	avg(cs152.head);System.out.println("""res28: Double = """ + $show(res$28));$skip(15); val res$29 = 
	avgAvg(cs152);System.out.println("""res29: List[Double] = """ + $show(res$29));$skip(16); val res$30 = 
	passing(cs152);System.out.println("""res30: List[Int] = """ + $show(res$30));$skip(16); val res$31 = 
	sumSums(cs152);System.out.println("""res31: Double = """ + $show(res$31));$skip(169); 
	
	//3.
	
	def spellCheck(doc: List[String], dictionary: List[String]) : List[String] = {
	def isIn(word: String) = dictionary.contains(word)
	
	doc.filter(!isIn(_))
	};System.out.println("""spellCheck: (doc: List[String], dictionary: List[String])List[String]""");$skip(48); 

  val words = List("Hello", "World", "correc");System.out.println("""words  : List[String] = """ + $show(words ));$skip(47); 
  val dict = List("Hello", "World", "correct");System.out.println("""dict  : List[String] = """ + $show(dict ));$skip(28); val res$32 = 
  
  spellCheck(words,dict);System.out.println("""res32: List[String] = """ + $show(res$32));$skip(51); 

//4.
val monomial = List((3.0, 2.0), (-5.0, 0.0));System.out.println("""monomial  : List[(Double, Double)] = """ + $show(monomial ));$skip(122); 

def evalMono(mono: (Double,Double), x: Double): Double = {
val coef = mono._1
val pow = mono._2
(math.pow(x,pow))*coef
};System.out.println("""evalMono: (mono: (Double, Double), x: Double)Double""");$skip(154); 

def evalPoly(poly: List[(Double,Double)], x: Double): Double = {
var result = 0.0
for(dude <- poly){ result += evalMono((dude._1,dude._2), x) }
result
};System.out.println("""evalPoly: (poly: List[(Double, Double)], x: Double)Double""");$skip(22); val res$33 = 

evalPoly(monomial,1);System.out.println("""res33: Double = """ + $show(res$33));$skip(21); val res$34 = 
evalPoly(monomial,0);System.out.println("""res34: Double = """ + $show(res$34))}

}
