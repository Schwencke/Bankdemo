package domain;

import java.util.Date;

public class Transaction {

    private int amount;
    private Date date;
    private int accNo;

    Account account;

    public Transaction(int amount, Date date) {
        this.amount = amount;
        this.date = date;
        this.accNo=account.getAccNo();
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
