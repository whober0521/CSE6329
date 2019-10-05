package facility_maintenance.model;

import java.io.Serializable;

public class Facility implements Serializable{
	private String id;
	private String name;
	private int amount;
	
	public void setFacility (String id, String name, int amount) {
		setId(id);
		setName(name);
		setAmount(amount);
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
}