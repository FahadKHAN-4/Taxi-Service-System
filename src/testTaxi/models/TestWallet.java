package testTaxi.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import taxi.exceptions.NotEnoughBalanceException;
import taxi.models.Wallet;

public class TestWallet {

	@Test
    public void testPayFromBalanceA() throws NotEnoughBalanceException {
        Wallet w = new Wallet(1000.0);
        w.payFromBalance(140.0);
        assertEquals(860.0, w.getBalance(), 0.00001);
    }

	@Test
    public void testPayFromBalanceB() throws NotEnoughBalanceException {
        Wallet w = new Wallet(100.0);
        assertThrows(NotEnoughBalanceException.class, () -> w.payFromBalance(200.0));
    }

	@Test
    public void testPayFromBalanceC() {
        Wallet w = new Wallet(0);
        w.topUpBalance(250.0);
        assertEquals(250.0, w.getBalance(), 0.00001);
    }
}
