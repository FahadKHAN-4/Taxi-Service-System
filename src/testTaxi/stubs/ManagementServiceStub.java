package testTaxi.stubs;

import java.util.ArrayList;
import java.util.Arrays;

import taxi.algorithm.ShortestPath;
import taxi.models.Taxi;
import taxi.models.Wallet;
import taxi.models.taxitype.ComfortTaxiType;
import taxi.models.user.Customer;
import taxi.models.user.Driver;
import taxi.services.ManagementService;

public class ManagementServiceStub extends ManagementService {
	private int driverLocation;

	public ManagementServiceStub() {
		super();
		this.driverLocation = -1;
	}

	public ManagementServiceStub(int driverLocation) {
		super();
		this.driverLocation = driverLocation;
	}

	@Override
	public ArrayList<Customer> getOnPathCustomers(Customer customer, ShortestPath shortestPath) {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		if (customer.getName() == "")
			return customers;
		customers.add(new Customer("Sema", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 3, 5, 7 })),
				new Wallet(Double.MAX_VALUE)));
		customers.add(new Customer("Yrys", 3, new ArrayList<Integer>(Arrays.asList(new Integer[] { 7 })),
				new Wallet(Double.MAX_VALUE)));
		return customers;
	}

	@Override
	public Driver getClosestDriver(int location, ShortestPath shortestPath) {
		int l = driverLocation == -1 ? location : driverLocation;
		return new Driver("", l, new Taxi("", new ComfortTaxiType()));
	}
}
