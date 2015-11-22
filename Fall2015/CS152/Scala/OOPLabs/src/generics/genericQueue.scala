package generics

import scala.collection.mutable.ArrayBuffer

/**
 * @author CuTs
 */
class genericQueue[T] (queueBody: ArrayBuffer[T] = new ArrayBuffer[T]){

def enqueue (item: T) =  {queueBody += item}
def dequeue =  {queueBody.remove(0)}
def isEmpty = {queueBody.isEmpty}
override def toString = {queueBody.toString()}
  
}

object genericQueue{
  def apply[T] = { new genericQueue[T] }
}

object test extends App {
   
  val waitingList =  genericQueue[String]
  waitingList.enqueue("April")
  waitingList.enqueue("Broseidon")
  waitingList.enqueue("Captain Planet")
  waitingList.enqueue("Daniel Avidan")
  waitingList.enqueue("Estevan Emmanuel Esperanza")
  println(waitingList.toString)
  
  while(!waitingList.isEmpty){
    waitingList.dequeue
    println(waitingList.toString)
  }
  
}