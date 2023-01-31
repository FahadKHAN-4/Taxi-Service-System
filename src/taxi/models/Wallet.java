package taxi.models;

import taxi.exceptions.NotEnoughBalanceException;

public class Wallet {
    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    public void topUpBalance(double amount) {
        balance += amount;
    }

    public void payFromBalance(double amount) throws NotEnoughBalanceException {
        if (balance < amount) {
            throw new NotEnoughBalanceException("You do not have enough money on your balance!");
        } else {
            balance -= amount;
        }
    }
    
    public double getBalance() {
    	return this.balance;
    }

}
