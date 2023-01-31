package testTaxi.models;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import taxi.exceptions.IncorrectRatingMarkException;
import taxi.models.Rating;

public class TestRating {
  @Test
  public void testRatingA() throws IncorrectRatingMarkException {
    Rating r = new Rating();
    r.updateRating(4);
    assertEquals(r.getAverageRating(), 4);
  }

  @Test
  public void testRatingB() throws IncorrectRatingMarkException {
    Rating r = new Rating(4);
    r.updateRating(3);
    assertEquals(r.getAverageRating(), 3);
  }

  @Test
  public void testRatingC() throws IncorrectRatingMarkException {
    Rating r = new Rating(4, 100);
    r.updateRating(3);
    assertEquals(r.getAverageRating(), 3.99, 0.1);
  }

  @Test
  public void testRatingD() throws IncorrectRatingMarkException {
    Rating r = new Rating(5, 5);
    r.updateRating(1);
    assertEquals(r.getAverageRating(), 4.33, 0.1);
  }

  @Test
  public void testRatingE() throws IncorrectRatingMarkException {
    Rating r = new Rating();
    assertThrows(IncorrectRatingMarkException.class, () -> r.updateRating(-1));
  }

  @Test
  public void testRatingF() throws IncorrectRatingMarkException {
    Rating r = new Rating();
    assertThrows(IncorrectRatingMarkException.class, () -> r.updateRating(6));
  }
}
