package edu.postech.csed332.homework1;

/**
 * An account with a low interest rate. The rate is 0.5% per day.
 * E.g., if the balance is initially 100, after 10 days (on the 11th day)
 * the balance will be 100*(1.005)^10.
 */
class LowInterestAccount implements Account {
    //TODO implement this

    private double initialBalance;
    private String owner;
    private double balance;
    private int accountNb;

    public LowInterestAccount(String name, double initial, int AccNb){
        this.owner=name;
        this.initialBalance=initial;
        this.accountNb=AccNb;
    }

    public int getAccountNumber() {
        //TODO implement this
        return 0;
    }

    public double getBalance() {
        //TODO implement this
        return 0;
    }

    public String getOwner() {
        //TODO implement this
        return null;
    }

    public void updateBalance(int elapsedDate) {
        //TODO implement this
    }

    public void deposit(double amount) {
        //TODO implement this
    }

    public void withdraw(double amount) throws IllegalOperationException {
        //TODO implement this
    }
}