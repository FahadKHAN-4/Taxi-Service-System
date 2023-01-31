package taxi.models.taxitype;

import static taxi.utils.Constants.COMFORT_TAXI_CHARGE_FEE;
import static taxi.utils.Constants.COMFORT_TAXI_SPEED;

public class ComfortTaxiType implements TaxiType {

	@Override
	public int getChargeFee() {
		return COMFORT_TAXI_CHARGE_FEE;
	}

	@Override
	public int getCarSpeed() {
		return COMFORT_TAXI_SPEED;
	}
}
