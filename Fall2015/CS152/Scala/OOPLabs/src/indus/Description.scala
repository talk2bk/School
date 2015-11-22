

package indus/**
 * @author CuTs
 */
class Description(private var description: String, private var price: Double, private var supplier: String) {
  
override def toString = "(Description: " + description + ", "+"price: " + price +", "+ "supplier: " + supplier + ")"
}

object Description {
  
def apply (description: String, price: Double, supplier: String) =
  new Description(description,price,supplier)
}