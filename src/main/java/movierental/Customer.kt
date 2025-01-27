package movierental;

import java.util.ArrayList;
import java.util.List;


public class Customer {

    public final String name;
    public final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public Customer addRental(String movie, PriceCode priceCode, int daysRented) {
        return addRental(new Rental(new Movie(movie, priceCode), daysRented));
    }

    public Customer addRental(Rental arg) {
        rentals.add(arg);
        return this;
    }

    public String statement() {
        return new RentalsFormatter(this).statement();
    }
}
