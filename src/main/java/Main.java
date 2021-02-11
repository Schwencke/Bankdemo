import java.util.List;

public class Main {

    public static void main(String[] args) {

        Customer c1 = new Customer("Jon");
        Account account = new Account(c1);

        int newBalance = account.depositAmount(220);
        System.out.println(String.format("Ny balance: %d", newBalance));

        newBalance = account.withDrawAmount(300);
        System.out.println(String.format("Ny balance: %d", newBalance));

        account.getTransactions();
    }
}
