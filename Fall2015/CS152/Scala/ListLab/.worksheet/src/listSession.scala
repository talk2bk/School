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
  };System.out.println("""sumOddCubes: (list: List[Int])Int""");$skip(37); val res$0 = 
  
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
  sumOddCubes4(List(1,3));System.out.println("""res7: Int = """ + $show(res$7));$skip(197); 
  
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
  };System.out.println("""sumOfSums2: (list: List[List[Int]])Int""");$skip(22); val res$9 = 
  
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
  };System.out.println("""sumOfSums3: (list: List[List[Int]])Int""");$skip(22); val res$10 = 
  
  sumOfSums3(test);System.out.println("""res10: Int = """ + $show(res$10));$skip(58); 
  //mapfilterreduce
  def sum(list: List[Int]) = list.sum;System.out.println("""sum: (list: List[Int])Int""");$skip(72); 
  def sumOfSums4(list: List[List[Int]]) = list.map(sum _).reduce(_ + _);System.out.println("""sumOfSums4: (list: List[List[Int]])Int""");$skip(19); val res$11 = 
  sumOfSums4(test);System.out.println("""res11: Int = """ + $show(res$11));$skip(53); 
  
  //3.
  def depth[T] (list: Array[T]) = {
  
  };System.out.println("""depth: [T](list: Array[T])Unit""");$skip(56); 
  
  //4.
  val numbers = List(0.0,1.0,2.0,3.0,4.0,5.0);System.out.println("""numbers  : List[Double] = """ + $show(numbers ));$skip(125); 
  //iterative
  def average(list: List[Double]) = {
  var result = 0.0
  for(x <- list) result += x
  result/list.length
  };System.out.println("""average: (list: List[Double])Double""");$skip(19); val res$12 = 
  average(numbers);System.out.println("""res12: Double = """ + $show(res$12));$skip(127); 
  //recursive
  def average2(list:List[Double]): Double = {
  if(list == Nil) 0.0 else (list.head + average2(list.tail))/2
  };System.out.println("""average2: (list: List[Double])Double""");$skip(20); val res$13 = 
  average2(numbers);System.out.println("""res13: Double = """ + $show(res$13));$skip(403); 
  //tail recursive
  //mapfilterreduce
  
  //5.
  //iterative
  /*
  def max (list: List[Any]) = {
  var maximum = list.head
  for(i <- 0 to list.length){
  if(list(i) > maximum) maximum = list(i)
  
  }
  maximum
  }
  
  max(numbers)
  */
  
  //6.
  //iterative
  def countPass[T] (list: List[T], pred: T=> Boolean): Int = {
  var count = 0
  for(x <- list){ if (pred(x)) {count += 1 }}
  count
  };System.out.println("""countPass: [T](list: List[T], pred: T => Boolean)Int""");$skip(104); 
  //mapfilterreduce
  
  def countPass2[T] (list: List[T], pred: T=>Boolean) = list.filter(pred).length;System.out.println("""countPass2: [T](list: List[T], pred: T => Boolean)Int""");$skip(74); 
  
    def greaterThan2(num: Int) = {
  if(num > 2) true
  else false
  };System.out.println("""greaterThan2: (num: Int)Boolean""");$skip(51); 
  
  val numbers2 = List(0,-1,2, 1, 2, 3, 4,5,6,3);System.out.println("""numbers2  : List[Int] = """ + $show(numbers2 ));$skip(35); val res$14 = 
  countPass(numbers2,greaterThan2);System.out.println("""res14: Int = """ + $show(res$14));$skip(36); val res$15 = 
  countPass2(numbers2,greaterThan2);System.out.println("""res15: Int = """ + $show(res$15));$skip(169); 
  
  //7.
  //iterative
  def allPass[T] (list: List[T], pred: T=> Boolean): Boolean = {
  var result = true
  for(x <- list) {if(!pred(x)) result = false}
  result
  };System.out.println("""allPass: [T](list: List[T], pred: T => Boolean)Boolean""");$skip(155); 
  
  //mapreducefilter
  def allPass2[T] (list: List[T], pred: T=> Boolean): Boolean = {
  val list2 = list.filter(pred)
  list2.length == list.length
  };System.out.println("""allPass2: [T](list: List[T], pred: T => Boolean)Boolean""");$skip(51); 
  
  val numbers3 = List(0,-1,2, 1, 2, 3, 4,5,6,3);System.out.println("""numbers3  : List[Int] = """ + $show(numbers3 ));$skip(35); 
  val numbers4 = List( 3, 4,5,6,3);System.out.println("""numbers4  : List[Int] = """ + $show(numbers4 ));$skip(33); val res$16 = 
  allPass(numbers3,greaterThan2);System.out.println("""res16: Boolean = """ + $show(res$16));$skip(34); val res$17 = 
  allPass2(numbers3,greaterThan2);System.out.println("""res17: Boolean = """ + $show(res$17));$skip(35); val res$18 = 
    allPass(numbers4,greaterThan2);System.out.println("""res18: Boolean = """ + $show(res$18));$skip(34); val res$19 = 
  allPass2(numbers4,greaterThan2);System.out.println("""res19: Boolean = """ + $show(res$19));$skip(168); 
  //8.
  //iterative
  def somethingPass[T] (list: List[T], pred: T=>Boolean): Boolean = {
  var result = false
  for(x <- list) {if(pred(x)) return true}
  result
  };System.out.println("""somethingPass: [T](list: List[T], pred: T => Boolean)Boolean""");$skip(118); 
  //mapreducefilter
  def somethingPass2[T] (list: List[T], pred: T=>Boolean): Boolean = list.filter(pred).length > 0;System.out.println("""somethingPass2: [T](list: List[T], pred: T => Boolean)Boolean""");$skip(53); 
  
    val numbers5 = List(0,-1,2, 1, 2, 3, 4,5,6,3);System.out.println("""numbers5  : List[Int] = """ + $show(numbers5 ));$skip(35); 
  val numbers6 = List( 0,-1,2,1,2);System.out.println("""numbers6  : List[Int] = """ + $show(numbers6 ));$skip(39); val res$20 = 
  somethingPass(numbers5,greaterThan2);System.out.println("""res20: Boolean = """ + $show(res$20));$skip(40); val res$21 = 
  somethingPass2(numbers5,greaterThan2);System.out.println("""res21: Boolean = """ + $show(res$21));$skip(41); val res$22 = 
    somethingPass(numbers6,greaterThan2);System.out.println("""res22: Boolean = """ + $show(res$22));$skip(40); val res$23 = 
  somethingPass2(numbers6,greaterThan2);System.out.println("""res23: Boolean = """ + $show(res$23));$skip(76); 
  
  //9.
  //iterative
  def reverseList[T] (list: List[T]) = list.reverse;System.out.println("""reverseList: [T](list: List[T])List[T]""");$skip(131); 
  //recursive
  def reverseList2[T] (list: List[T]): List[T]  = if(list == Nil) list else reverseList2(list.tail) ::: list.take(1);System.out.println("""reverseList2: [T](list: List[T])List[T]""");$skip(27); val res$24 = 
  
  reverseList(numbers6);System.out.println("""res24: List[Int] = """ + $show(res$24));$skip(25); val res$25 = 
  reverseList2(numbers6);System.out.println("""res25: List[Int] = """ + $show(res$25));$skip(232); 
  
  //10.
  
  
  //part 2
  def spellCheck(doc: List[String], dictionary: List[String]): List[String] = {
  val badWords = spellCheck(doc.tail, dictionary)
  if(dictionary.contains(doc.head)) badWords else doc.head::badWords
  };System.out.println("""spellCheck: (doc: List[String], dictionary: List[String])List[String]""")}
  
}
