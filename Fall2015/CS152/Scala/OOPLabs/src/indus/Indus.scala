package indus

import scala.collection.mutable.ArrayBuffer

object Indus extends App{
var inventory = new ArrayBuffer[Item]
for(i <- 0 until 5){inventory.append(new Item(new Description("The Matrix DVD", 15.50, "DVD World")))}
for(i <- 0 until 3){inventory.append(new Item(new Description("The Terminator DVD", 13.25, "DVD World")))}
for(i <- 0 until 2) {inventory.append(new Item(new Description("Ironman DVD", 18.00, "DVD Planet")))}

for(thing <- inventory){
  println(thing.toString)
}
}