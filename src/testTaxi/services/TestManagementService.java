package testTaxi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import taxi.algorithm.ShortestPath;
import taxi.models.Wallet;
import taxi.models.user.Customer;
import taxi.services.ManagementService;
import testTaxi.stubs.ShortestPathStub;

public class TestManagementService {
	@Test
	public void testReadDriversA() throws IOException {
		ManagementService service = new ManagementService();
		String data = "Sema, 5, 4.7, 12, Toyota, comfort\n" + "Yrys, 7, 4.2, 75, Lamborgini, premium\n"
				+ "Tolik, 2, 4.1, 13, Mercedes-Benz, comfort";
		Reader r = new StringReader(data);
		service.readDrivers(r);
		assertEquals(service.getDrivers().size(), 3);
	}

	@Test
	public void testReadDriversB() throws IOException {
		ManagementService service = new ManagementService();
		String data = "";
		Reader r = new StringReader(data);
		service.readDrivers(r);
		assertEquals(service.getDrivers().size(), 0);
	}

	@Test
	public void testReadDriversC() {
		ManagementService service = new ManagementService();
		String data = "Unexpected data";
		Reader r = new StringReader(data);
		assertThrows(IOException.class, () -> service.readDrivers(r));
	}

	@Test
	public void testReadDriversD() {
		ManagementService service = new ManagementService();
		String data = "A, B, C, D, E, F";
		Reader r = new StringReader(data);
		assertThrows(NumberFormatException.class, () -> service.readDrivers(r));
	}

	@Test
	public void testReadCustomersA() throws IOException {
		ManagementService service = new ManagementService();
		String data = "Sema, 2, 4.7, 15, {5}, 2000\n" + "Yrys, 9, 4.2, 27, {6,8}, 350\n"
				+ "Tolik, 5, 4.1, 42, {1}, 7000";
		Reader r = new StringReader(data);
		service.readCustomers(r);
		assertEquals(service.getCustomers().size(), 3);
	}

	@Test
	public void testReadCustomersB() throws IOException {
		ManagementService service = new ManagementService();
		String data = "";
		Reader r = new StringReader(data);
		service.readCustomers(r);
		assertEquals(service.getCustomers().size(), 0);
	}

	@Test
	public void testReadCustomersC() {
		ManagementService service = new ManagementService();
		String data = "Unexpected data";
		Reader r = new StringReader(data);
		assertThrows(IOException.class, () -> service.readCustomers(r));
	}

	@Test
	public void testReadCustomersD() {
		ManagementService service = new ManagementService();
		String data = "A, B, C, D, E, F";
		Reader r = new StringReader(data);
		assertThrows(NumberFormatException.class, () -> service.readCustomers(r));
	}

	@Test
	public void testReadCustomersE() throws IOException {
		ManagementService service = new ManagementService();
		String data = "Sema, 2, 4.7, 15, {5,6,8}, 2000\n";
		Reader r = new StringReader(data);
		service.readCustomers(r);
		assertEquals(service.getCustomers().get(0).getDestinations().size(), 3);
	}

	@Test
	public void testGetClosestDriverA() throws IOException {
		ManagementService service = new ManagementService();
		String data = "";
		Reader r = new StringReader(data);
		service.readDrivers(r);
		ShortestPath shortestPath = new ShortestPathStub();
		assertEquals(service.getClosestDriver(0, shortestPath), null);
	}

	@Test
	public void testGetClosestDriverB() throws IOException {
		ManagementService service = new ManagementService();
		String data = "Sema, 5, 4.7, 12, Toyota, comfort\n" + "Yrys, 7, 4.2, 75, Lamborgini, premium\n"
				+ "Tolik, 2, 4.1, 13, Mercedes-Benz, comfort";
		Reader r = new StringReader(data);
		service.readDrivers(r);
		ShortestPath shortestPath = new ShortestPathStub();
		assertEquals(service.getClosestDriver(0, shortestPath).getName(), "Sema");
	}

	@Test
	public void testOnPathCustomersA() throws IOException {
		ManagementService service = new ManagementService();
		String data = "Sema, 2, 4.7, 15, {5,6,8}, 2000\n";
		Reader r = new StringReader(data);
		service.readCustomers(r);
		ShortestPath shortestPath = new ShortestPathStub(new Integer[] { 2, 5, 6, 7, 6, 8 });
		Customer customer = new Customer("", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 8 })),
				new Wallet(Double.MAX_VALUE));
		assertEquals(service.getOnPathCustomers(customer, shortestPath).size(), 1);
	}

	@Test
	public void testOnPathCustomersB() throws IOException {
		ManagementService service = new ManagementService();
		String data = "Tolik, 1, 4.7, 15, {1}, 2000\n";
		Reader r = new StringReader(data);
		service.readCustomers(r);
		ShortestPath shortestPath = new ShortestPathStub();
		Customer customer = new Customer("", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 8 })),
				new Wallet(Double.MAX_VALUE));
		assertEquals(service.getOnPathCustomers(customer, shortestPath).size(), 1);
	}

	@Test
	public void testOnPathCustomersC() throws IOException {
		ManagementService service = new ManagementService();
		String data = "Yrys, 3, 4.7, 15, {8}, 2000\n";
		Reader r = new StringReader(data);
		service.readCustomers(r);
		ShortestPath shortestPath = new ShortestPathStub(new Integer[] {});
		Customer customer = new Customer("", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 8 })),
				new Wallet(Double.MAX_VALUE));
		assertEquals(service.getOnPathCustomers(customer, shortestPath).size(), 0);
	}

	@Test
	public void testOnPathCustomersD() throws IOException {
		ManagementService service = new ManagementService();
		String data = "Yrys, 3, 4.7, 15, {8}, 2000\n" + "Tolik, 5, 4.7, 15, {8}, 2000\n";
		Reader r = new StringReader(data);
		service.readCustomers(r);
		ShortestPath shortestPath = new ShortestPathStub(new Integer[] { 2, 5, 6, 7, 6, 8 });
		Customer customer = new Customer("", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 8 })),
				new Wallet(Double.MAX_VALUE));
		assertEquals(service.getOnPathCustomers(customer, shortestPath).size(), 1);
	}

	@Test
	public void testOnPathCustomersE() throws IOException {
		ManagementService service = new ManagementService();
		String data = "Yrys, 2, 4.7, 15, {5,6}, 2000\n" + "Tolik, 7, 4.7, 15, {6,8}, 2000\n";
		Reader r = new StringReader(data);
		service.readCustomers(r);
		ShortestPath shortestPath = new ShortestPathStub(new Integer[] { 2, 5, 6, 7, 6, 8 });
		Customer customer = new Customer("", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 8 })),
				new Wallet(Double.MAX_VALUE));
		assertEquals(service.getOnPathCustomers(customer, shortestPath).size(), 2);
	}

	@Test
	public void testOnPathCustomersF() throws IOException {
		ManagementService service = new ManagementService();
		String data = "Yrys, 6, 4.7, 15, {5,2}, 2000\n" + "Tolik, 8, 4.7, 15, {6,7,5}, 2000\n";
		Reader r = new StringReader(data);
		service.readCustomers(r);
		ShortestPath shortestPath = new ShortestPathStub();
		Customer customer = new Customer("", 1, new ArrayList<Integer>(Arrays.asList(new Integer[] { 8 })),
				new Wallet(Double.MAX_VALUE));
		assertEquals(service.getOnPathCustomers(customer, shortestPath).size(), 0);
	}
}
