package movierental

import kotlin.test.Test
import kotlin.test.assertEquals

class CustomersFormatterTest {
    @Test fun testHeader() {
        val formatter = CustomersFormatter(Customer("Hassan"))
        assertEquals("<h1>Rental Record for <em>Hassan</em></h1>", formatter.header())
    }

    @Test fun rentalsTitlesAsHtmlTable() {
        val expected = """
            <table>
              <tr><td>Ran</td><td>3.5</td></tr>
              <tr><td>Trois Couleurs: Bleu</td><td>2.0</td></tr>
            </table>
        """.trimIndent()
        val customer = Customer("Bob")
            .addRental("Ran", PriceCode.REGULAR, 3)
            .addRental("Trois Couleurs: Bleu", PriceCode.REGULAR, 2)
        assertEquals(expected, CustomersFormatter(customer).rentalsTitles())

    }

    @Test
    fun getAmountOwned() {
        val expected = "<p>Amount owed is <em>3.0</em></p>"
        val customer = Customer("Bob")
            .addRental("Ran", PriceCode.REGULAR, 3)
            .addRental("Trois Couleurs: Bleu", PriceCode.REGULAR, 2)
        val actual = CustomersFormatter(customer).getAmountOwned(3.0)
        assertEquals(expected, actual)
    }
    @Test
    fun renterPointsEarned() {
        val expected = "<p>You earned <em>23</em> frequent renter points</p>"
        val customer = Customer("Bob")
            .addRental("Ran", PriceCode.REGULAR, 3)
            .addRental("Trois Couleurs: Bleu", PriceCode.REGULAR, 2)
        val actual = CustomersFormatter(customer).renterPointsEarned(23)
        assertEquals(expected, actual)
    }

}