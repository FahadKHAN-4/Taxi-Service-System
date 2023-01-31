package taxi.models;

import taxi.models.taxitype.TaxiType;
import taxi.models.user.Customer;

public class Receipt {
  private double price, distance, time;

  public Receipt(double price, double distance, double time) {
    this.price = price;
    this.distance = distance;
    this.time = time;
  }

  public double getPrice() {
    return price;
  }

  public double getDistance() {
    return distance;
  }

  public double getTime() {
    return time;
  }

  @Override
  public String toString() {
    return "Thank you for travelling with us! \r\n"
        + String.format("Distance: %.2f / Price: %.2f / Time: %.2f", distance, price, time);
  }

  // code for computing the price and time based on the distance

  public static Receipt generateReceipt(double distance, TaxiType type, Customer customer) {
    double price = type.getChargeFee() * distance;
    price = Receipt.checkDiscount(customer, price);
    double time = distance / type.getCarSpeed();
    customer.payTheBill(price);
    return new Receipt(price, distance, time);
  }

  public static Receipt generateShareReceipt(double distance, TaxiType type, Customer customer1, Customer customer2) {
    double price = type.getChargeFee() * distance / 2;
    double time = distance / type.getCarSpeed();
    customer1.payTheBill(price);
    customer2.payTheBill(price);
    return new Receipt(price, distance, time);
  }

  public static Receipt generateShareReceipt(double accumulatedDistance, double distance, TaxiType type,
      Customer customer) {
    double price = type.getChargeFee() * distance * distance / accumulatedDistance;
    double time = accumulatedDistance / type.getCarSpeed();
    customer.payTheBill(price);
    return new Receipt(price, distance, time);
  }

  private static double checkDiscount(Customer customer, double price) {
    String userType = customer.getUserTypeString();
    switch (userType) {
      case "premium":
        return price * 0.8;
      case "vip":
        return price * 0.85;
      case "top":
        return price * 0.9;
      default:
        return price;
    }
  }
}
