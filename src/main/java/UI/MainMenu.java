package UI;

import Persistance.Database;
import Persistance.DbMapper;
import domain.Account;
import domain.Customer;
import domain.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    Database database;
    DbMapper dbMapper;

    public MainMenu(Database database) {
        this.database = database;
        this.dbMapper = new DbMapper(database);
    }

    public void mainMenuLoop() {
        System.out.println("Velkommen til Ebberød Banks IT-System ");

        boolean running = true;
        while (running) {
            int token = Input.getInt("Tryk 1 for kundelogin, 2 for rådgiverlogin eller 0 for at afslutte: ");
            switch (token) {
                case 1:
                    System.out.println("Du har valgt kundelogin");
                    showMainMenuKunde();
                    int valg = Input.getInt("Indtast dit valg: ");
                    switch (valg) {
                        case 1:
                            System.out.println("du valgte 1: indsæt penge");
                            int indsæt_kontoNr = Input.getInt("indtast kontonummer: ");
                            int indsæt_amount = Input.getInt("hvor meget ønsker du at indsætte: ");
                            depositAmount(indsæt_kontoNr, indsæt_amount);
                            break;
                        case 2:
                            System.out.println("du valgte 2: hæv penge");
                            int hæv_kontoNr = Input.getInt("indtast kontonummer: ");
                            int hæv_amount = Input.getInt("hvor meget ønsker du at hæve?: ");
                            withdrawAmount(hæv_kontoNr, hæv_amount);
                            break;
                        case 3:
                            System.out.println("du valgte 3: vis kontoudskrift");
                            int acc_no = Input.getInt("Hvilket konto nummer?");
                            listCustomerTransactions(acc_no);
                            break;
                        case 4:
                            System.out.println("du valgte 4: overfør penge mellem konti");
                            int kontoNr = Input.getInt("indtast kontonummer du vil hæve fra: ");
                            int amount = Input.getInt("hvor meget ønsker du at hæve: ");
                            int printamount = amount;
                            amount = -amount;
                            int newAccount = Input.getInt("Hvilken konto vil du indsætte til? ");
                            if (amount >= 0) {
                                System.out.println("for at hæve penge, skal der indtastes et heltal. ");
                                return;
                            } else {
                                changeAccount(kontoNr, amount, newAccount);
                            }
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
                    int bankvalg = Input.getInt("Indtast dit valg: ");
                    switch (bankvalg) {
                        case 1:
                            System.out.println("du valgte 1: indsæt penge");
                            break;
                        case 2:
                            System.out.println("du valgte 2: hæv penge");
                            break;
                        case 3:
                            System.out.println("du valgte 3: kontoudskrift");
                            int acc_no = Input.getInt("Hvilken kunde vil du se kontoudskrift for?: ");
                            listBankTransactions(acc_no);
                            break;


//                                    System.out.println("du valgte 3: overfør penge mellem konti");
//                                    System.out.println("Vælg kunde som du vil hæve fra: " + dbMapper.viewAllCustomersWithBalance().toString());
//                                    int hæv = Input.getInt("");
//                                    System.out.println("Hvor meget vil du hæve? ");
//                                    int hævBeløb = Input.getInt("");
//                                    System.out.println("Vælg kunde som du vil indsætte til: " + dbMapper.viewAllCustomers());
//                                    int indsæt = Input.getInt("");
//                                    System.out.println("du valgte 3: kontoudskrift");
//                                    break;

                        case 4:
                            System.out.println("Du valgte 4: overfør mellem konti");
                            int kontoNr = Input.getInt("indtast kontonummer du vil hæve fra: ");
                            int amount = Input.getInt("hvor meget ønsker du at hæve: ");
                            int printamount = amount;
                            amount = -amount;
                            int newAccount = Input.getInt("Hvilken konto vil du indsætte til? ");
                            if (amount >= 0) {
                                System.out.println("for at hæve penge, skal der indtastes et heltal. ");
                                return;
                            } else {
                                changeAccountBank(kontoNr, amount, newAccount);
                            }
                            break;
                        case 5:
                            System.out.println("Du valgte 5: opret ny konto til kunde");
                            int kundenr = Input.getInt("Hvilke kunde skal have ny konto?: ");
                            createNewAcc(kundenr);
                            break;
                        case 6:
                            System.out.println("Du valgte 6: opret ny kunde");
                            String fornavn = Input.getString("Indtast fornavn: ");
                            String efternavn = Input.getString("Indtast efternavn: ");
                            addCustomer(fornavn, efternavn);
                            break;

                        case 7:
                            System.out.println("Du valgte 7: slet kunde fra kundekartotek");
                            if (dbMapper.deleteCustomer(Input.getInt("indtast kundenummer for den kunde du ønsker at slette: "))) {
                                System.out.println("kunden blev slettet fra kartotek");
                            }
                            break;
                        case 8:
                            System.out.println("Du valgte 8: vis oversigt over kunder");
                            viewAllCustomers();
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

        System.out.println("6: opret ny kunde");
        System.out.println("7: slet kunde fra kartotek");
        System.out.println("8: vis oversigt over alle kunder");

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

    public void viewAllCustomers() {
        List<Customer> customerList = dbMapper.viewAllCustomers();
        System.out.println("oversigt over kunder");
        System.out.println("kundenr. \t" + "fornavn \t" + "efternavn");
        for (Customer customer : customerList) {
            System.out.print(customer.getCustomer_no() + "\t" + "\t" + "\t");
            System.out.print(customer.getFirst_name() + "\t" + "\t");
            System.out.println(customer.getLast_name());
        }
    }

    private void addCustomer(String fornavn, String efternavn) {
        Customer newCustomer = new Customer(fornavn, efternavn);
        if (dbMapper.addCustomer(newCustomer) != null) {
            System.out.println("Ny kunde med kontonummer " + newCustomer.getCustomer_no() + " blev oprettet");
        }

    }

    public void depositAmount(int kontoNr, int amount) {
        if (amount <= 0) {
            System.out.println("for at indsætte penge, skal der indtastes et positivt heltal. ");
            return;
        } else {
            if (dbMapper.addTransaction(new Transaction(kontoNr, amount)) != null) {
                dbMapper.updateAccountBalance(kontoNr);
                System.out.print("du har indsat " + amount + " kr. på din konto. ");
            }
        }
        System.out.println("Saldo: " + dbMapper.getAccountBalance(kontoNr) + " kr.");
    }

    public void withdrawAmount(int kontoNr, int amount) {
        int printamount = amount;
        amount = -amount;
        if (amount >= 0) {
            System.out.println("for at hæve penge, skal der indtastes et heltal. ");
            return;
        } else {
            if (dbMapper.addTransaction(new Transaction(kontoNr, amount)) != null) {
                dbMapper.updateAccountBalance(kontoNr);
                System.out.println("du har hævet " + printamount + " kr. på din konto");
            }
        }
    }

    public void createNewAcc(int kundenr) {
        if (dbMapper.newAccount(kundenr) != 0) {
            int accNo = dbMapper.newAccount(kundenr);
            System.out.println("En ny konto til kunden med nr: " + accNo + " blev oprettet");
        } else System.out.println("fejlen opstod fordi funktionen retunere 0");
    }

    public List<Transaction> listCustomerTransactions(int acc_no) {
        List<Transaction> transactionList;
        transactionList = dbMapper.getTransactionForAccNo(acc_no);
        System.out.println("Printer transaktionsliste for kontonr: " + acc_no);
        for (Transaction transaction : transactionList) {
            System.out.print("Transaktions ID:" + transaction.getTransactionNr() + "");
            System.out.print(" Beløb :" + transaction.getAmount());
            System.out.println(" Dato : " + transaction.getDate());
        }
        return transactionList;
    }

    public List<Transaction> listBankTransactions(int acc_no) {
        List<Transaction> transactionList;
        transactionList = dbMapper.getTransactionForAccNo(acc_no);
        for (Transaction transaction : transactionList) {
            System.out.println("Printer transaktionsliste for kontonr: " + acc_no);
            System.out.print("Transaktions ID:" + transaction.getTransactionNr() + "");
            System.out.print(" Beløb:" + transaction.getAmount());
            System.out.println(" Dato: " + transaction.getDate());
        }
        return transactionList;
    }

    public void changeAccount(int kontoNr, int amountSend, int newAccount) {
        int amountWithdrawn = -amountSend;
        if (dbMapper.addTransaction(new Transaction(kontoNr, amountWithdrawn)) != null) {
            dbMapper.updateAccountBalance(kontoNr);
            dbMapper.addTransaction(new Transaction(newAccount, amountSend));
            dbMapper.updateAccountBalance(newAccount);
            System.out.println("du har hævet " + amountSend + " kr. på konto " + kontoNr + " og indsat " + amountSend + " på konti " + newAccount);
        }
    }


    public void changeAccountBank(int kontoNr, int amountSend, int newAccount) {
        int amountWithdrawn = -amountSend;
        if (dbMapper.addTransaction(new Transaction(kontoNr, amountWithdrawn)) != null) {
            dbMapper.updateAccountBalance(kontoNr);
            dbMapper.addTransaction(new Transaction(newAccount, amountSend));
            dbMapper.updateAccountBalance(newAccount);
            System.out.println("du har hævet " + amountSend + " kr. på konto " + kontoNr + " og indsat " + amountSend + " på konti " + newAccount);
        }
    }
}
