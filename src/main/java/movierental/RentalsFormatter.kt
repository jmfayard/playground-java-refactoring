package movierental

import java.util.stream.Collectors

class RentalsFormatter(val customer: Customer) {

    fun statement(): String {
        // add bonus for a two day new release rental
        val frequentRenterPoints = getFrequentRenterPoints()
        val totalAmount = getTotalAmount()
        val titles = rentalsTitles()

        return "Rental Record for " + customer.name + "\n" + titles + ("Amount owed is " + totalAmount + "\n") + ("You earned " + frequentRenterPoints + " frequent renter points\n")
    }

    private fun rentalsTitles(): String {
        return customer.rentals.stream()
            .map<String> { each: Rental ->
                addTitleForLine(
                    each,
                    determineAmountsForLine(each)
                )
            }
            .collect(Collectors.joining())
    }

    fun getTotalAmount(): Double {
        return customer.rentals.stream()
            .map<Double> { each: Rental -> determineAmountsForLine(each) }
            .reduce(0.0) { a, b -> a+b }
    }

    fun getFrequentRenterPoints(): Int {
        return customer.rentals.stream()
            .map<Int> { obj: Rental -> obj.rentalBonus() }
            .reduce(0) { a: Int, b: Int -> Integer.sum(a, b) }
    }

    companion object {
        fun addTitleForLine(each: Rental, thisAmount: kotlin.Double): String {
            val movie = each.movie
            return String.format("\t%s\t%s\n", movie.title, thisAmount)
        }

        fun determineAmountsForLine(each: Rental): Double {
            return when (each.movie.priceCode) {
                PriceCode.REGULAR -> if (each.daysRented > 2) 1.5 * each.daysRented - 1.0 else 2.0
                PriceCode.NEW_RELEASE -> each.daysRented * 3.0
                PriceCode.CHILDRENS -> if (each.daysRented > 3) 1.5 * each.daysRented - 3.0 else 1.5
            }
        }
    }
}
