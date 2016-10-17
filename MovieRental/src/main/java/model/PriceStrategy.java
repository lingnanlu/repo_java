package model;

/**
 * Created by rico on 2016/10/14.
 */
public abstract class PriceStrategy {
    abstract int getPriceCode();

   /* public double getCharge(int daysRented) {
        double result = 0;

        //不同的影片类型有不同的计费方式
        //也就是说， 对于每种子类， 有不同的行为， 所以想到多态
        //使用Replace Conditional with Polymorphism
        switch (getPriceStrategyCode()) {
            case Movie.REGULAR:
                result += 2;
                if (daysRented > 2) {
                    result += (daysRented - 2) * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += daysRented * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if (daysRented > 3) {
                    result += (daysRented - 3) * 1.5;
                }
                break;
        }
        return result;
    }*/

    abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
