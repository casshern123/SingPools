package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;

@Entity
public class Race extends Model {

	@Id
	private long id;
	private String name;
	private String raceclass;
	private long meetingid;
	
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
	public String getRaceclass() {
		return raceclass;
	}
	public void setRaceclass(String raceclass) {
		this.raceclass = raceclass;
	}
	public long getMeetingid() {
		return meetingid;
	}
	public void setMeetingid(long meetingid) {
		this.meetingid = meetingid;
	}
	
	public static Finder<Long, Race> find = new Finder<Long, Race>(Race.class);
	
}
