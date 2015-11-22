package dungeonsAndDragons


object Dungeon extends App {
  val random = new scala.util.Random(System.nanoTime())
  val dragon = Dragon("hotsman")
  val knight =  Knight("slashlord")

  while(dragon.health > 0 && knight.health > 0){
    dragon.attack(knight, random.nextInt(knight.health)+1)
    knight.attack(dragon, random.nextInt(dragon.health)+1)
    }
   println("Dragon: " + dragon.health)
   println("Knight: " + knight.health)
}