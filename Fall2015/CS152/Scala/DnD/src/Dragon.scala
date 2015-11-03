

/**
 * @author CuTs
 */
class Dragon (var name: String, var health: Int = 100) {
 def attack (target: Knight, damage: Int) = { 
   println(name + " is flaming " + target.name) 
   target.health -=damage
    }
}