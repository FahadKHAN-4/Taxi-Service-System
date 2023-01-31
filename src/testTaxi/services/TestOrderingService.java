package testTaxi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import taxi.algorithm.ShortestPath;
import taxi.exceptions.ExCustomerFar;
import taxi.exceptions.ExCustomerRatingLow;
import taxi.exceptions.ExDestinationFar;
import taxi.exceptions.ExInFeasibleOrder;
import taxi.exceptions.ExInvalidNumOfDestination;
import taxi.exceptions.ExTaxiFar;
import taxi.exceptions.NoCustomerOnPathException;
import taxi.models.Rating;
import taxi.models.Receipt;
import taxi.models.Wallet;
import taxi.models.user.Customer;
import taxi.services.ManagementService;
import taxi.services.OrderingService;
import testTaxi.stubs.ManagementServiceStub;
import testTaxi.stubs.ShortestPathStub;

public class TestOrderingService {
	@Test
	public void testCustomerPickupA() {
		ShortestPath shortestPath1 = new ShortestPathStub();
		ShortestPath shortestPath2 = new ShortestPathStub();
		Customer customer = new Customer("Sema", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), null);
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExInvalidNumOfDestination.class,
				() -> orderingService.customerPickup(customer, shortestPath1, shortestPath2, managementService));
	}

	@Test
	public void testCustomerPickupB() {
		ShortestPath shortestPath1 = new ShortestPathStub();
		ShortestPath shortestPath2 = new ShortestPathStub();
		Customer customer = new Customer("", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 1 })), null);
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(NoCustomerOnPathException.class,
				() -> orderingService.customerPickup(customer, shortestPath1, shortestPath2, managementService));
	}

	@Test
	public void testCustomerPickupC() throws NoCustomerOnPathException, ExInvalidNumOfDestination {
		ShortestPath shortestPath1 = new ShortestPathStub();
		ShortestPath shortestPath2 = new ShortestPathStub();
		Customer customer = new Customer("Tolik", 1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 5, 7, 6, 8 })), new Wallet(1000));
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		Receipt receipt = orderingService.customerPickup(customer, shortestPath1, shortestPath2, managementService);
		assertEquals(receipt.getPrice(), 448.3, 1);
	}

	// Invalid test: destination < 1
	@Test
	public void testorderMultidestinationTaxi_A()
			throws ExInvalidNumOfDestination, ExTaxiFar, ExDestinationFar, ExCustomerRatingLow {
		ShortestPath shortestPath = new ShortestPathStub();
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), null);
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExInvalidNumOfDestination.class,
				() -> orderingService.orderMultidestinationTaxi(customer, shortestPath, managementService));
	}

	// Invalid test: destination > 5
	@Test
	public void testorderMultidestinationTaxi_B()
			throws ExInvalidNumOfDestination, ExTaxiFar, ExDestinationFar, ExCustomerRatingLow {
		ShortestPath shortestPath = new ShortestPathStub();
		Customer customer = new Customer("Tolik", 1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 5, 7, 6, 8, 10, 11 })), null);
		ManagementService managementService = new ManagementServiceStub();

		OrderingService orderingService = new OrderingService();

		assertThrows(ExInvalidNumOfDestination.class,
				() -> orderingService.orderMultidestinationTaxi(customer, shortestPath, managementService));
	}

	// Invalid test: rating < 1
	@Test
	public void testorderMultidestinationTaxi_C()
			throws ExInvalidNumOfDestination, ExTaxiFar, ExDestinationFar, ExCustomerRatingLow {
		ShortestPath shortestPath = new ShortestPathStub();
		Rating stubRating1 = new Rating(0.5, 0);
		Customer customer = new Customer("Tolik", 1, stubRating1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 5, 7, 6, 8, 10 })), null);
		ManagementService managementService = new ManagementServiceStub();

		OrderingService orderingService = new OrderingService();

		assertThrows(ExCustomerRatingLow.class,
				() -> orderingService.orderMultidestinationTaxi(customer, shortestPath, managementService));
	}

	// Invalid test: closest_taxi_distance > 50

	@Test
	public void testorderMultidestinationTaxi_D()
			throws ExInvalidNumOfDestination, ExTaxiFar, ExDestinationFar, ExCustomerRatingLow {
		ShortestPath shortestPath = new ShortestPathStub();
		ManagementService managementService = new ManagementServiceStub();
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 2, 3 })),
				null);
		OrderingService orderingService = new OrderingService();
		assertThrows(ExTaxiFar.class,
				() -> orderingService.orderMultidestinationTaxi(customer, shortestPath, managementService));
	}

	// Invalid test: shortestpathlength > 100
	@Test
	public void testorderMultidestinationTaxi_E() throws ExTaxiFar {
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 3 })), null);
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExDestinationFar.class,
				() -> orderingService.orderMultidestinationTaxi(customer, shortestPath, managementService));
	}

	// Valid test: Destination == 1.
	@Test
	public void testorderMultidestinationTaxi_F()
			throws ExInvalidNumOfDestination, ExTaxiFar, ExDestinationFar, ExCustomerRatingLow {
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 5 })),
				new Wallet(1000));
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		Receipt ActualReceipt = orderingService.orderMultidestinationTaxi(customer, shortestPath,
				managementService);
		Receipt ExpectedReceipt = new Receipt(720, 60, 0.75);

		assertEquals(ExpectedReceipt.getPrice(), ActualReceipt.getPrice(), 0.0001);
		assertEquals(ExpectedReceipt.getDistance(), ActualReceipt.getDistance(), 0.0001);
		assertEquals(ExpectedReceipt.getTime(), ActualReceipt.getTime(), 0.0001);

	}

	// Valid test: Destination == 5.
	@Test
	public void testorderMultidestinationTaxi_G()
			throws ExInvalidNumOfDestination, ExTaxiFar, ExDestinationFar, ExCustomerRatingLow {
		Customer customer = new Customer("Tolik", 1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 7, 8, 9, 10, 11 })), new Wallet(1000));
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();

		Receipt ActualReceipt = orderingService.orderMultidestinationTaxi(customer, shortestPath,
				managementService);
		Receipt ExpectedReceipt = new Receipt(600, 50, 0.625);

		assertEquals(ExpectedReceipt.getPrice(), ActualReceipt.getPrice(), 0.0001);
		assertEquals(ExpectedReceipt.getDistance(), ActualReceipt.getDistance(), 0.0001);
		assertEquals(ExpectedReceipt.getTime(), ActualReceipt.getTime(), 0.0001);

	}

	// Valid test: Destination == 5, closest_taxi_distance == 50.
	@Test
	public void testorderMultidestinationTaxi_H()
			throws ExInvalidNumOfDestination, ExTaxiFar, ExDestinationFar, ExCustomerRatingLow {
		Customer customer = new Customer("Tolik", 1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 7, 8, 9, 10, 4 })), new Wallet(1000));
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();

		Receipt ActualReceipt = orderingService.orderMultidestinationTaxi(customer, shortestPath,
				managementService);
		Receipt ExpectedReceipt = new Receipt(1080, 90, 1.125);

		assertEquals(ExpectedReceipt.getPrice(), ActualReceipt.getPrice(), 0.0001);
		assertEquals(ExpectedReceipt.getDistance(), ActualReceipt.getDistance(), 0.0001);
		assertEquals(ExpectedReceipt.getTime(), ActualReceipt.getTime(), 0.0001);

	}

	// Valid test: Destination == 5, closest_taxi_distance == 50, shortestPathLength
	// == 100.
	@Test
	public void testorderMultidestinationTaxi_I()
			throws ExInvalidNumOfDestination, ExTaxiFar, ExDestinationFar, ExCustomerRatingLow {
		Customer customer = new Customer("Tolik", 1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 8, 9, 10, 14, 12 })), new Wallet(1000));
		OrderingService orderingService = new OrderingService();
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub();

		Receipt ActualReceipt = orderingService.orderMultidestinationTaxi(customer, shortestPath,
				managementService);
		Receipt ExpectedReceipt = new Receipt(1200, 100, 1.25);

		assertEquals(ExpectedReceipt.getPrice(), ActualReceipt.getPrice(), 0.0001);
		assertEquals(ExpectedReceipt.getDistance(), ActualReceipt.getDistance(), 0.0001);
		assertEquals(ExpectedReceipt.getTime(), ActualReceipt.getTime(), 0.0001);

	}

	// Invalid test: destination < 1
	@Test
	public void testorderMultidestinationTaxi_J()
			throws ExInvalidNumOfDestination, ExTaxiFar, ExDestinationFar, ExCustomerRatingLow {
		ShortestPath shortestPath = new ShortestPathStub();
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), null);
		ManagementService managementService = new ManagementServiceStub();

		OrderingService orderingService = new OrderingService();

		assertThrows(ExInvalidNumOfDestination.class,
				() -> orderingService.orderMultidestinationTaxi(customer, shortestPath, managementService));
	}

	// number of destination == 0
	@Test
	public void testShareTaxiA() throws ExInvalidNumOfDestination {
		ShortestPath shortestPath = new ShortestPathStub();
		Customer customer = new Customer("Tolik", 1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] {})), null);
		Customer customer2 = new Customer("John", 1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] {})), null);
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExInvalidNumOfDestination.class,
				() -> orderingService.shareTaxi(customer, customer2, shortestPath, managementService));
	}

	// number of destination > 5
	@Test
	public void testShareTaxiB() throws ExInvalidNumOfDestination {
		ShortestPath shortestPath = new ShortestPathStub();
		Customer customer = new Customer("Tolik", 1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 2, 3, 5, 6, 8, 7 })), null);
		Customer customer2 = new Customer("John", 1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] {})), null);
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExInvalidNumOfDestination.class,
				() -> orderingService.shareTaxi(customer, customer2, shortestPath, managementService));
	}

	// Both customer rating < 1
	@Test
	public void testShareTaxiC() throws ExCustomerRatingLow {
		Rating stubRating1 = new Rating(0.5);
		Rating stubRating2 = new Rating(0.8);
		ShortestPath shortestPath1 = new ShortestPathStub();
		Customer customer = new Customer("Tolik", 1, stubRating1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })), null);
		Customer customer2 = new Customer("Tolik", 1, stubRating2,
				new ArrayList<Integer>(Arrays.asList(new Integer[] {})), null);
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExCustomerRatingLow.class,
				() -> orderingService.shareTaxi(customer, customer2, shortestPath1, managementService));
	}

	// C1 rating < 1
	@Test
	public void testShareTaxiD() throws ExCustomerRatingLow {
		Rating stubRating1 = new Rating(0.5);
		Rating stubRating2 = new Rating();
		ShortestPath shortestPath1 = new ShortestPathStub();
		Customer customer = new Customer("Tolik", 1, stubRating1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })), null);
		Customer customer2 = new Customer("Tolik", 1, stubRating2,
				new ArrayList<Integer>(Arrays.asList(new Integer[] {})), null);
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExCustomerRatingLow.class,
				() -> orderingService.shareTaxi(customer, customer2, shortestPath1, managementService));
	}

	// C2 rating < 1
	@Test
	public void testShareTaxiE() throws ExCustomerRatingLow {
		Rating stubRating1 = new Rating();
		Rating stubRating2 = new Rating(0.8);
		ShortestPath shortestPath1 = new ShortestPathStub();
		Customer customer = new Customer("Tolik", 1, stubRating1,
				new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })), null);
		Customer customer2 = new Customer("Tolik", 1, stubRating2,
				new ArrayList<Integer>(Arrays.asList(new Integer[] {})), null);
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExCustomerRatingLow.class,
				() -> orderingService.shareTaxi(customer, customer2, shortestPath1, managementService));
	}

	// Taxi distance > 50
	@Test
	public void testShareTaxiF() throws ExTaxiFar {
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 5 })), null);
		Customer customer2 = new Customer("John", 2, new ArrayList<Integer>(Arrays.asList(new Integer[] { 5 })), null);
		ShortestPath shortestPath = new ShortestPathStub();
		ManagementService managementService = new ManagementServiceStub(3);
		OrderingService orderingService = new OrderingService();
		assertThrows(ExTaxiFar.class,
				() -> orderingService.shareTaxi(customer, customer2, shortestPath, managementService));
	}

	// C2_to_destination > 100
	@Test
	public void testShareTaxiG() throws ExDestinationFar {
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 3 })), null);
		Customer customer2 = new Customer("John", 2, new ArrayList<Integer>(Arrays.asList(new Integer[] { 3 })), null);
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService stubManagementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExDestinationFar.class,
				() -> orderingService.shareTaxi(customer, customer2, shortestPath, stubManagementService));
	}

	// C1 to C2 distance > 50
	@Test
	public void testShareTaxiH() throws ExCustomerFar {
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })), null);
		Customer customer2 = new Customer("John", 5, new ArrayList<Integer>(Arrays.asList(new Integer[] {})), null);
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExCustomerFar.class,
				() -> orderingService.shareTaxi(customer, customer2, shortestPath, managementService));
	}

	// return right receipt
	@Test
	public void testShareTaxiI() throws ExInvalidNumOfDestination, ExDestinationFar, ExTaxiFar, ExCustomerFar,
			ExCustomerRatingLow, ExInFeasibleOrder {
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })),
				new Wallet(1000));
		Customer customer2 = new Customer("John", 10, new ArrayList<Integer>(Arrays.asList(new Integer[] {})),
				new Wallet(1000));
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();

		Receipt receipt = orderingService.shareTaxi(customer, customer2, shortestPath, managementService);
		Receipt rightReceipt = new Receipt(262.5, 35.0, 0.4375);

		assertEquals(rightReceipt.getPrice(), receipt.getPrice(), 0.0001);
		assertEquals(rightReceipt.getDistance(), receipt.getDistance(), 0.0001);
		assertEquals(rightReceipt.getTime(), receipt.getTime(), 0.0001);
	}

	// c1 and c2 are on the same location
	@Test
	public void testShareTaxiJ()
			throws ExInvalidNumOfDestination, ExDestinationFar, ExTaxiFar, ExCustomerFar, ExCustomerRatingLow,
			ExInFeasibleOrder {
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })),
				new Wallet(1000));
		Customer customer2 = new Customer("John", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] {})),
				new Wallet(1000));
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub(6);
		OrderingService orderingService = new OrderingService();

		Receipt receipt = orderingService.shareTaxi(customer, customer2, shortestPath, managementService);
		Receipt rightReceipt = new Receipt(187.5, 25.0, 0.3125);

		assertEquals(rightReceipt.getPrice(), receipt.getPrice(), 0.0001);
		assertEquals(rightReceipt.getDistance(), receipt.getDistance(), 0.0001);
		assertEquals(rightReceipt.getTime(), receipt.getTime(), 0.0001);
	}

	// C1 and taxi are on the same location
	@Test
	public void testShareTaxiK()
			throws ExInvalidNumOfDestination, ExDestinationFar, ExTaxiFar, ExCustomerFar, ExCustomerRatingLow,
			ExInFeasibleOrder {
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })),
				new Wallet(1000));
		Customer customer2 = new Customer("John", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] {})),
				new Wallet(1000));
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();

		Receipt receipt = orderingService.shareTaxi(customer, customer2, shortestPath, managementService);
		Receipt rightReceipt = new Receipt(187.5, 25.0, 0.3125);

		assertEquals(rightReceipt.getPrice(), receipt.getPrice(), 0.0001);
		assertEquals(rightReceipt.getDistance(), receipt.getDistance(), 0.0001);
		assertEquals(rightReceipt.getTime(), receipt.getTime(), 0.0001);
	}

	// c1 and taxi are on the same location, c2 and c1 are not.
	@Test
	public void testShareTaxiL()
			throws ExInvalidNumOfDestination, ExDestinationFar, ExTaxiFar, ExCustomerFar, ExCustomerRatingLow,
			ExInFeasibleOrder {
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })),
				new Wallet(1000));
		Customer customer2 = new Customer("John", 9, new ArrayList<Integer>(Arrays.asList(new Integer[] {})),
				new Wallet(1000));
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub(customer.getLocation());
		OrderingService orderingService = new OrderingService();

		Receipt receipt = orderingService.shareTaxi(customer, customer2, shortestPath, managementService);
		Receipt rightReceipt = new Receipt(262.5, 35.0, 0.4375);

		assertEquals(rightReceipt.getPrice(), receipt.getPrice(), 0.0001);
		assertEquals(rightReceipt.getDistance(), receipt.getDistance(), 0.0001);
		assertEquals(rightReceipt.getTime(), receipt.getTime(), 0.0001);
	}

	// C2_to_destination == 0
	@Test
	public void testShareTaxiN() throws ExDestinationFar {
		Customer customer = new Customer("Tolik", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 2 })), null);
		Customer customer2 = new Customer("John", 2, new ArrayList<Integer>(Arrays.asList(new Integer[] { 6 })), null);
		ShortestPath shortestPath = new ShortestPathStub(customer.getLocation());
		ManagementService managementService = new ManagementServiceStub();
		OrderingService orderingService = new OrderingService();
		assertThrows(ExInFeasibleOrder.class,
				() -> orderingService.shareTaxi(customer, customer2, shortestPath, managementService));
	}
}
