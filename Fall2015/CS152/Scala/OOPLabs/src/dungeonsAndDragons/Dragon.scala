package dungeonsAndDragons

class Dragon (val name: String, var health: Int = 100) {
 def attack (target: Knight, damage: Int) = { 
   println(name + " is flaming " + target.name) 
   target.health -=damage
    }
}
object Dragon {
 def apply( name: String) = {
 new Dragon(name)
 } 
}