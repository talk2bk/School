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
