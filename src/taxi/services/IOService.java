package taxi.services;

import static taxi.utils.Constants.OFFERED_SERVICES;
import static taxi.utils.Constants.WELCOME_TEXT;

import java.util.ArrayList;
import java.util.Scanner;

import taxi.models.Wallet;
import taxi.models.user.Customer;

public class IOService {

	private Scanner in;

	public IOService(Scanner in) {
		this.in = in;
	}

	public Customer initializeCustomer() {
		System.out.print(WELCOME_TEXT + '\n');
		System.out.print("Dear customer, welcome to our service!: \n");
		System.out.print("Please input your name: ");
		String name = in.nextLine();
		System.out.print("Please input your location: ");
		int location = in.nextInt();
		System.out.print("Please input your account balance: ");
		Wallet wallet = new Wallet(in.nextDouble());
		in.nextLine();
		System.out.print("Please input one or more destinations (separated by space): ");
		String line = in.nextLine();
		String[] inputs = line.split(" ");
		ArrayList<Integer> destinations = new ArrayList<Integer>();
		for (String input : inputs)
			destinations.add(Integer.parseInt(input));
		return new Customer(name, location, destinations, wallet);
	}

	public int getCustomerChoice() {
		System.out.print(OFFERED_SERVICES + '\n');
		System.out.print("Please pick a service: ");
		int choice = in.nextInt();
		while (choice < 0 || choice > 3) {
			System.out.print("Please enter a valid service:");
			choice = in.nextInt();
		}
		return choice;
	}

	public int getSecondCustomerLocation() {
		System.out.print("Please enter second customer location: ");
		int location = in.nextInt();
		return location;
	}

	public ArrayList<Integer> getUserDestinations() {
		in.nextLine();
		System.out.print("Please input one or more destinations (separated by space): ");
		String line = in.nextLine();
		String[] inputs = line.split(" ");
		ArrayList<Integer> destinations = new ArrayList<Integer>();
		for (String input : inputs)
			destinations.add(Integer.parseInt(input));
		return destinations;
	}
}
