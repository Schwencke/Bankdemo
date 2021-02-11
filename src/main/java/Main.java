import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Customer c1 = new Customer("Jon");
        Account account = new Account(c1);
        account.depositAmount(100);
        account.depositAmount(150);
        account.depositAmount(180);
        account.depositAmount(800);
        account.depositAmount(313);
        account.depositAmount(666);
        account.withDrawAmount(50);
        account.withDrawAmount(100);
        account.withDrawAmount(150);
        account.withDrawAmount(200);
        account.getBalance();
        System.out.println("Liste over alle transaktioner");
        account.getTransactions();

       // account.getTransactions();
    }
}
