package taxi;

import static taxi.utils.Constants.NODES;
import static taxi.utils.Constants.SEED;

import java.util.Scanner;

import taxi.algorithm.ShortestPath;
import taxi.exceptions.ExCustomerFar;
import taxi.exceptions.ExCustomerRatingLow;
import taxi.exceptions.ExDestinationFar;
import taxi.exceptions.ExInFeasibleOrder;
import taxi.exceptions.ExInvalidNumOfDestination;
import taxi.exceptions.ExTaxiFar;
import taxi.exceptions.NoCustomerOnPathException;
import taxi.map.Graph;
import taxi.models.Receipt;
import taxi.models.Wallet;
import taxi.models.user.Customer;
import taxi.services.IOService;
import taxi.services.ManagementService;
import taxi.services.OrderingService;

public class Main {

	public static void main(String[] args) {
		Scanner in = args.length == 0 ? new Scanner(System.in) : new Scanner(args[0]);
		IOService io = new IOService(in);
		OrderingService orderingService = new OrderingService();
		Customer customer = io.initializeCustomer();
		ManagementService managementService = new ManagementService();
		Graph map = new Graph(NODES, SEED);
		int choice = io.getCustomerChoice();
		while (choice != 0) {
			switch (choice) {
				case 1:
					try {
						ShortestPath shortestPath = new ShortestPath(customer.getLocation(), map);
						Receipt receipt = orderingService.orderMultidestinationTaxi(customer, shortestPath, managementService);
						System.out.print(receipt);
						System.out.print("\n");
					} catch (ExInvalidNumOfDestination | ExTaxiFar | ExDestinationFar | ExCustomerRatingLow e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					int secondLocation = io.getSecondCustomerLocation();
					try {
						ShortestPath shortestPath = new ShortestPath(secondLocation, map);
						Customer anotherCustomer = new Customer("", secondLocation,
								customer.getDestinations(),
								new Wallet(Integer.MAX_VALUE));
						Receipt receipt = orderingService.shareTaxi(customer, anotherCustomer, shortestPath,
								managementService);
						System.out.print(receipt);
						System.out.print("\n");
					} catch (ExInvalidNumOfDestination | ExDestinationFar | ExTaxiFar | ExCustomerFar | ExCustomerRatingLow
							| ExInFeasibleOrder e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						ShortestPath shortestPath = new ShortestPath(customer.getLocation(), map);
						ShortestPath shortestPathPickup = new ShortestPath(map);
						Receipt receipt = orderingService.customerPickup(customer, shortestPath,
								shortestPathPickup, managementService);
						System.out.print(receipt);
						System.out.print("\n");
					} catch (NoCustomerOnPathException | ExInvalidNumOfDestination e) {
						System.out.println(e.getMessage());
					}
			}
			customer.setDestinations(io.getUserDestinations());
			choice = io.getCustomerChoice();
		}
		System.out.print("\nThank you for choosing us! See you soon!\n");
	}
}
