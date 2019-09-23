package edu.postech.csed332.homework1;

/**
 * An account with a high interest rate and a minimum balance.
 * The rate is 1% per day. E.g., if the balance is initially 100,
 * after 10 days (on the 11th day) the balance will be 100*(1.01)^10.
 * The balance should always be greater than or equal to 1000.
 */
class HighInterestAccount implements Account {
    //TODO implement this

    private double initialBalance;
    private String owner;
    private double balance;
    private int accNumber;

    public HighInterestAccount(String name, double initial, int accNb){
        this.owner=name;
        this.initialBalance=initial;
        this.accNumber=accNb;
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
