import java.util.ArrayList;
import java.util.List;

/**
 * Created by rico on 2016/10/14.
 */
public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        String result = "Rental Record for " + getName() + "\n";

        for (Rental each : rentals) {

            result += "\t" + each.getMovie().getTitle() + "\t" + each.getCharge() + "\n";
        }

        result += "Amount owed is " + getTotalCharge() + "\n";
        result +="You earned " + getTotalFrequentRenterPoints() + " frequent renter points";

        return result;
    }

    public String htmlStatement() {
        String result = "<H1> Rentals for<EM>" + getName() + "</EM></H1><P>\n";
        for (Rental each : rentals) {
            result += each.getMovie().getTitle() + ": " + each.getCharge() + "<BR>\n";
        }

        result += "<P>You owe <EM>" + getTotalCharge() + "</EM><P>\n";
        result += "On this rental you earned <EM>" + getTotalFrequentRenterPoints() + "</EM> frequent renter points";
        return result;
    }

    public static void addRentalHelper(Customer customer, Rental... rentals) {
        for (Rental rental : rentals) {
            customer.addRental(rental);
        }
    }

    private double getTotalCharge() {
        double result = 0;
        for (Rental each : rentals) {
            result += each.getCharge();
        }

        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        for (Rental each : rentals) {
            result += each.getFrequentRenterPoints();
        }

        return result;
    }

    public static void main(String[] args) {

        Customer ling = new Customer("ling");
        addRentalHelper(ling,
                new Rental(new Movie("hello world", Movie.CHILDRENS), 5),
                new Rental(new Movie("It's Easy", Movie.NEW_RELEASE), 3),
                new Rental(new Movie("We are the Champion", Movie.REGULAR), 7));
        String expectedLing = "Rental Record for ling" + "\n"
                + "\t" + "hello world" + "\t" + "4.5" + "\n"
                + "\t" + "It's Easy" + "\t" + "9.0" + "\n"
                + "\t" + "We are the Champion" + "\t" + "9.5" + "\n"
                + "Amount owed is 23.0" + "\n"
                + "You earned 4 frequent renter points";
        System.out.println(ling.getName() + " " + ling.statement().equals(expectedLing) + "\n");

        Customer nan = new Customer("nan");
        addRentalHelper(nan,
                new Rental(new Movie("hello world", Movie.CHILDRENS), 3),
                new Rental(new Movie("It's Easy", Movie.NEW_RELEASE), 7),
                new Rental(new Movie("We are the Champion", Movie.REGULAR), 4));
        String expectedNan = "Rental Record for nan" + "\n"
                + "\t" + "hello world" + "\t" + "1.5" + "\n"
                + "\t" + "It's Easy" + "\t" + "21.0" + "\n"
                + "\t" + "We are the Champion" + "\t" + "5.0" + "\n"
                + "Amount owed is 27.5" + "\n"
                + "You earned 4 frequent renter points";
        System.out.println(nan.getName() + " " + nan.statement().equals(expectedNan) + "\n");

        Customer lu = new Customer("lu");
        addRentalHelper(lu,
                new Rental(new Movie("hello world", Movie.CHILDRENS), 1),
                new Rental(new Movie("It's Easy", Movie.NEW_RELEASE), 5),
                new Rental(new Movie("We are the Champion", Movie.REGULAR), 9));
        String expectedLu = "Rental Record for lu" + "\n"
                + "\t" + "hello world" + "\t" + "1.5" + "\n"
                + "\t" + "It's Easy" + "\t" + "15.0" + "\n"
                + "\t" + "We are the Champion" + "\t" + "12.5" + "\n"
                + "Amount owed is 29.0" + "\n"
                + "You earned 4 frequent renter points";
        System.out.println(lu.getName() + " " + lu.statement().equals(expectedLu) + "\n");

    }
}


