
package adapterPattern

trait IThermometer {
  // = avg degrees Farenheit
  def getMeanTemperature(cities: List[String]): Double
}

class CenTherm {
// = degrees Centigrade
def computeTemp(city: String): Double = { city.length() }
}


class Thermometer(val cen: CenTherm) extends IThermometer{
def getMeanTemperature(cities: List[String]): Double = { 
var total = 0.0
for(place <- cities){ total += cen.computeTemp(place) }
total/cities.length
}

}

object CenTherm extends App {
val therm = new Thermometer(new CenTherm)
println(therm.getMeanTemperature(List("Milpitas", "San Jose")))
}
