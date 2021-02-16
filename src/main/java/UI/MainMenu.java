package UI;

import Persistance.Database;
import Persistance.DbMapper;
import domain.Account;
import domain.Customer;
import domain.Transaction;

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
            showMainMenu();
            int valg = Input.getInt("");
            switch (valg) {
                case 1:
                    System.out.println("du valgte 1: hæv penge");

                    break;
                case 2:
                    System.out.println("du valgte 2: kontoudskrift");

                    break;
                case 3:
                    System.out.println("du valgte 3: overfør penge mellem konti");

                    System.out.println("Vælg kunde som du vil hæve fra: " + dbMapper.viewAllCustomersWithBalance().toString());
                    int hæv = Input.getInt("");
                    System.out.println("Hvor meget vil du hæve? ");
                    int hævBeløb=Input.getInt("");
                    account.withDrawAmount(hævBeløb);
                    System.out.println("Vælg kunde som du vil indsætte til: " + dbMapper.viewAllCustomers());
                    int indsæt = Input.getInt("");


                    break;
                case 4:
                    System.out.println("du valgte 0: afslut");
                    running = false;
                    break;
                default:
                    System.out.println("Din indtastning svarede ikke til en valgmulighed");


            }

        }
    }

    private void showMainMenu() {
        System.out.println("Hovedmenu:");
        System.out.println("Du har følgende valgmligheder:");
        System.out.println("1: hæv penge");
        System.out.println("2: kontoudskrift");
        System.out.println("3: overfør penge mellem konti");
        System.out.println("0: afslut");

    }

// mangler vi ikke en usecase for at udskrive en liste over alle kunder?

    //    List<Customer> customerList = dbMapper.viewAllCustomers();
//    for (Customer customer :customerList) {
//        System.out.println(customer.getCustomer_no());
//        System.out.println(customer.getFirst_name());
//        System.out.println(customer.getLast_name());
//        }
}
