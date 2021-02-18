package UI;

import Persistance.Database;
import Persistance.DbMapper;
import domain.Account;
import domain.Customer;
import domain.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class MainMenuTest {
    final String USER = "bankuser";
    final String PASSWORD = "Bank123";
    final String URL = "jdbc:mysql://localhost:3306/bank_test?serverTimezone=CET&useSSL=false";

    Database database = new Database(USER, PASSWORD, URL);
    DbMapper dbMapper = new DbMapper(database);
    MainMenu mainMenu = new MainMenu(database);

    Customer testCustomer;
    Account testAccount;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer("test", "testefternavn");
        dbMapper.addCustomer(testCustomer);
        dbMapper.newAccount(testCustomer.getCustomer_no());

    }

    @AfterEach
    void tearDown() {
        dbMapper.deleteTESTAccounts();
        dbMapper.deleteTESTCustomers();

    }

    @Test
    void depositAmount() {
        mainMenu.depositAmount(testCustomer.getCustomer_no(), 100);
        assertEquals(100, dbMapper.getAccountBalance(testCustomer.getCustomer_no()));


    }

    @Test
    void withdrawAmount() {
        mainMenu.depositAmount(testCustomer.getCustomer_no(), 100);
        mainMenu.withdrawAmount(testCustomer.getCustomer_no(), 100);
        assertEquals(0, dbMapper.getAccountBalance(testCustomer.getCustomer_no()));

    }

    @Test
    void createNewAcc() {
       int newAccNo = 2;
       dbMapper.newAccount(newAccNo);
       assertNotNull(dbMapper.getAccountBalance(newAccNo));
    }

    @Test
    void listCustomerTransactions() {
        mainMenu.depositAmount(testCustomer.getCustomer_no(), 100);
       assertFalse(mainMenu.listCustomerTransactions(testCustomer.getCustomer_no()).isEmpty());


    }

    @Test
    void listBankTransactions() {
        mainMenu.depositAmount(testCustomer.getCustomer_no(), 100);
        assertFalse(mainMenu.listBankTransactions(testCustomer.getCustomer_no()).isEmpty());
    }

    @Test
    void changeAccount() {
    }

    @Test
    void changeAccountBank() {
    }
}