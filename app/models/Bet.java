package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;

@Entity
public class Bet extends Model {

	@Id
	private long id;
	private String user;
	private long raceid;
	private String bettype;
	private long runnerid;
	private String unit;
	
	public Bet(String username, long raceid, String bettype, long runnerid, String unit)
	{
		this.user = username;
		this.raceid = raceid;
		this.bettype = bettype;
		this.runnerid = runnerid;
		this.unit = unit;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public long getRaceid() {
		return raceid;
	}
	public void setRaceid(long raceid) {
		this.raceid = raceid;
	}
	public String getBettype() {
		return bettype;
	}
	public void setBettype(String bettype) {
		this.bettype = bettype;
	}
	public long getRunnerid() {
		return runnerid;
	}
	public void setRunnerid(long runnerid) {
		this.runnerid = runnerid;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

}
