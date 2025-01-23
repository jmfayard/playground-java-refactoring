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
                Rental Record for Bob
                	Jaws	2.0
                	Golden Eye	3.5
                	Short New	3.0
                	Long New	6.0
                	Bambi	1.5
                	Toy Story	3.0
                Amount owed is 19.0
                You earned 7 frequent renter points
                """;

        assertEquals(expected, customer.statement());
    }

    @Test
    public void kotlinIsConfigured() {
        assertEquals("You were always on my mind", Elvis.INSTANCE.sing());
    }
}