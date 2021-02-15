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
            switch (valg){
                case 1:
                    System.out.println("du valgte et");
                break;


            }


            //    List<Customer> customerList = dbMapper.viewAllCustomers();
//    for (Customer customer :customerList) {
//        System.out.println(customer.getCustomer_no());
//        System.out.println(customer.getFirst_name());
//        System.out.println(customer.getLast_name());
//        }
        }
    }

    private void showMainMenu() {
        System.out.println("Hovedmenu:");

    }
}
