
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

/*
trait IThermometer {
  // = avg degrees Farenheit
  def getMeanTemperature(cities: List[String]): Double
}

class CenTherm extends IThermometer{
// = degrees Centigrade
  val cityMap = Map("Milpitas"->10.0, "San Jose" -> 15.0)
  
  def computeTemp(city: String) = { 
    if(cityMap.contains(city)){ cityMap.apply(city) }
      }
  def getMeanTemperature(cities: List[String]): Double = {
    var mean = 0.0
    for(place <- cities){ mean += computeTemp(place).asInstanceOf[Double] }
    mean/cities.length
  }
}

object CenTherm extends App{
val thermometer = new CenTherm with IThermometer

println(thermometer.getMeanTemperature(List("Milpitas", "San Jose")))
}
*/