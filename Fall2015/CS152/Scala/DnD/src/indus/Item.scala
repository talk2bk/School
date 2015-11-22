

package indus

class Item( private var description: Description) {
private val id: Int = Item.nextId
override def toString = "(id: " + id +", " + "Description: " +description +")" 

}

object Item{
private var  currentId = 0
def  description = ""
def apply(description: Description) = {
new Item(description)
}
def nextId = {currentId += 1; currentId}


}