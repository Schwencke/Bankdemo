package Persistance;

import Persistance.Database;
import domain.Account;
import domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbMapper {

    private Database database;

    public DbMapper(Database database){this.database = database;}

    public List<Customer> viewAllCustomers()
    {
        List<Customer> customerList = new ArrayList<>();
        String sql = "select * from customers";
        try (Connection con = database.connect();
             PreparedStatement ps = con.prepareStatement(sql)){
            ResultSet resultSet = ps.executeQuery();
            System.out.println("\n");
            while (resultSet.next()){
                int customer_no = resultSet.getInt("customer_no");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                customerList.add(new Customer(customer_no,first_name,last_name));
            }
        } catch (SQLException e){
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
        return customerList;
    }

    public void newAccount(int customerID)
    {
        boolean result = false;
        String sql = "insert into accounts (balance, owner_id) values (?,?)";
        try (Connection connection = database.connect()) {
           try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS )) {
            ps.setInt(1, 0);
            ps.setInt(2, customerID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected >= 1)
            {
                result = true;
            }
            }
        } catch (SQLException e){
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
    }
}



   // public Pizza insertPizza(Pizza pizza){
//        boolean result = false;
//        int newId = 0;
//        String sql = "insert into pizza (pizza_no, name, ingredients, price) values (?,?,?,?)";
//        try (Connection connection = database.connect()) {
//            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS )) {
//                ps.setInt(1, pizza.getPizzaNo());
//                ps.setString(2, pizza.getName());
//                ps.setString(3, pizza.getIngredients());
//                ps.setInt(4, pizza.getPrice());
//                int rowsAffected = ps.executeUpdate();
//                if (rowsAffected == 1){
//                    result = true;
//                }
//                ResultSet idResultset = ps.getGeneratedKeys();
//                if (idResultset.next()){
//                    newId = idResultset.getInt(1);
//                    pizza.setPizzaId(newId);
//                } else {
//                    pizza = null;
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return pizza;
//    }




