package taxi.models;

public class Road {
  private double traffic, distance;
  public Road(double distance, double traffic) {
    this.distance = distance;
    this.traffic = traffic;
  }
  
  public Road(double distance) {
	  this.distance = distance;
  }
  public double getTraffic() {
    return traffic;
  }
  public double getDistance() {
    return distance;
  }
}
