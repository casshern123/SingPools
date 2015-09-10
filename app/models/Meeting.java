package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class Meeting extends Model {
	
	@Id
	private long id;
	private String country;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	public static Finder<Long, Meeting> find = new Finder<Long, Meeting>(Meeting.class);
	
}
