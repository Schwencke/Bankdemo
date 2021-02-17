package UI;

import Persistance.Database;
import Persistance.DbMapper;
import domain.Account;
import domain.Customer;
import domain.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    final String USER = "bankuser";
    final String PASSWORD = "Bank123";
    final String URL = "jdbc:mysql://localhost:3306/bank?serverTimezone=CET&useSSL=false";

    Database database = new Database(USER, PASSWORD, URL);
    DbMapper dbMapper = new DbMapper(database);
    Account account;

    public void mainMenuLoop() {
        System.out.println("Velkommen til Ebberød Banks IT-System ");

        boolean running = true;
        while (running) {
            int token = Input.getInt("Tryk 1 for kundelogin, 2 for rådgiverlogin eller 0 for at afslutte ");
            switch (token) {
                case 1:
                    System.out.println("Du har valgt kundelogin");
                    showMainMenuKunde();
                    int valg = Input.getInt("Indtast dit valg: ");
                    switch (valg) {
                        case 1:
                            System.out.println("du valgte 1: indsæt penge");
                            depositAmount();
                            break;
                        case 2:
                            System.out.println("du valgte 2: hæv penge");
                            withdrawAmount();
                            break;
                        case 3:
                            System.out.println("du valgte 3: vis kontoudskrift");
                            listCustomerTransactions();
                            break;
                        case 4:
                            System.out.println("du valgte 4: overfør penge mellem konti");
                            changeAccount();
                            break;
                        case 0:
                            System.out.println("du valgte 0: afslut");
                            break;
                        default:
                            System.out.println("Din indtastning svarede ikke til en valgmulighed");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Du valge banklogin");
                    showMainMenuBank();
                    int bankvalg = Input.getInt("Vælg menu ");
                    switch (bankvalg) {
                        case 1:
                            System.out.println("du valgte 1: indsæt penge");
                            break;
                        case 2:
                            System.out.println("du valgte 2: hæv penge");
                            break;
                        case 3:
                            System.out.println("du valgte 3: kontoudskrift");
                            listBankTransactions();
                            break;
                        case 4:
                            switch (bankvalg) {
                                case 1:
                                    System.out.println("du valgte 1: indsæt penge");
                                    break;
                                case 2:
                                    System.out.println("du valgte 2: hæv penge");
                                    break;

                                case 3:
                                    System.out.println("du valgte 3: overfør penge mellem konti");
                                    System.out.println("Vælg kunde som du vil hæve fra: " + dbMapper.viewAllCustomersWithBalance().toString());
                                    int hæv = Input.getInt("");
                                    System.out.println("Hvor meget vil du hæve? ");
                                    int hævBeløb = Input.getInt("");
                                    System.out.println("Vælg kunde som du vil indsætte til: " + dbMapper.viewAllCustomers());
                                    int indsæt = Input.getInt("");
                                    System.out.println("du valgte 3: kontoudskrift");
                                    break;

                                case 4:
                                    System.out.println("Du valgte 4: overfør mellem konti");
                                    changeAccountBank();
                                    break;
                                case 5:
                                    System.out.println("Du valgte 5: opret ny konto til kunde");
                                    createNewAcc();
                                    break;
                                case 0:
                                    System.out.println("du valgte 0: afslut");
                                    running = false;
                                    break;
                                default:
                                    System.out.println("Din indtastning svarede ikke til en valgmulighed");
                                    break;
                            }
                            break;
                        case 0:
                            running = false;
                            break;
                        default:
                            System.out.println("Din indtastning svarede ikke til en valgmulighed");
                            break;
                    }


            }
        }
    }

    private void showMainMenuKunde() {
        System.out.println("Hovedmenu:");
        System.out.println("Du har følgende valgmligheder:");
        System.out.println("1: indsæt penge");
        System.out.println("2: hæv penge");
        System.out.println("3: kontoudskrift");
        System.out.println("4: overfør penge mellem konti");
        System.out.println("0: afslut");

    }

    private void showMainMenuBank() {
        System.out.println("Hovedmenu:");
        System.out.println("Du har følgende valgmligheder:");
        System.out.println("1: indsæt penge");
        System.out.println("2: hæv penge");
        System.out.println("3: kontoudskrift");
        System.out.println("4: overfør penge mellem konti");
        System.out.println("5: opret ny konto til kunde");
        System.out.println("0: afslut");

    }

//    private void newAcc (int customerID)
//    {
//        List<Customer> customerList = dbMapper.viewAllCustomers();
//        for (Customer customer : customerList) {
//            if (customerID == customer.getCustomer_no()){
//                dbMapper.newAccount(customer.getCustomer_no());
//                System.out.println("En konto blev oprettet");
//            }
////        }
//    }

// mangler vi ikke en usecase for at udskrive en liste over alle kunder?

    //    List<Customer> customerList = dbMapper.viewAllCustomers();
//    for (Customer customer :customerList) {
//        System.out.println(customer.getCustomer_no());
//        System.out.println(customer.getFirst_name());
//        System.out.println(customer.getLast_name());
//        }

    public void depositAmount() {
//        dbMapper.addTransaction(new Transaction(300, 1));
        int kontoNr = Input.getInt("indtast kontonummer: ");
        int amount = Input.getInt("hvor meget ønsker du at indsætte: ");
        if (amount <= 0) {
            System.out.println("for at indsætte penge, skal der indtastes et positivt heltal. ");
            depositAmount();
        } else {
            if (dbMapper.addTransaction(new Transaction(kontoNr, amount)) != null) {
                dbMapper.updateAccountBalance(kontoNr);
                System.out.println("du har indsat " + amount + " kr. på din konto");
            }
        }
//                System.out.println("\nDin nye saldo er: " + getBalance() + "kr.");
    }

    public void withdrawAmount() {
        int kontoNr = Input.getInt("indtast kontonummer: ");
        int amount = Input.getInt("hvor meget ønsker du at hæve: ");
        int printamount = amount;
        amount = -amount;
        if (amount >= 0) {
            System.out.println("for at hæve penge, skal der indtastes et heltal. ");
            withdrawAmount();
        } else {
            if (dbMapper.addTransaction(new Transaction(kontoNr, amount)) != null) {
                dbMapper.updateAccountBalance(kontoNr);
                System.out.println("du har hævet " + printamount + " kr. på din konto");
            }
        }
    }

    public void createNewAcc() {
        int kundenr = Input.getInt("Hvilke kunde skal have ny konto?: ");
        if (dbMapper.newAccount(kundenr) != 0) {
            int accNo = dbMapper.newAccount(kundenr);
            System.out.println("En ny konto til kunden med nr: " + accNo + " blev oprettet");
        } else System.out.println("fejlen opstod fordi funktionen retunere 0");
    }

    public void listCustomerTransactions() {
        // transaktioner vises for kunde 1, da der ikke er et loginsystem til forskellige kunder
        int acc_no = 1;
        List<Transaction> transactionList = new ArrayList<>();
        transactionList = dbMapper.getTransactionForAccNo(acc_no);
        for (Transaction transaction : transactionList) {
            System.out.println("Printer transaktionsliste for kontonr: " + acc_no);
            System.out.print("Transaktions ID:" + transaction.getTransactionNr() + "");
            System.out.print(" Beløb:" + transaction.getAmount());
            System.out.println(" Dato: " + transaction.getDate());

        }
    }

    public void listBankTransactions() {
        int acc_no = Input.getInt("Hvilken kunde vil du se kontoudskrift for?: ");
        List<Transaction> transactionList;
        transactionList = dbMapper.getTransactionForAccNo(acc_no);
        for (Transaction transaction : transactionList) {
            System.out.println("Printer transaktionsliste for kontonr: " + acc_no);
            System.out.print("Transaktions ID:" + transaction.getTransactionNr() + "");
            System.out.print(" Beløb:" + transaction.getAmount());
            System.out.println(" Dato: " + transaction.getDate());

        }
    }

    public void changeAccount() {
        int kontoNr = Input.getInt("indtast kontonummer du vil hæve fra: ");
        int amount = Input.getInt("hvor meget ønsker du at hæve: ");
        int printamount = amount;
        amount = -amount;
        int newAccount = Input.getInt("Hvilken konto vil du indsætte til? ");
        if (amount >= 0) {
            System.out.println("for at hæve penge, skal der indtastes et heltal. ");
            changeAccount();
        } else {
            if (dbMapper.addTransaction(new Transaction(kontoNr, amount)) != null) {
                dbMapper.updateAccountBalance(kontoNr);
                dbMapper.addTransaction(new Transaction(newAccount, printamount));
                dbMapper.updateAccountBalance(newAccount);
                System.out.println("du har hævet " + printamount + " kr. på konto " + kontoNr + " og indsat " + printamount + " på konti " + newAccount);
            }
        }
    }

    public void changeAccountBank() {
        int kontoNr = Input.getInt("indtast kontonummer du vil hæve fra: ");
        int amount = Input.getInt("hvor meget ønsker du at hæve: ");
        int printamount = amount;
        amount = -amount;
        if (amount >= 0) {
            System.out.println("for at hæve penge, skal der indtastes et heltal. ");
            changeAccount();
        } else {
            int newAccount = Input.getInt("Hvilken konto vil du indsætte til? ");
            if (dbMapper.addTransaction(new Transaction(kontoNr, amount)) != null) {
                dbMapper.updateAccountBalance(kontoNr);
                dbMapper.addTransaction(new Transaction(newAccount, printamount));
                dbMapper.updateAccountBalance(newAccount);
                System.out.println("du har hævet " + printamount + " kr. på konto " + kontoNr + " og indsat " + printamount + " på konti " + newAccount);
            }
        }
    }

}
