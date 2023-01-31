package taxi.models;

import taxi.models.taxitype.TaxiType;

public class Taxi {
	private String model;
	private TaxiType taxiType;

	public Taxi(String model, TaxiType taxiType) {
		this.model = model;
		this.taxiType = taxiType;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public TaxiType getTaxiType() {
		return taxiType;
	}

	public void setTaxiType(TaxiType taxiType) {
		this.taxiType = taxiType;
	}
}
