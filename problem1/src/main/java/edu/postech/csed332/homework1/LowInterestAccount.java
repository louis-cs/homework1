package edu.postech.csed332.homework1;

import static java.lang.Math.pow;

/**
 * An account with a low interest rate. The rate is 0.5% per day.
 * E.g., if the balance is initially 100, after 10 days (on the 11th day)
 * the balance will be 100*(1.005)^10.
 */
class LowInterestAccount implements Account {
    //TODO implement this

    private String owner;
    private double balance;
    private int accountNb;

    public LowInterestAccount(String name, double initial, int AccNb){
        this.owner=name;
        this.balance=initial;
        this.accountNb=AccNb;
    }

    public int getAccountNumber() {
        return this.accountNb;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getOwner() {
        return this.owner;
    }

    public void updateBalance(int elapsedDate) {
        if (elapsedDate>=0){
            this.balance=this.balance*pow(1.005,elapsedDate);
        }
    }

    public void deposit(double amount) {
        this.balance+=amount;
    }

    public void withdraw(double amount) throws IllegalOperationException {
        if (this.balance-amount < 0 ){
            throw new IllegalOperationException("Insufficient funds");
        }
        else{
            this.balance-=amount;
        }
    }
}
