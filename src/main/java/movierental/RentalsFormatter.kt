package movierental

class RentalsFormatter(val customer: Customer) {

    fun statement(): String {
        // add bonus for a two day new release rental
        val frequentRenterPoints = getFrequentRenterPoints()
        val totalAmount = getTotalAmount()
        val titles = rentalsTitles()

        return "Rental Record for " + customer.name + "\n" + titles + ("Amount owed is " + totalAmount + "\n") + ("You earned " + frequentRenterPoints + " frequent renter points\n")
    }

    fun rentalsTitles(): String = customer.rentals
        .joinToString(separator = "") { rental ->
            titleForLine(rental, determineAmountsForLine(rental))
        }

    fun getTotalAmount(): Double =
        customer.rentals
            .map { rental: Rental -> determineAmountsForLine(rental) }
            .sum()

    fun getFrequentRenterPoints(): Int =
        customer.rentals
            .map(Rental::rentalBonus)
            .sum()

    companion object {
        fun titleForLine(each: Rental, thisAmount: Double): String =
            "\t${each.movie().title}\t$thisAmount\n"

        fun determineAmountsForLine(each: Rental): Double {
            return when (each.movie.priceCode) {
                PriceCode.REGULAR -> if (each.daysRented > 2) 1.5 * each.daysRented - 1.0 else 2.0
                PriceCode.NEW_RELEASE -> each.daysRented * 3.0
                PriceCode.CHILDRENS -> if (each.daysRented > 3) 1.5 * each.daysRented - 3.0 else 1.5
            }
        }
    }
}
