object midtermSession {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
 //2.
 def approxZero(num: Any): Boolean = num.asInstanceOf[Comparable[Any]].compareTo(0.1) < 0 && num.asInstanceOf[Comparable[Any]].compareTo(-0.1) > 0
                                                  //> approxZero: (num: Any)Boolean
 def self(num: Double): Double = num              //> self: (num: Double)Double
 
def countRoots[T](f: T => Double, inputs: List[T]) = inputs.map(f).filter(approxZero _).length
                                                  //> countRoots: [T](f: T => Double, inputs: List[T])Int
val numbers = List(0.01, 0.1, 1, 0.001, -0.001, -1)
                                                  //> numbers  : List[Double] = List(0.01, 0.1, 1.0, 0.001, -0.001, -1.0)
val numbers2 = List(0.001, 0.00001, 0.0001, 0.01) //> numbers2  : List[Double] = List(0.001, 1.0E-5, 1.0E-4, 0.01)
val numbers3 = List(1.0,2,3,4,5)                  //> numbers3  : List[Double] = List(1.0, 2.0, 3.0, 4.0, 5.0)
countRoots(self, numbers)                         //> res0: Int = 3
countRoots(self, numbers2)                        //> res1: Int = 4
countRoots(self, numbers3)                        //> res2: Int = 0

//3.
def fact(n: Int): Int = if(n ==0) 1 else n*fact(n-1)
                                                  //> fact: (n: Int)Int

def recur(base: Int, combiner: (Int, Int) => Int): Int => Int = {
def func(n : Int): Int = { if(n == 0) base else combiner(n, func(n-1)) }
func _
}                                                 //> recur: (base: Int, combiner: (Int, Int) => Int)Int => Int

def mult(first: Int, second: Int) = first*second  //> mult: (first: Int, second: Int)Int
val recurFact = recur(1, mult)                    //> recurFact  : Int => Int = <function1>

recurFact(1)                                      //> res3: Int = 1
recurFact(2)                                      //> res4: Int = 2
recurFact(3)                                      //> res5: Int = 6
recurFact(4)                                      //> res6: Int = 24

//4.
//a.
def some(vals: List[Int], test: Int => Boolean): Boolean = {
def helper(vals2: List[Int], test2: Int =>Boolean, pass: Boolean): Boolean = {
if(vals2 == Nil) pass else if(pass) true else helper(vals2.tail, test2, test2(vals2.head))}
helper(vals,test,false)
}                                                 //> some: (vals: List[Int], test: Int => Boolean)Boolean

some(List(1, 3, 5, 7), (n: Int)=> n % 2 == 0)     //> res7: Boolean = false
some(List(1, 3, 5, 8), (n: Int)=> n % 2 == 0)     //> res8: Boolean = true

//b.
def some2(vals:List[Int], test: Int => Boolean): Boolean = vals.filter(test).length > 0
                                                  //> some2: (vals: List[Int], test: Int => Boolean)Boolean
some2(List(1, 3, 5, 7), (n: Int)=> n % 2 == 0)    //> res9: Boolean = false
some2(List(1, 3, 5, 8), (n: Int)=> n % 2 == 0)    //> res10: Boolean = true

}