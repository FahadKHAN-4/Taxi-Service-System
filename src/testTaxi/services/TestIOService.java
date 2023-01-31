package testTaxi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

import taxi.models.user.Customer;
import taxi.services.IOService;


public class TestIOService {
	@Test
	public void testIOService_1() {
		String data = "Sema\n5\n1000\n6 7 8 9";
		Scanner sc = new Scanner(data);
		IOService service = new IOService(sc);
		Customer c = service.initializeCustomer();
		assertEquals(c.getName(), "Sema");
	}
	
	@Test
	public void testIOService_2() {
		String data = "Sema\n5\n1000\n6 7 8 9";
		Scanner sc = new Scanner(data);
		IOService service = new IOService(sc);
		Customer c = service.initializeCustomer();
		assertEquals(c.getLocation(), 5);
	}
	
	@Test
	public void testIOService_3() {
		String data = "Sema\n5\n1000\n6 7 8 9";
		Scanner sc = new Scanner(data);
		IOService service = new IOService(sc);
		Customer c = service.initializeCustomer();
		assertEquals(c.getWallet().getBalance(), 1000.0, 0);
	}
	
	@Test
	public void testIOService_4() {
		String data = "Sema\n5\n1000\n6 7 8 9";
		Scanner sc = new Scanner(data);
		IOService service = new IOService(sc);
		Customer c = service.initializeCustomer();
		assertEquals(c.getDestinations().size(), 4);
	}
	
	@Test
	public void testIOService_5() {
		String data = "2";
		Scanner sc = new Scanner(data);
		IOService service = new IOService(sc);
		int c = service.getCustomerChoice();
		assertEquals(c, 2);
	}
	
	@Test
	public void testIOService_6() {
		String data = "5\n1";
		Scanner sc = new Scanner(data);
		IOService service = new IOService(sc);
		int c = service.getCustomerChoice();
		assertEquals(c, 1);
	}
	
	@Test
	public void testIOService_7() {
		String data = "5";
		Scanner sc = new Scanner(data);
		IOService service = new IOService(sc);
		int c = service.getSecondCustomerLocation();
		assertEquals(c, 5);
	}
	
	@Test
	public void testIOService_8() {
		String data = "\n5 6 7 8 3";
		Scanner sc = new Scanner(data);
		IOService service = new IOService(sc);
		ArrayList<Integer> c = service.getUserDestinations();
		assertIterableEquals(Arrays.asList(new Integer[] { 5, 6, 7, 8, 3 }), c);
	}
}
