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
  def depth[T] (list: Array[T]) = {
  
  }                                               //> depth: [T](list: Array[T])Unit
  
  //4.
  val numbers = List(0.0,1.0,2.0,3.0,4.0,5.0)     //> numbers  : List[Double] = List(0.0, 1.0, 2.0, 3.0, 4.0, 5.0)
  //iterative
  def average(list: List[Double]) = {
  var result = 0.0
  for(x <- list) result += x
  result/list.length
  }                                               //> average: (list: List[Double])Double
  average(numbers)                                //> res12: Double = 2.5
  //recursive
  def average2(list:List[Double]): Double = {
  if(list == Nil) 0.0 else (list.head + average2(list.tail))/2
  }                                               //> average2: (list: List[Double])Double
  average2(numbers)                               //> res13: Double = 0.890625
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
  }                                               //> countPass: [T](list: List[T], pred: T => Boolean)Int
  //mapfilterreduce
  
  def countPass2[T] (list: List[T], pred: T=>Boolean) = list.filter(pred).length
                                                  //> countPass2: [T](list: List[T], pred: T => Boolean)Int
  
    def greaterThan2(num: Int) = {
  if(num > 2) true
  else false
  }                                               //> greaterThan2: (num: Int)Boolean
  
  val numbers2 = List(0,-1,2, 1, 2, 3, 4,5,6,3)   //> numbers2  : List[Int] = List(0, -1, 2, 1, 2, 3, 4, 5, 6, 3)
  countPass(numbers2,greaterThan2)                //> res14: Int = 5
  countPass2(numbers2,greaterThan2)               //> res15: Int = 5
  
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
  allPass(numbers3,greaterThan2)                  //> res16: Boolean = false
  allPass2(numbers3,greaterThan2)                 //> res17: Boolean = false
    allPass(numbers4,greaterThan2)                //> res18: Boolean = true
  allPass2(numbers4,greaterThan2)                 //> res19: Boolean = true
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
  somethingPass(numbers5,greaterThan2)            //> res20: Boolean = true
  somethingPass2(numbers5,greaterThan2)           //> res21: Boolean = true
    somethingPass(numbers6,greaterThan2)          //> res22: Boolean = false
  somethingPass2(numbers6,greaterThan2)           //> res23: Boolean = false
  
  //9.
  //iterative
  def reverseList[T] (list: List[T]) = list.reverse
                                                  //> reverseList: [T](list: List[T])List[T]
  //recursive
  def reverseList2[T] (list: List[T]): List[T]  = if(list == Nil) list else reverseList2(list.tail) ::: list.take(1)
                                                  //> reverseList2: [T](list: List[T])List[T]
  
  reverseList(numbers6)                           //> res24: List[Int] = List(2, 1, 2, -1, 0)
  reverseList2(numbers6)                          //> res25: List[Int] = List(2, 1, 2, -1, 0)
  
  //10.
  
  
  //part 2
  def spellCheck(doc: List[String], dictionary: List[String]): List[String] = {
  val badWords = spellCheck(doc.tail, dictionary)
  if(dictionary.contains(doc.head)) badWords else doc.head::badWords
  }                                               //> spellCheck: (doc: List[String], dictionary: List[String])List[String]
  
}