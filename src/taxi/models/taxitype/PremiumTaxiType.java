package taxi.models.taxitype;

import static taxi.utils.Constants.PREMIUM_TAXI_CHARGE_FEE;
import static taxi.utils.Constants.PREMIUM_TAXI_SPEED;

public class PremiumTaxiType implements TaxiType {

	@Override
	public int getChargeFee() {
		return PREMIUM_TAXI_CHARGE_FEE;
	}

	@Override
	public int getCarSpeed() {
		return PREMIUM_TAXI_SPEED;
	}

}
