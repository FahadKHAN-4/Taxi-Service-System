package taxi.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import taxi.algorithm.ShortestPath;
import taxi.models.Rating;
import taxi.models.Taxi;
import taxi.models.Wallet;
import taxi.models.taxitype.ComfortTaxiType;
import taxi.models.taxitype.PremiumTaxiType;
import taxi.models.taxitype.TaxiType;
import taxi.models.user.Customer;
import taxi.models.user.Driver;

public class ManagementService {
	private ArrayList<Driver> drivers;
	private ArrayList<Customer> customers;

	public ManagementService() {
		customers = new ArrayList<Customer>();
		drivers = new ArrayList<Driver>();
		try {
			String absPath = new File("").getAbsolutePath();
			String driverFilepath = absPath + "/src/taxi/services/drivers.txt";
			String customerFilepath = absPath + "/src/taxi/services/customers.txt";
			readDrivers(new FileReader(driverFilepath));
			readCustomers(new FileReader(customerFilepath));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	public ArrayList<Driver> getDrivers() {
		return drivers;
	}

	public void readDrivers(Reader r) throws IOException {
		drivers.clear();
		BufferedReader br = new BufferedReader(r);
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;

			// format: name, location, rating, rides, model, type
			String[] inputs = line.split(", ");
			if (inputs.length != 6)
				throw new IOException();
			String name = inputs[0];
			int location = Integer.parseInt(inputs[1]);
			double averageRating = Double.parseDouble(inputs[2]);
			int rides = Integer.parseInt(inputs[3]);
			Rating rating = new Rating(averageRating, rides);
			String model = inputs[4];
			TaxiType taxiType = null;
			switch (inputs[5]) {
				case "comfort":
					taxiType = new ComfortTaxiType();
				case "premium":
					taxiType = new PremiumTaxiType();
			}
			Taxi taxi = new Taxi(model, taxiType);
			drivers.add(new Driver(name, location, rating, taxi));
		}
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void readCustomers(Reader r) throws IOException {
		customers.clear();
		BufferedReader br = new BufferedReader(r);
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;

			// format: name, location, rating, rides, {d1,d2,d3}, balance
			String[] inputs = line.split(", ");
			String name = inputs[0];
			if (inputs.length != 6)
				throw new IOException();
			int location = Integer.parseInt(inputs[1]);
			double averageRating = Double.parseDouble(inputs[2]);
			int rides = Integer.parseInt(inputs[3]);
			Rating rating = new Rating(averageRating, rides);
			ArrayList<Integer> destinations = new ArrayList<Integer>();
			for (String destination : inputs[4].replaceAll("([{}])", "").split(","))
				destinations.add(Integer.parseInt(destination));
			Wallet wallet = new Wallet(Double.parseDouble(inputs[5]));
			customers.add(new Customer(name, location, rating, destinations, wallet));
		}
	}

	public Driver getClosestDriver(int location, ShortestPath shortestPath) {
		Driver driver = null;
		double path = Integer.MAX_VALUE;
		System.out.print("Finding the closest driver...\n\n");
		for (Driver d : drivers) {
			shortestPath.setSource(d.getLocation());
			shortestPath.printSolution(location);
			double distance = shortestPath.getShortestPath(location);
			if (distance < path) {
				path = distance;
				driver = d;
			}
		}
		shortestPath.setSource(location);
		return driver;
	}

	public ArrayList<Customer> getOnPathCustomers(Customer c, ShortestPath shortestPath) {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ArrayList<Integer> destinations = c.getDestinations();
		ArrayList<Integer> visited = new ArrayList<Integer>();
		visited.add(c.getLocation());
		for (int destination : destinations) {
			ArrayList<Integer> path = shortestPath.getPath(destination);
			for (int i = 0; i < path.size(); i++)
				visited.add(path.get(i));
			shortestPath.setSource(destination);
		}
		shortestPath.setSource(c.getLocation());
		for (Customer customer : this.customers) {
			int pickupLocation = visited.indexOf(customer.getLocation());
			if (pickupLocation == -1)
				continue;
			int stops = 0;
			ArrayList<Integer> otherDestinations = customer.getDestinations();
			for (int j = pickupLocation; j < visited.size() && stops < otherDestinations.size(); j++) {
				if (otherDestinations.get(stops) == visited.get(j))
					stops++;
			}
			if (stops == otherDestinations.size())
				customers.add(customer);
		}

		System.out.printf("There are %d customers on your way:\n", customers.size());
		for (Customer customer : customers) {
			ArrayList<Integer> ds = customer.getDestinations();
			System.out.printf("%s %d %d\n", customer.getName(), customer.getLocation(),
					ds.get(ds.size() - 1));
		}
		return customers;
	}
}