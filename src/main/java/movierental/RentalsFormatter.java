package movierental;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class RentalsFormatter {
    private final Customer customer;

    public RentalsFormatter(Customer customer) {
        this.customer = customer;
    }

    public String statement() {
        // add bonus for a two day new release rental
        int frequentRenterPoints = getFrequentRenterPoints();
        double totalAmount = getTotalAmount();
        String titles = rentalsTitles();

        return "Rental Record for " + customer.name + "\n" + titles + ("Amount owed is " + totalAmount + "\n") + ("You earned " + frequentRenterPoints + " frequent renter points\n");
    }

    @NotNull
    private String rentalsTitles() {
        return customer.rentals.stream()
                .map(each -> addTitleForLine(each, determineAmountsForLine(each)))
                .collect(Collectors.joining());
    }

    double getTotalAmount() {
        return customer.rentals.stream()
                .map(RentalsFormatter::determineAmountsForLine)
                .reduce(0.0, Double::sum);
    }

    int getFrequentRenterPoints() {
        return customer.rentals.stream()
                .map(Rental::rentalBonus)
                .reduce(0, Integer::sum);
    }

    static String addTitleForLine(Rental each, double thisAmount) {
        Movie movie = each.movie();
        return String.format("\t%s\t%s\n", movie.title(), thisAmount);
    }

    static double determineAmountsForLine(Rental each) {
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
