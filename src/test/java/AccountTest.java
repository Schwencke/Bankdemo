/*
import domain.Account;
import domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Customer c1;
    Account account;
    int amount = 100;
    @BeforeEach
    void setUp() {
        c1 = new Customer("Jon");
        account = new Account(c1);
        account.depositAmount(100);
    }

    @Test
    void getCustomer() {
       assertEquals(c1,account.getCustomer());
    }

    @Test
    void getBalance() {
        //acount starts with 100
        assertEquals(100, account.getBalance());
    }

    @Test
    void withDrawAmount() {
        //acount starts with 100
        account.withDrawAmount(95);
        assertEquals(5,account.getBalance());

    }

    @Test
    void depositAmount() {
        //acount starts with 100
        account.depositAmount(125);
        assertEquals(225, account.getBalance());
    }

    @Test
    void getTransactions() {
        assertEquals(1, account.getTransactions().size());
        assertEquals(100, account.getTransactions().get(0).getAmount());
    }
}*/
