import model.Customer;
import model.Movie;
import model.Rental;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rico on 2016/10/14.
 */
public class CustomerTest {

    @Test
    public void statement() throws Exception {

        Customer ling = new Customer("ling");
        Customer.addRentalHelper(ling,
                new Rental(new Movie("hello world", Movie.CHILDRENS), 5),
                new Rental(new Movie("It's Easy", Movie.NEW_RELEASE), 3),
                new Rental(new Movie("We are the Champion", Movie.REGULAR), 7));
        String expectedLing = "model.Rental Record for ling" + "\n"
                + "\t" + "hello world" + "\t" + "4.5" + "\n"
                + "\t" + "It's Easy" + "\t" + "9.0" + "\n"
                + "\t" + "We are the Champion" + "\t" + "9.5" + "\n"
                + "Amount owed is 23.0" + "\n"
                + "You earned 4 frequent renter points";
        assertEquals(ling.statement(), expectedLing);

        Customer nan = new Customer("nan");
        Customer.addRentalHelper(nan,
                new Rental(new Movie("hello world", Movie.CHILDRENS), 3),
                new Rental(new Movie("It's Easy", Movie.NEW_RELEASE), 7),
                new Rental(new Movie("We are the Champion", Movie.REGULAR), 4));
        String expectedNan = "model.Rental Record for nan" + "\n"
                + "\t" + "hello world" + "\t" + "1.5" + "\n"
                + "\t" + "It's Easy" + "\t" + "21.0" + "\n"
                + "\t" + "We are the Champion" + "\t" + "5.0" + "\n"
                + "Amount owed is 27.5" + "\n"
                + "You earned 4 frequent renter points";
        assertEquals(nan.statement(), expectedNan);

        Customer lu = new Customer("lu");
        Customer.addRentalHelper(lu,
                new Rental(new Movie("hello world", Movie.CHILDRENS), 1),
                new Rental(new Movie("It's Easy", Movie.NEW_RELEASE), 5),
                new Rental(new Movie("We are the Champion", Movie.REGULAR), 9));
        String expectedLu = "model.Rental Record for lu" + "\n"
                + "\t" + "hello world" + "\t" + "1.5" + "\n"
                + "\t" + "It's Easy" + "\t" + "15.0" + "\n"
                + "\t" + "We are the Champion" + "\t" + "12.5" + "\n"
                + "Amount owed is 29.0" + "\n"
                + "You earned 4 frequent renter points";
        assertEquals(lu.statement(), expectedLu);
    }

}