package edu.postech.csed332.homework1;

import static java.lang.Math.pow;

/**
 * An account with a high interest rate and a minimum balance.
 * The rate is 1% per day. E.g., if the balance is initially 100,
 * after 10 days (on the 11th day) the balance will be 100*(1.01)^10.
 * The balance should always be greater than or equal to 1000.
 */
class HighInterestAccount implements Account {
    //TODO implement this

    private String owner;
    private double balance;
    private int accNumber;

    public HighInterestAccount(String name, double initial, int accNb){
        this.owner=name;
        this.balance=initial;
        this.accNumber=accNb;
    }

    public int getAccountNumber() {
        return this.accNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getOwner() {
        return this.owner;
    }

    public void updateBalance(int elapsedDate) {
        if (elapsedDate>=0){
            this.balance=this.balance*pow(1.01,elapsedDate);
        }
    }

    public void deposit(double amount) {
        this.balance+=amount;
    }

    public void withdraw(double amount) throws IllegalOperationException {
        if (this.balance-amount < 1000.0){
            throw new IllegalOperationException("Insufficient funds");
        }
        else this.balance-=amount;
    }
}
