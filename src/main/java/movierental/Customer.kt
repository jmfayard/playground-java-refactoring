package movierental

data class Customer(
    @JvmField val name: String,
) {
    @JvmField val rentals: MutableList<Rental> = mutableListOf()

    fun addRental(movie: String, priceCode: PriceCode, daysRented: Int): Customer {
        return addRental(Rental(Movie(movie, priceCode), daysRented))
    }

    fun addRental(arg: Rental): Customer {
        rentals.add(arg)
        return this
    }

    fun statement(): String =
        CustomersFormatter(this).statement()
}
