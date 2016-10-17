package model;

/**
 * Created by rico on 2016/10/14.
 */
public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String title;
    private PriceStrategy priceStrategyCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceStrategyCode(priceCode);
    }


    public String getTitle() {
        return title;
    }

    public int getPriceStrategyCode() {
        return priceStrategyCode.getPriceCode();
    }

    public void setPriceStrategyCode(int arg) {
        switch (arg) {
            case REGULAR:
                priceStrategyCode = new RegularPrice();
                break;
            case CHILDRENS:
                priceStrategyCode = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                priceStrategyCode = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect price code");
        }
    }

    public double getCharge(int daysRented) {
        return priceStrategyCode.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {

        if((getPriceStrategyCode() == Movie.NEW_RELEASE) && daysRented > 1)
            return 2;
        else
            return 1;
    }
}
