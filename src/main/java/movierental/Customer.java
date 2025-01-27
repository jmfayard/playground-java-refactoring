package movierental;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Customer {

    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

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
        // add bonus for a two day new release rental
        int frequentRenterPoints = rentals.stream()
                .map(Rental::rentalBonus)
                .reduce(0, Integer::sum);

        double totalAmount = rentals.stream()
                .map(Customer::determineAmountsForLine)
                .reduce(0.0, Double::sum);

        String titles = rentals.stream()
                .map(each ->
                        addTitleForLine(each, determineAmountsForLine(each))
                ).collect(Collectors.joining());

        return "Rental Record for " + name + "\n" + titles + ("Amount owed is " + totalAmount + "\n") + ("You earned " + frequentRenterPoints + " frequent renter points\n");
    }

    private static String addTitleForLine(Rental each, double thisAmount) {
        Movie movie = each.movie();
        return String.format("\t%s\t%s\n", movie.title(), thisAmount);
    }

    private static double determineAmountsForLine(Rental each) {
        return switch (each.movie().priceCode()) {
            case PriceCode.REGULAR:
                if (each.daysRented() > 2)
                    yield 1.5 * each.daysRented() - 1;
                else
                    yield 2;
            case PriceCode.NEW_RELEASE:
                yield each.daysRented() * 3;
            case PriceCode.CHILDRENS:
                if (each.daysRented() > 3)
                    yield 1.5 * each.daysRented() - 3;
                else
                    yield 1.5;
        };
    }

}
