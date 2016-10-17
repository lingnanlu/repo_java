package model;

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
        String result = "model.Rental Record for " + getName() + "\n";

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
}


