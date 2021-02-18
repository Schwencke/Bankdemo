package UI;

import Persistance.Database;
import Persistance.DbMapper;
import domain.Account;
import domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class MainMenuTest {
    final String USER = "bankuser";
    final String PASSWORD = "Bank123";
    final String URL = "jdbc:mysql://localhost:3306/bank_test?serverTimezone=CET&useSSL=false";

    Database database = new Database(USER, PASSWORD, URL);
    DbMapper dbMapper = new DbMapper(database);
    MainMenu mainMenu = new MainMenu();

    Customer testCustomer;
    int kontoNrTest;
    Account testAccount;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer("test", "testefternavn");
        dbMapper.addCustomer(testCustomer);
        kontoNrTest = dbMapper.newAccount(testCustomer.getCustomer_no());
        testAccount = new Account(kontoNrTest);

    }

    @AfterEach
    void tearDown() {
        dbMapper.deleteCustomer(testCustomer.getCustomer_no());
        dbMapper.deleteAccount(testAccount.getAccNo());
    }

    @Test
    void depositAmount() {
        mainMenu.depositAmount(kontoNrTest, 100);
        int balance = dbMapper.getAccountBalance(kontoNrTest);
        assertEquals(100, balance);


    }

    @Test
    void withdrawAmount() {
    }

    @Test
    void createNewAcc() {
    }

    @Test
    void listCustomerTransactions() {
    }

    @Test
    void listBankTransactions() {
    }

    @Test
    void changeAccount() {
    }

    @Test
    void changeAccountBank() {
    }
}