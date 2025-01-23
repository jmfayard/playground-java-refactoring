package movierental;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Test;

public class CustomerTest {

    @Test
    public void test() {
        Customer customer = new Customer("Bob")
            .addRental(new Rental(new Movie("Jaws", Movie.REGULAR), 2))
            .addRental(new Rental(new Movie("Golden Eye", Movie.REGULAR), 3))
            .addRental(new Rental(new Movie("Short New", Movie.NEW_RELEASE), 1))
            .addRental(new Rental(new Movie("Long New", Movie.NEW_RELEASE), 2))
            .addRental(new Rental(new Movie("Bambi", Movie.CHILDRENS), 3))
            .addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS), 4))
                ;

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