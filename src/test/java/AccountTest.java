import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Customer c1;
    Account account;
    @BeforeEach
    void setUp() {
        c1 = new Customer("Jon");
        account = new Account(c1);
        int newBalance = account.depositAmount(125);
        //newBalance = account.withDrawAmount(125);
    }

    @Test
    void getCustomer() {
       assertEquals(c1,account.getCustomer());
    }

    @Test
    void getBalance() {
        assertEquals(125, account.getBalance());
    }

    @Test
    void withDrawAmount() {
        assertEquals(0,account.getBalance());

    }

    @Test
    void depositAmount() {
        assertEquals(125, account.getBalance());
    }

    @Test
    void getTransactions() {
    }
}