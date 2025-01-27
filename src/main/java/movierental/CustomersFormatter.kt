package movierental

class CustomersFormatter(val customer: Customer) {

    fun statement(): String {
        // add bonus for a two day new release rental
        val totalAmount = getTotalAmount()

        return """
${header()}
${rentalsTitles()}
${getAmountOwned(totalAmount)}
${renterPointsEarned(getFrequentRenterPoints())}
""".trimStart()
    }

    fun renterPointsEarned(frequentRenterPoints: Int): String = "<p>You earned <em>$frequentRenterPoints</em> frequent renter points</p>"

    fun getAmountOwned(totalAmount: Double): String = "<p>Amount owed is <em>$totalAmount</em></p>"

    fun header(): String = """<h1>Rental Record for <em>${customer.name}</em></h1>"""

    fun rentalsTitles(): String = customer.rentals
        .joinToString(
            prefix = "<table>\n",
            postfix = "</table>",
            separator = ""
        ) { rental ->
            titleForLine(rental, determineAmountsForLine(rental))
        }.trimEnd()

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
            "  <tr><td>${each.movie().title}</td><td>$thisAmount</td></tr>\n"

        fun determineAmountsForLine(each: Rental): Double {
            return when (each.movie.priceCode) {
                PriceCode.REGULAR -> if (each.daysRented > 2) 1.5 * each.daysRented - 1.0 else 2.0
                PriceCode.NEW_RELEASE -> each.daysRented * 3.0
                PriceCode.CHILDRENS -> if (each.daysRented > 3) 1.5 * each.daysRented - 3.0 else 1.5
            }
        }
    }
}
