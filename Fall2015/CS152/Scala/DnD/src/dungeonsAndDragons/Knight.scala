

package dungeonsAndDragons

class Knight (val name: String, var health: Int = 100){
  def attack (target: Dragon, damage: Int) = { 
    println(name + " is stabbing " + target.name)
    target.health -=damage
    }
}

object Knight {
  def apply(name:String) = { new Knight (name)}
  
}