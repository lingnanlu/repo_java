/**
 * Created by rico on 2016/10/14.
 */
public class Rental {
    Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double getCharge() {
        return movie.getCharge(daysRented);
    }
    public Movie getMovie() {
        return movie;
    }
    public int getDaysRented() {
        return daysRented;
    }
    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(daysRented);
    }
}
