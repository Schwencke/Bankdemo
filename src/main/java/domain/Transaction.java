package domain;

import java.sql.Timestamp;

public class Transaction {
    private int transactionNr;
    private int account_no;
    private int amount;
    private Timestamp date;

    public Transaction(int account_no, int amount) {
        this.account_no = account_no;
        this.amount = amount;
    }

    public Transaction(int transactionNr, int account_no, int amount, Timestamp date) {
        this.transactionNr = transactionNr;
        this.account_no = account_no;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(int transactionNr, int amount, Timestamp date) {
        this.transactionNr = transactionNr;
        this.amount = amount;
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setTransactionNr(int transactionNr) {
        this.transactionNr = transactionNr;
    }

    public int getTransactionNr() {
        return transactionNr;
    }

    public int getAccount_no() {
        return account_no;
    }

    public int getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return
                "domain.Transaction{" +
                "amount=" + amount +
                ", date=" + date +
                '}';
    }
}
