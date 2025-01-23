package movierental;

/**
 * The rental class represents a customer renting a movie.
 */
public record Rental(Movie movie, Integer daysRented) {
    public boolean isEligibleToRentalBonus() {
        return movie.isNewRelease() && this.daysRented() > 1;
    }

    public int rentalBonus() {
        return isEligibleToRentalBonus() ? 2 : 1;
    }
}
