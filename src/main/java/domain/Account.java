package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Account {

    private int acc_no;
    private int balance;

    public Account(int acc_no, int balance) {
        this.acc_no = acc_no;
        this.balance = balance;
    }

    public Account() {
    }


//    Scanner sc = new Scanner(System.in);
//    private List<Transaction> transactions;
//    private Customer customer;

//    public Account(Customer customer) {
//        this.transactions = new ArrayList<>();
//        this.customer = customer;
//    }

//    public Customer getCustomer() {
//        return customer;
//    }

//    public int getBalance() {
//        int sum = 0;
//        for (Transaction transaction : transactions) {
//            sum += transaction.getAmount();
//        }
//        return sum;
//    }

//    public int withDrawAmount(int amount) {
//        System.out.print("Du hæver " + amount + "kr.");
//        if (amount > getBalance()) {
//            System.out.println("Du har ikke penge nok, din saldo er: " + getBalance() + "kr.");
//        } else {
//            amount = -amount;
//            transactions.add(new Transaction(amount, new Date()));
//            System.out.println("\nDin nye saldo er: " + getBalance() + "kr.");
//        }
//        return getBalance();
//    }
//
//    public int depositAmount(int amount) {
//        System.out.println("Du har sat dette beløb ind på din konto: " + amount + "kr.");
//        transactions.add(new Transaction(amount, new Date()));
//        System.out.println("Nuværende saldo: " + getBalance());
//        return getBalance();
//    }
//
//    public List<Transaction> getTransactions() {
//        for (Transaction transaction : transactions) {
//            System.out.println(transaction.toString());
//        }
//        return transactions;
//    }
}