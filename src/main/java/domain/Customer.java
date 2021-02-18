package domain;

public class Customer {
    private int customer_no;
    private String first_name;
    private String last_name;

    public Customer(int customer_no, String first_name, String last_name) {
        this.customer_no = customer_no;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Customer(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Customer() {
    }

    public int getCustomer_no() {
        return customer_no;
    }

    public void setCustomer_no(int customer_no) {
        this.customer_no = customer_no;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    }