package testTaxi;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import taxi.Main;
import taxi.utils.Constants;

public class TestMain {

  @Test
  public void testMainA() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    Main.main(new String[] { "Salikh\n1\n100000\n3 2 1 4\n1\n0\n0\n" });
	String print = Constants.WELCOME_TEXT + "\n" + "Dear customer, welcome to our service!: \n"
			+ "Please input your name: Please input your location: Please input your account balance: Please input one or more destinations (separated by space): "
			+ Constants.OFFERED_SERVICES + "\n"
			+ "Please pick a service: Finding the closest driver...\n" + "\n" + "Route\t\t\t Distance\t\t Path\n"
			+ "5 -> 1 \t\t\t 13.0 \t\t\t 5 0 1 \n" + "Route\t\t\t Distance\t\t Path\n" + "7 -> 1 \t\t\t 6.0 \t\t\t 7 1 \n"
			+ "Route\t\t\t Distance\t\t Path\n" + "2 -> 1 \t\t\t 6.0 \t\t\t 2 7 1 \n"
			+ "Route\t\t\t Distance\t\t Path\n" + "9 -> 1 \t\t\t 19.0 \t\t\t 9 8 4 1 \n" + "\n"
			+ "Calculating the optimal route...\n" + "\n" + "Route\t\t\t Distance\t\t Path\n"
			+ "1 -> 3 \t\t\t 40.0 \t\t\t 1 4 8 3 \n" + "Route\t\t\t Distance\t\t Path\n"
			+ "3 -> 2 \t\t\t 46.0 \t\t\t 3 7 2 \n" + "Route\t\t\t Distance\t\t Path\n"
			+ "2 -> 1 \t\t\t 6.0 \t\t\t 2 7 1 \n" + "Route\t\t\t Distance\t\t Path\n" + "1 -> 4 \t\t\t 3.0 \t\t\t 1 4 \n"
			+ "Thank you for travelling with us! \r\n" + "Distance: 95.00 / Price: 760.00 / Time: 1.58\n"
			+ "Please input one or more destinations (separated by space): "
			+ Constants.OFFERED_SERVICES + "\n"
			+ "Please pick a service: \n" + "Thank you for choosing us! See you soon!\n";
	assertEquals(print, out.toString());
  }

  @Test
  public void testMainB() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Main.main(new String[] { "Salikh\n2\n100000\n3 5\n2\n4\n0\n0\n" });
		String print = Constants.WELCOME_TEXT + "\n" + "Dear customer, welcome to our service!: \n"
				+ "Please input your name: Please input your location: Please input your account balance: Please input one or more destinations (separated by space): "
				+ Constants.OFFERED_SERVICES + "\n"
				+ "Please pick a service: Please enter second customer location: Finding the closest driver...\n" + "\n"
				+ "Route\t\t\t Distance\t\t Path\n" + "5 -> 2 \t\t\t 19.0 \t\t\t 5 0 1 7 2 \n"
				+ "Route\t\t\t Distance\t\t Path\n" + "7 -> 2 \t\t\t 0.0 \t\t\t 7 2 \n"
				+ "Route\t\t\t Distance\t\t Path\n" + "2 -> 2 \t\t\t 0.0 \t\t\t 2 \n"
				+ "Route\t\t\t Distance\t\t Path\n" + "9 -> 2 \t\t\t 23.0 \t\t\t 9 7 2 \n" + "\n"
				+ "Calculating the optimal route...\n" + "\n" + "Route\t\t\t Distance\t\t Path\n"
				+ "2 -> 4 \t\t\t 9.0 \t\t\t 2 7 1 4 \n" + "Route\t\t\t Distance\t\t Path\n"
				+ "2 -> 3 \t\t\t 46.0 \t\t\t 2 7 3 \n" + "Route\t\t\t Distance\t\t Path\n"
				+ "2 -> 5 \t\t\t 19.0 \t\t\t 2 7 1 0 5 \n" + "Thank you for travelling with us! \r\n"
				+ "Distance: 74.00 / Price: 370.00 / Time: 1.23\n"
				+ "Please input one or more destinations (separated by space): " + Constants.OFFERED_SERVICES + "\n"
				+ "Please pick a service: \n" + "Thank you for choosing us! See you soon!\n";
		assertEquals(print, out.toString());
  }

  @Test
  public void testMainC() {
	  ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Main.main(new String[] { "Salikh\n1\n100000\n3 5 6\n3\n0\n0\n" });
		String print = Constants.WELCOME_TEXT + "\n" + "Dear customer, welcome to our service!: \n"
				+ "Please input your name: Please input your location: Please input your account balance: Please input one or more destinations (separated by space): "
				+ Constants.OFFERED_SERVICES + "\n"
				+ "Please pick a service: Finding the closest driver...\n" + "\n"
				+ "Route\t\t\t Distance\t\t Path\n" + "5 -> 1 \t\t\t 13.0 \t\t\t 5 0 1 \n"
				+ "Route\t\t\t Distance\t\t Path\n" + "7 -> 1 \t\t\t 6.0 \t\t\t 7 1 \n"
				+ "Route\t\t\t Distance\t\t Path\n" + "2 -> 1 \t\t\t 6.0 \t\t\t 2 7 1 \n"
				+ "Route\t\t\t Distance\t\t Path\n" + "9 -> 1 \t\t\t 19.0 \t\t\t 9 8 4 1 \n" + "\n"
				+ "Calculating the optimal route...\n" + "\nThere are 2 customers on your way:\n" + "Salikh 4 6\n"
				+ "Kevin 3 6\n" + "Route\t\t\t Distance\t\t Path\n"
				+ "1 -> 3 \t\t\t 40.0 \t\t\t 1 4 8 3 \n" + "Route\t\t\t Distance\t\t Path\n"
				+ "3 -> 5 \t\t\t 43.0 \t\t\t 3 0 5 \n" + "Route\t\t\t Distance\t\t Path\n"
				+ "5 -> 6 \t\t\t 7.0 \t\t\t 5 6 \n" + "Thank you for travelling with us! \r\n"
				+ "Distance: 90.00 / Price: 364.86 / Time: 3.70\n"
				+ "Please input one or more destinations (separated by space): " + Constants.OFFERED_SERVICES + "\n"
				+ "Please pick a service: \n" + "Thank you for choosing us! See you soon!\n";
		assertEquals(print, out.toString());
  }

}
