package testTaxi.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import taxi.models.Rating;
import taxi.models.Receipt;
import taxi.models.Wallet;
import taxi.models.taxitype.ComfortTaxiType;
import taxi.models.taxitype.PremiumTaxiType;
import taxi.models.user.Customer;

public class TestReceipt {

    // Customer with default rating(5.0). UserType = premium. ComfortTaxiType.
    @Test
    public void testGenerateReceiptA() {
        Customer c = new Customer("A", 0, null, new Wallet(1000));
        Receipt r = Receipt.generateReceipt(10, new ComfortTaxiType(), c);
        assertEquals(120.0, r.getPrice(), 0.1);
        assertEquals(10, r.getDistance(), 0.1);
        assertEquals(0.125, r.getTime(), 0.1);
    }

    // Customer with default rating(5.0). UserType = premium. PremiumTaxiType
    @Test
    public void testGenerateReceiptB() {
        Customer c = new Customer("B", 0, null, new Wallet(1000));
        Receipt r = Receipt.generateReceipt(12, new PremiumTaxiType(), c);
        assertEquals(96, r.getPrice(), 0.1);
        assertEquals(12, r.getDistance(), 0.1);
        assertEquals(0.2, r.getTime(), 0.1);
    }

    // Customer with 4.2 rating. UserType = vip. ComfortTaxiType
    @Test
    public void testGenerateReceiptC() {
        Customer c = new Customer("C", 0, new Rating(4.2), null, new Wallet(1000));
        Receipt r = Receipt.generateReceipt(10, new ComfortTaxiType(), c);
        assertEquals(127.5, r.getPrice(), 0.1);
        assertEquals(10, r.getDistance(), 0.1);
        assertEquals(0.125, r.getTime(), 0.1);
    }

    // Customer with 4.3 rating. UserType = vip. PremiumTaxiType
    @Test
    public void testGenerateReceiptD() {
        Customer c = new Customer("D", 0, new Rating(4.3), null, new Wallet(1000));
        Receipt r = Receipt.generateReceipt(12, new PremiumTaxiType(), c);
        assertEquals(102.0, r.getPrice(), 0.1);
        assertEquals(12, r.getDistance(), 0.1);
        assertEquals(0.2, r.getTime(), 0.1);
    }

    // Customer with 3.7 rating. UserType = top. ComfortTaxiType
    @Test
    public void testGenerateReceiptE() {
        Customer c = new Customer("E", 0, new Rating(3.7), null, new Wallet(1000));
        Receipt r = Receipt.generateReceipt(10, new ComfortTaxiType(), c);
        assertEquals(135.0, r.getPrice(), 0.1);
        assertEquals(10, r.getDistance(), 0.1);
        assertEquals(0.125, r.getTime(), 0.1);
    }

    // Customer with 3.8 rating. UserType = top. PremiumTaxiType
    @Test
    public void testGenerateReceiptF() {
        Customer c = new Customer("F", 0, new Rating(3.8), null, new Wallet(1000));
        Receipt r = Receipt.generateReceipt(12, new PremiumTaxiType(), c);
        assertEquals(108.0, r.getPrice(), 0.1);
        assertEquals(12, r.getDistance(), 0.1);
        assertEquals(0.2, r.getTime(), 0.1);
    }

    // Customer with 3.3 rating. UserType = casual. ComfortTaxiType
    @Test
    public void testGenerateReceiptG() {
        Customer c = new Customer("G", 0, new Rating(3.3), null, new Wallet(1000));
        Receipt r = Receipt.generateReceipt(10, new ComfortTaxiType(), c);
        assertEquals(150.0, r.getPrice(), 0.1);
        assertEquals(10, r.getDistance(), 0.1);
        assertEquals(0.125, r.getTime(), 0.1);
    }

    // Customer with 3.2 rating. UserType = casual. PremiumTaxiType
    @Test
    public void testGenerateReceiptH() {
        Customer c = new Customer("H", 0, new Rating(3.2), null, new Wallet(1000));
        Receipt r = Receipt.generateReceipt(12, new PremiumTaxiType(), c);
        assertEquals(120.0, r.getPrice(), 0.1);
        assertEquals(12, r.getDistance(), 0.1);
        assertEquals(0.2, r.getTime(), 0.1);
    }

    // Shared receipt. ComfortTaxiType.
    @Test
    public void testGenerateShareReceiptI() {
        Customer c1 = new Customer("A", 0, null, new Wallet(1000));
        Customer c2 = new Customer("A", 0, null, new Wallet(1000));
        Receipt r = Receipt.generateShareReceipt(10, new ComfortTaxiType(), c1, c2);
        assertEquals(75, r.getPrice(), 0.1);
        assertEquals(10, r.getDistance(), 0.1);
        assertEquals(0.125, r.getTime(), 0.1);
    }

    // Shared receipt. PremiumTaxiType.
    @Test
    public void testGenerateShareReceiptJ() {
        Customer c1 = new Customer("A", 0, null, new Wallet(1000));
        Customer c2 = new Customer("A", 0, null, new Wallet(1000));
        Receipt r = Receipt.generateShareReceipt(12, new PremiumTaxiType(), c1, c2);
        assertEquals(60, r.getPrice(), 0.1);
        assertEquals(12, r.getDistance(), 0.1);
        assertEquals(0.2, r.getTime(), 0.1);
    }

    // Shared receipt. ComfortTaxiType.
    @Test
    public void testGenerateShareReceiptK() {
        Customer c = new Customer("A", 0, null, new Wallet(1000));
        Receipt r = Receipt.generateShareReceipt(15, 10, new ComfortTaxiType(), c);
        assertEquals(100, r.getPrice(), 0.1);
        assertEquals(10, r.getDistance(), 0.1);
        assertEquals(0.1875, r.getTime(), 0.1);
    }

    // Shared receipt. PremiumtTaxiType.
    @Test
    public void testGenerateShareReceiptL() {
        Customer c = new Customer("A", 0, null, new Wallet(1000));
        Receipt r = Receipt.generateShareReceipt(18, 12, new PremiumTaxiType(), c);
        assertEquals(80, r.getPrice(), 0.1);
        assertEquals(12, r.getDistance(), 0.1);
        assertEquals(0.3, r.getTime(), 0.1);
    }

    @Test
    public void testGenerateShareReceiptM() {
        Customer c = new Customer("A", 0, null, new Wallet(1000));
        Receipt r = Receipt.generateShareReceipt(18, 12, new PremiumTaxiType(), c);
        assertEquals("Thank you for travelling with us! \r\n"
                + String.format("Distance: %.2f / Price: %.2f / Time: %.2f", 12.0, 80.0, 0.3), r.toString());
    }
}
