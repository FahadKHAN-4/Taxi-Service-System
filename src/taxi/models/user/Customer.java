package taxi.models.user;

import java.util.ArrayList;

import taxi.exceptions.NotEnoughBalanceException;
import taxi.models.Rating;
import taxi.models.Wallet;

public class Customer extends User {
	private Wallet wallet;
	private ArrayList<Integer> destinations;

	public Customer(String name, int location, ArrayList<Integer> destinations, Wallet wallet) {
		super(name, location);
		this.wallet = wallet;
		this.destinations = destinations;
	}

	public Customer(String name, int location, Rating rating, ArrayList<Integer> destinations, Wallet wallet) {
		super(name, location, rating);
		this.wallet = wallet;
		this.destinations = destinations;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public ArrayList<Integer> getDestinations() {
		return destinations;
	}
	
	public void setDestinations(ArrayList<Integer> destinations) {
		this.destinations = destinations;
	}

	public void payTheBill(double amount) {
		try {
			wallet.payFromBalance(amount);
		}
		catch (NotEnoughBalanceException e) {
			System.out.println(e.getMessage());
		}
	}
}
