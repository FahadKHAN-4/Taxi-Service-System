package taxi.models.user;

import taxi.exceptions.IncorrectRatingMarkException;
import taxi.models.Rating;

public class User {
  private String name;
  private int location;
  private Rating rating;
  private UserType userType;

  protected User(String name, int location) {
    this.name = name;
    this.location = location;
    this.rating = new Rating();
    this.userType = new UserType();
    this.userType.updateUserType(rating.getAverageRating());
  }

  protected User(String name, int location, Rating rating) {
    this.name = name;
    this.location = location;
    this.rating = rating;
    this.userType = new UserType();
    this.userType.updateUserType(rating.getAverageRating());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // public double getRating() {
  // return rating;
  // }

  // public void setRating(double rating) {
  // this.rating = rating;
  // }

  public int getLocation() {
    return location;
  }

  public void setLocation(int location) {
    this.location = location;
  }

  public void rateUser(int mark, User user) throws IncorrectRatingMarkException {
    rating.updateRating(mark);
    // user.rating.updateRating(mark);
    user.updateUserUserType(user.getRating());
    userType.updateUserType(user.getRating());
  }

  private void updateUserUserType(double rating) {
    userType.updateUserType(rating);
  }

  public double getRating() {
    return rating.getAverageRating();
  }

  public String getUserTypeString() {
    return userType.getUserType();
  }
}
