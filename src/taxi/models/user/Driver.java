package taxi.models.user;

import taxi.models.Rating;
import taxi.models.Taxi;

public class Driver extends User {
	private Taxi taxi;

	public Driver(String name, int location, Taxi taxi) {
		super(name, location);
		this.taxi = taxi;
	}

	public Driver(String name, int location, Rating rating, Taxi taxi) {
		super(name, location, rating);
		this.taxi = taxi;
	}

	public Taxi getTaxi() {
		return taxi;
	}
}
