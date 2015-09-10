package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

@Entity
public class Runner extends Model {

	@Id
	private long id;
	private String name;
	private long raceid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getRaceid() {
		return raceid;
	}
	public void setRaceid(long raceid) {
		this.raceid = raceid;
	}
	
	public static Finder<Long, Runner> find = new Finder<Long, Runner>(Runner.class);
	
}
