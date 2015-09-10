package controllers;

import play.*;
import play.data.Form;
import play.db.DB;
import play.libs.Json;
import play.cache.*;
import play.mvc.*;

import views.html.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.*;
import models.*;

import javax.inject.Inject;


//import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

	@Inject CacheApi cache;
	
    /*public Result index() {
        return ok(index.render("Your new application is ready."));
    }*/
	
	public Result getMeetings()
	{
		List<Meeting> meetingList = null;
		
		//check cache
		
		meetingList = cache.get("cachemeeting");
		if(meetingList == null)
		{
			meetingList = Meeting.find.all();
			cache.set("cachemeeting", meetingList, 60 * 2);
		}
		return ok(Json.toJson(meetingList));
		
	}
	
	public Result getRaces()
	{
		List<Race> raceList = (List<Race>)Race.find.all();
		
		return ok(Json.toJson(raceList));
	}
	
	public Result getRaces(String meetingid)
	{
		List<Race> raceList = (List<Race>)Race.find.where().ieq("meetingid", meetingid).findList();
		
		return ok(Json.toJson(raceList));
	}

	public Result getRunners(String raceid)
	{
		List<Runner> runnerList = Runner.find.where().ieq("raceid", raceid).findList();
		
		return ok(Json.toJson(runnerList));
	}
	
	public Result getBetTypes(String raceid)
	{
		List<Bettype> bettypesList = Bettype.find.where().ieq("raceid", raceid).findList();
		
		return ok(Json.toJson(bettypesList));
	}
	
	@Security.Authenticated
	public Result getUnits()
	{
		List<Unit> unitsList = Unit.find.all();
		
		return ok(Json.toJson(unitsList));
	}

	@Security.Authenticated	//this line, check to session cookies "username"
	public Result postBet()
	{
		Form<Bet> betForm = Form.form(Bet.class);
		Bet dataBet = betForm.bindFromRequest().get();
	
		Bet betObj = new Bet(session("username"),dataBet.getRaceid(),dataBet.getBettype(),dataBet.getRunnerid(),dataBet.getUnit());
//		betObj.setId(1);
//		betObj.setUser("User 1");
//		betObj.setRaceid(1);
//		betObj.setBettype("Type 1");
//		betObj.setRunnerid(1);
//		betObj.setUnit("1x");
		
		//Save to DB
		betObj.save();
		
		//object has been updated with ID populated in DB
		//just use it to return
		
		return ok("OK!");
	}
	
	public Result login()
	{
		Form<User> userForm = Form.form(User.class);
		User loginUser = userForm.bindFromRequest().get();
		
		User retrievedPassword = User.find.where().ieq("username", loginUser.getUsername()).findUnique();
		
//		return ok(loginUser.getPassword());
		
		if(retrievedPassword == null)
		{
			return ok("Forbidden 1");
		}
		else
		{
			if(retrievedPassword.getPassword().equals(loginUser.getPassword()))
			{
				//Create Session
				session("username", retrievedPassword.getUsername());
				return ok("Success Login!");
			}
			else
			{
				return ok("Forbidden 2");
			}
		}
	}
	
	public Result logout()
	{
		//session().clear();	//all session
		session().remove("username");
		return ok("Success logout");
	}

	public Result clearCache()
	{
		//as example cache of meetings
		List<Meeting> meetingList = null;
		meetingList = cache.get("cachemeeting");
		
		if(meetingList != null) cache.remove("cachemeeting");
		
		return ok("cache cleared");
	}

	//without EBean
	public Result oldSchoolMeetings()
	{
		
		Connection con = null;
		List<Meeting> meetingList = new ArrayList<Meeting>();
		PreparedStatement st = null;
		Meeting meeting = null;
		ResultSet rs = null;
		//Connection
		//get default connection set in properties
		try {
			
			con = DB.getConnection();
			st = con.prepareStatement("select * from meeting");
			rs = st.executeQuery();
			
			while(rs.next())
			{
				meeting = new Meeting();
				meeting.setId(rs.getShort("id"));
				meeting.setCountry(rs.getString("country"));
				meetingList.add(meeting);			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		return ok(Json.toJson(meetingList));
	}
}
