package edu.postech.csed332.homework1;

import java.util.*;

/**
 * Bank manages a collection of accounts. An account number is assigned
 * incrementally from 100000. E.g., the first account has 100000, the second
 * has 100001, etc. There are also functions for finding specific accounts.
 */
public class Bank {

    // TODO: add more fields to implement this class
    // (hint: use Java Collection Framework, including List, Map, Set, etc.)
    ArrayList<Account> accounts;
    private int lastAccNb;
    /**
     * Create a bank. Initially, there is no account.
     */
    public Bank() {
        lastAccNb=100000;
        accounts = new ArrayList<>();
    }

    /**
     * Find an account by a given account number.
     *
     * @param accNum an account number
     * @return the account with number accNum; null if no such account exists
     */
    Account findAccount(int accNum) {
        int ind = accNum - 100000;
        if ((ind >= 0 ) & (ind <= this.accounts.size())){ /*if the account nb is in the list */
            return accounts.get(ind);
        }
        return null;
    }

    /**
     * Find all accounts owned by a given name
     *
     * @param name owner's name
     * @return a list of accounts sorted in ascending order by account number
     */
    ArrayList<Account> findAccountByName(String name) {
        ArrayList<Account> ownedAccounts = new ArrayList<>();
        for (int i=0; i<accounts.size(); i++){
            if (accounts.get(i).getOwner().equals(name)){
                ownedAccounts.add(accounts.get(i));
            }
        }
        return ownedAccounts;
    }

    /**
     * Create a new account and register it to the bank.
     *
     * @param name    owner name
     * @param accType HIGH or LOW
     * @param initial initial balance
     * @return the newly created account; null if not possible
     */
    Account createAccount(String name, ACCTYPE accType, double initial) {
        Account createdAcc=null;
        if (accType.equals(ACCTYPE.HIGH)){
            if (initial<1000) System.out.println("High interest account created with insufficient funds");
            else {
                createdAcc = new HighInterestAccount(name, initial, this.lastAccNb);
                this.accounts.add(createdAcc);
                this.lastAccNb++;
            }
        }
        else if (accType.equals(ACCTYPE.LOW)){
            createdAcc = new LowInterestAccount(name,initial,this.lastAccNb);
            this.accounts.add(createdAcc);
            this.lastAccNb++;
        }
        else System.out.println("Error creating account, wrong account type");
        return createdAcc;
    }

    /**
     * Transfer a given amount of money from src account to dst account.
     *
     * @param src    source account
     * @param dst    destination acount
     * @param amount of money
     * @throws IllegalOperationException if not possible
     */
    void transfer(Account src, Account dst, double amount) throws IllegalOperationException {
        if ((src.getAccountNumber() < 100000) || (src.getAccountNumber() > this.lastAccNb) || (dst.getAccountNumber() < 100000) || (dst.getAccountNumber() > this.lastAccNb)){
            throw new IllegalOperationException("Specified accounts are not in the bank");
        }
        else{
            src.withdraw(amount);
            dst.deposit(amount);
        }
    }
}
