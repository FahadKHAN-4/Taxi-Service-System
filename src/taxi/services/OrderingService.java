package taxi.services;

import java.util.ArrayList;

import taxi.algorithm.ShortestPath;
import taxi.exceptions.ExCustomerFar;
import taxi.exceptions.ExCustomerRatingLow;
import taxi.exceptions.ExDestinationFar;
import taxi.exceptions.ExInFeasibleOrder;
import taxi.exceptions.ExInvalidNumOfDestination;
import taxi.exceptions.ExTaxiFar;
import taxi.exceptions.NoCustomerOnPathException;
import taxi.models.Receipt;
import taxi.models.Taxi;
import taxi.models.user.Customer;
import taxi.models.user.Driver;

public class OrderingService {
	public Receipt orderMultidestinationTaxi(Customer c, ShortestPath shortestPath, ManagementService service)
			throws ExInvalidNumOfDestination, ExTaxiFar, ExDestinationFar, ExCustomerRatingLow {

		Driver driver = service.getClosestDriver(c.getLocation(), shortestPath);
		Taxi taxi = driver.getTaxi();
		int currentLocation = c.getLocation();

		System.out.print("\nCalculating the optimal route...\n\n");
		double shortestPathLength = 0;

		double closest_taxi_distance = shortestPath.getShortestPath(driver.getLocation());

		ArrayList<Integer> destinations = c.getDestinations();
		if (destinations.size() > 5 || destinations.size() < 1)
			throw new ExInvalidNumOfDestination();
		if (c.getRating() < 1)
			throw new ExCustomerRatingLow();
		if (closest_taxi_distance > 50)
			throw new ExTaxiFar();

		for (int destination : destinations) {
			shortestPath.setSource(currentLocation);
			shortestPathLength += shortestPath.getShortestPath(destination);
			shortestPath.printSolution(destination);
			currentLocation = destination;
		}

		if (shortestPathLength > 100)
			throw new ExDestinationFar();

		c.setLocation(currentLocation);
		driver.setLocation(currentLocation);
		return Receipt.generateReceipt(shortestPathLength, taxi.getTaxiType(), c);
	}

	public Receipt shareTaxi(Customer c1, Customer c2, ShortestPath shortestPath, ManagementService service)
			throws ExInvalidNumOfDestination, ExDestinationFar, ExTaxiFar, ExCustomerFar, ExCustomerRatingLow,
			ExInFeasibleOrder {

		Driver driver = service.getClosestDriver(c1.getLocation(), shortestPath);
		Taxi taxi = driver.getTaxi();
		System.out.print("\nCalculating the optimal route...\n\n");

		ArrayList<Integer> destinations = c1.getDestinations();
		if (destinations.size() < 1 || destinations.size() > 5)
			throw new ExInvalidNumOfDestination();

		double c2_to_destination = shortestPath.getShortestPath(destinations.get(destinations.size() - 1));
		shortestPath.setSource(c1.getLocation());
		double closest_taxi_distance = shortestPath.getShortestPath(driver.getLocation());
		double c1_to_c2_distance = shortestPath.getShortestPath(c2.getLocation());
		shortestPath.printSolution(c2.getLocation());

		if (c1.getRating() < 1 || c2.getRating() < 1)
			throw new ExCustomerRatingLow();
		if (c1.getLocation() == destinations.get(0) || c2.getLocation() == destinations.get(0))
			throw new ExInFeasibleOrder();
		if (closest_taxi_distance > 50)
			throw new ExTaxiFar();
		if (c2_to_destination > 100 || c2_to_destination == 0)
			throw new ExDestinationFar();
		if (c1_to_c2_distance > 50)
			throw new ExCustomerFar();

		int currentLocation = c2.getLocation();
		double total_distance = c1_to_c2_distance;
		for (int destination : c1.getDestinations()) {
			total_distance += shortestPath.getShortestPath(destination);
			shortestPath.printSolution(destination);
			currentLocation = destination;
		}
		c1.setLocation(currentLocation);
		driver.setLocation(currentLocation);
		return Receipt.generateShareReceipt(total_distance, taxi.getTaxiType(), c1, c2);
	}

	public Receipt customerPickup(Customer customer, ShortestPath shortestPath, ShortestPath pickupPath,
			ManagementService service) throws NoCustomerOnPathException, ExInvalidNumOfDestination {
		ArrayList<Integer> destinations = customer.getDestinations();
		if (destinations.size() < 1)
			throw new ExInvalidNumOfDestination();

		Driver driver = service.getClosestDriver(customer.getLocation(), shortestPath);

		System.out.print("\nCalculating the optimal route...\n\n");

		int currentLocation = customer.getLocation();
		ArrayList<Customer> customersOnPath = service.getOnPathCustomers(customer, shortestPath);
		if (customersOnPath.size() == 0)
			throw new NoCustomerOnPathException();

		double distanceTravelledByPickups = 0;
		for (Customer pickupCustomer : customersOnPath) {
			pickupPath.setSource(pickupCustomer.getLocation());
			for (int destination : pickupCustomer.getDestinations()) {
				distanceTravelledByPickups += pickupPath.getShortestPath(destination);
				pickupPath.setSource(destination);
			}
		}

		int distance = 0;
		for (int destination : destinations) {
			shortestPath.setSource(currentLocation);
			distance += shortestPath.getShortestPath(destination);
			shortestPath.printSolution(destination);
			currentLocation = destination;
		}
		return Receipt.generateShareReceipt(distanceTravelledByPickups + distance, distance,
				driver.getTaxi().getTaxiType(), customer);
	}
}
