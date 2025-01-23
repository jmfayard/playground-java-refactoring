package movierental

object Elvis {

    fun sing() = "You were always on my mind"

    fun formatStatement(titles: String, totalAmount: Double , frequentRenterPoints: Int, name: String): String {
        return "Rental Record for " + name + "\n" + titles + ("Amount owed is " + totalAmount + "\n") + ("You earned " + frequentRenterPoints + " frequent renter points\n");
    }
}