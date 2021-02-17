package UI;

import Persistance.Database;
import Persistance.DbMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {
    final String USER = "bankuser";
    final String PASSWORD = "Bank123";
    final String URL = "jdbc:mysql://localhost:3306/bank_test?serverTimezone=CET&useSSL=false";

    Database database = new Database(USER, PASSWORD, URL);
    DbMapper dbMapper = new DbMapper(database);

    @BeforeEach
    void setUp() {
        int kontoNr = 1; //det er lidt uheldigt at lade testen afhænge af databasen..

    }

    @Test
    void depositAmount() {

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