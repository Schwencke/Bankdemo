import Persistance.Database;
import Persistance.DbMapper;
import domain.Account;
import domain.Customer;

import java.util.List;

public class Main {



    public static void main(String[] args) {

         final String USER = "bankuser";
         final String PASSWORD = "Bank123";
         final String URL = "jdbc:mysql://localhost:3306/bank?serverTimezone=CET&useSSL=false";

         Database database = new Database(USER,PASSWORD,URL);
         DbMapper dbMapper = new DbMapper(database);

         List<Customer> customerList = dbMapper.viewAllCustomers();
        for (Customer customer : customerList) {
            System.out.println(customer.getCustomer_no());
            System.out.println(customer.getFirst_name());
            System.out.println(customer.getLast_name());

        }

    }
}
