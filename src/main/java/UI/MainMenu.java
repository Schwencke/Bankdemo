package UI;

import Persistance.Database;
import Persistance.DbMapper;
import domain.Customer;

import java.util.List;

public class MainMenu {
    final String USER = "bankuser";
    final String PASSWORD = "Bank123";
    final String URL = "jdbc:mysql://localhost:3306/bank?serverTimezone=CET&useSSL=false";

    Database database = new Database(USER, PASSWORD, URL);
    DbMapper dbMapper = new DbMapper(database);

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

                    break;
                case 9:
                    newAcc(Input.getInt("Hvad er dit kundenr?"));
                    break;
                case 0:
                    System.out.println("du valgte 0: afslut");
                    running=false;
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

    private void newAcc (int customerID)
    {
        List<Customer> customerList = dbMapper.viewAllCustomers();
        for (Customer customer : customerList) {
            if (customerID == customer.getCustomer_no()){
                dbMapper.newAccount(customer.getCustomer_no());
                System.out.println("En konto blev oprettet");
            }

        }
    }

// mangler vi ikke en usecase for at udskrive en liste over alle kunder?

    //    List<Customer> customerList = dbMapper.viewAllCustomers();
//    for (Customer customer :customerList) {
//        System.out.println(customer.getCustomer_no());
//        System.out.println(customer.getFirst_name());
//        System.out.println(customer.getLast_name());
//        }
}
