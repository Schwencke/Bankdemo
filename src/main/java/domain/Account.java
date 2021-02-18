package domain;


public class Account {
    private Customer customer;
    private int accNo;
    private int accBalance;

    public Account(int accNo) {
        this.accNo = accNo;
    }

    public Account(int accNo, int balance) {
        this.accNo = accNo;
        this.accBalance = balance;
    }

    public int getAccNo() {
        return accNo;
    }
    @Override
    public String toString() {
        return "\n" + "customer= " + customer + "\n" + "accNo= " + accNo + "\n" + "accBalance= " + accBalance;
    }

    public Account() {
    }
}