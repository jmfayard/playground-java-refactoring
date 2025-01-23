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

    public Customer addRental(Rental arg) {
        rentals.add(arg);
        return this;
    }

    public String statement() {
        int frequentRenterPoints = rentals.stream()
                .map(this::addBonusForRental)
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
        return "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
    }

    int addBonusForRental(Rental each) {
        // add bonus for a two day new release rental
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
            return  2;
        else {
            return  1;
        }
    }

    private static double determineAmountsForLine(Rental each) {
        double thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }
}
