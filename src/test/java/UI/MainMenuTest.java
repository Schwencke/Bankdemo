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
        int testAccountNr = dbMapper.newAccount(testCustomer.getCustomer_no());
        testAccount = new Account(testAccountNr);


    }

    @AfterEach
    void tearDown() {
        dbMapper.deleteTESTAccounts();
        dbMapper.deleteTESTCustomers();

    }

    @Test
    void depositAmount() {
        mainMenu.depositAmount(testAccount.getAccNo(), 100);
        assertEquals(100, dbMapper.getAccountBalance(testAccount.getAccNo()));


    }

    @Test
    void withdrawAmount() {
        mainMenu.depositAmount(testAccount.getAccNo(), 100);
        mainMenu.withdrawAmount(testAccount.getAccNo(), 100);
        assertEquals(0, dbMapper.getAccountBalance(testAccount.getAccNo()));

    }

    @Test
    void createNewAcc() {
        int coNo = 2;
        mainMenu.createNewAcc(coNo);
        assertNotNull(dbMapper.getAccountBalance(coNo));
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
//        tilføjer en modtager-bruger med konto lokalt til denne test
        Customer recieverCustomer = new Customer("reciever", "recieverSurname");
        dbMapper.addCustomer(recieverCustomer);
        int recieverAccountNr = dbMapper.newAccount(recieverCustomer.getCustomer_no());
        Account recieverAccount = new Account(recieverAccountNr);

//                sætter penge ind på afsenders konto
        mainMenu.depositAmount(testAccount.getAccNo(), 100);

        mainMenu.changeAccount(testAccount.getAccNo(), 50, recieverAccount.getAccNo());

        assertEquals(50, dbMapper.getAccountBalance(recieverAccount.getAccNo()));


    }

    @Test
    void changeAccountBank() {
        //        kopi af changeAccount
        Customer recieverCustomer = new Customer("reciever", "recieverSurname");
        dbMapper.addCustomer(recieverCustomer);
        int recieverAccountNr = dbMapper.newAccount(recieverCustomer.getCustomer_no());
        Account recieverAccount = new Account(recieverAccountNr);
        mainMenu.depositAmount(testAccount.getAccNo(), 100);
        mainMenu.changeAccount(testAccount.getAccNo(), 50, recieverAccount.getAccNo());
        assertEquals(50, dbMapper.getAccountBalance(recieverAccount.getAccNo()));
    }
}