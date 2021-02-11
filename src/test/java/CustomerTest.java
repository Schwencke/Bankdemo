import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Customer c1;
    @BeforeEach
    void setUp() {
        c1 = new Customer("Jon");
    }

    @Test
    void getName() {
        assertEquals("Jon", c1.getName());
    }

}