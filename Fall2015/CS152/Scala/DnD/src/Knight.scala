

/**
 * @author CuTs
 */
class Knight (var name: String, var health: Int = 100){
  def attack (target: Dragon, damage: Int) = { 
    println(name + " is stabbing " + target.name)
    target.health -=damage
    }
}