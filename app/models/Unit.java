package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

@Entity
public class Unit extends Model {

	
	@Id
	private String unit;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public static Finder<Long, Unit> find = new Finder<Long, Unit>(Unit.class);
}
