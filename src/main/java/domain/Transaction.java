package domain;

import java.sql.Timestamp;
import java.util.Date;

public class Transaction {
    private int transactionNr;
    private int account_no;
    private int amount;
    private Date date;
    private int accNo;

    Account account;
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

    public void setTransactionNr(int transactionNr) {
        this.transactionNr = transactionNr;
    }

    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(Timestamp date) {
        this.date = date;
        this.accNo=account.getAccNo();
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

    public Date getDate() {
        return date;
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
