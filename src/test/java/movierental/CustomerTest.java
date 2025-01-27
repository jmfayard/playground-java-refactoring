package movierental;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void test() {
        Customer customer = new Customer("Bob")
                .addRental("Jaws", PriceCode.REGULAR, 2)
                .addRental("Golden Eye", PriceCode.REGULAR, 3)
                .addRental("Short New", PriceCode.NEW_RELEASE, 1)
                .addRental("Long New", PriceCode.NEW_RELEASE, 2)
                .addRental("Bambi", PriceCode.CHILDRENS, 3)
                .addRental("Toy Story", PriceCode.CHILDRENS, 4);

        String expected = """
                <h1>Rental Record for <em>Bob</em></h1>
                <table>
                  <tr><td>Jaws</td><td>2.0</td></tr>
                  <tr><td>Golden Eye</td><td>3.5</td></tr>
                  <tr><td>Short New</td><td>3.0</td></tr>
                  <tr><td>Long New</td><td>6.0</td></tr>
                  <tr><td>Bambi</td><td>1.5</td></tr>
                  <tr><td>Toy Story</td><td>3.0</td></tr>
                </table>
                <p>Amount owed is <em>19.0</em></p>
                <p>You earned <em>7</em> frequent renter points</p>
                """;

        assertEquals(expected, customer.statement());
    }

    @Test
    public void kotlinIsConfigured() {
        assertEquals("You were always on my mind", Elvis.INSTANCE.sing());
    }
}