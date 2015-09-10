package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

@Entity
public class Bettype extends Model {

	@Id
	private String bettype;
	private long raceid;
	public String getBettype() {
		return bettype;
	}
	public void setBettype(String bettype) {
		this.bettype = bettype;
	}
	public long getRaceid() {
		return raceid;
	}
	public void setRaceid(long raceid) {
		this.raceid = raceid;
	}
	
	public static Finder<Long, Bettype> find = new Finder<Long, Bettype>(Bettype.class);
	
}
