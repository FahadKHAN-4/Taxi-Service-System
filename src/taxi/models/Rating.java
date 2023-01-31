package taxi.models;

import taxi.exceptions.IncorrectRatingMarkException;

public class Rating {
    private double averageRating;
    private int rateCounter;

    public Rating() {
        averageRating = 5.0;
        rateCounter = 0;
    }

    public Rating(double rating) {
        averageRating = rating;
        rateCounter = 0;
    }

    public Rating(double averageRating, int rateCounter) {
        this.averageRating = averageRating;
        this.rateCounter = rateCounter;
    }

    public void updateRating(int mark) throws IncorrectRatingMarkException {
        if (mark < 0 || mark > 5)
            throw new IncorrectRatingMarkException("The input mark is incorrect. Rating must be between 1 and 5!");
        averageRating = (averageRating * rateCounter + Double.valueOf(mark)) / (++rateCounter);
    }

    public double getAverageRating() {
        return averageRating;
    }
}
