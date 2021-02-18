package Persistance;

import Persistance.Database;
import domain.Account;
import domain.Customer;
import domain.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbMapper {

    private Database database;

    public DbMapper(Database database) {
        this.database = database;
    }

    public List<Customer> viewAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "select * from customers";
        try (Connection con = database.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int customer_no = resultSet.getInt("customer_no");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                customerList.add(new Customer(customer_no, first_name, last_name));
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
        return customerList;
    }


    public List<Account> viewAllCustomersWithBalance() {
        List<Account> accountList = new ArrayList<>();
        String sql = "select * from bank.accounts where balance>=0 ";
        try (Connection con = database.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            System.out.println("\n");
            while (resultSet.next()) {
                int accNo = resultSet.getInt("acc_no");
                int balance = resultSet.getInt("balance");
                accountList.add(new Account(accNo, balance));
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
        return accountList;
    }

//    public Pizza insertPizza(Pizza pizza){
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

    public Transaction addTransaction(Transaction transaction) {
        boolean updated = false;
        int newId = 0;
        String sql = "insert into transactions (account_no, amount) values(?,?)";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, transaction.getAccount_no());
                ps.setInt(2, transaction.getAmount());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    updated = true;
                }
                ResultSet resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    newId = resultSet.getInt(1);
                    transaction.setTransactionNr(newId);
                } else {
                    transaction = null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return transaction;
    }

    public List<Transaction> getTransactionForAccNo(int kontoNr) {
        List<Transaction> transactionlist = new ArrayList<>();
        String sql = "select * from transactions where account_no =" + kontoNr;
        try (Connection con = database.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int transaction_id = resultSet.getInt("transaction_id");
                int amount = resultSet.getInt("amount");
                Timestamp date = resultSet.getTimestamp("transaction_date");
                transactionlist.add(new Transaction(transaction_id, amount, date));
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
        return transactionlist;
    }

    public int getAccountBalance(int kontoNr) {
        int sum = 0;
        String sql = "select sum(amount) AS result from transactions where account_no = " + kontoNr;
        try (Connection con = database.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
        return sum;
    }

    public void updateAccountBalance(int kontoNr) {
        String sql = "update accounts set balance =? where acc_no =?";
        try (Connection con = database.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, getAccountBalance(kontoNr));
            ps.setInt(2, kontoNr);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
    }

    public int newAccount(int customerID) {
        String sql = "insert into accounts (balance, owner_id) values (?,?)";
        int newAccNo = 0;
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, 0);
                ps.setInt(2, customerID);
                ps.executeUpdate();
                ResultSet resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    newAccNo = resultSet.getInt(1);
                } else {
                    newAccNo = 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
        return newAccNo;
    }

    public Customer addCustomer(Customer customer) {
        boolean updated = false;
        int newId = 0;
        String sql = "insert into customers (first_name, last_name) values(?,?)";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, customer.getFirst_name());
                ps.setString(2, customer.getLast_name());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    updated = true;
                }
                ResultSet resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    newId = resultSet.getInt(1);
                    customer.setCustomer_no(newId);
                } else {
                    customer = null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
   }
    public void deleteAccount(int a_no) {
        boolean result = false;
        String sql = "delete from accounts where acc_no = ?";
        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, a_no);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
    }

    public boolean deleteCustomer(int c_no) {
        boolean result = false;
        String sql = "delete from customers where customer_no = ?";
        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, c_no);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
        return result;
    }
    public void deleteTESTCustomers() {
        boolean result = false;
        String sql = "delete FROM customers";
        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
    }
    public void deleteTESTAccounts() {
        boolean result = false;
        String sql = "delete FROM accounts";
        try (Connection connection = database.connect()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Fejl i connection til databasen");
            e.printStackTrace();
        }
    }
}





