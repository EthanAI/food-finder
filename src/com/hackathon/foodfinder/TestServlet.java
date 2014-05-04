package com.hackathon.foodfinder;

import java.io.*;
import java.sql.*;

import javax.servlet.http.*;

import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {
	
	//add new checkin
	  @Override
	  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		query(req, resp);
	  }
	
	//get the virgin and visited from database
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		query(req, resp);
	}
	
	public void query(HttpServletRequest req, HttpServletResponse resp) {
		String userEmail = "ethan@ethan.com";
		
		String projectID = "smooth-kiln-569";
		String instanceName = "finder1"; //smooth-kiln-569.appspot.com"; //smooth-kiln-569:finder1
		String databaseName = "foodfinder";
		String sqlLoginName = "root";
		String sqlLoginPassword = "food";
		
	    String url = null;
	    
	    String s = null;
	    try {
	        Class.forName("com.mysql.jdbc.GoogleDriver");
	        url = "jdbc:google:mysql://" + projectID + ":" + instanceName + "/" + databaseName + "?user=root";
	        
	        //jdbc:google:mysql://<appID:instanceID>/<dbname>?user=root
	        
	        Connection conn = DriverManager.getConnection(url);
        
            Statement stmt = conn.createStatement();
            
            String query = "SELECT * " +
                    "FROM checkins " +
                    "WHERE userEmail = \"" + userEmail + "\"";
            
            ResultSet rs = stmt.executeQuery(query);
            
            
            

            JSONArray jsArray = new JSONArray();
            
        	while (rs.next()) {
                //s = rs.getString("userEmail");
                //System.out.println(s);
                JSONObject myJSON = new JSONObject();

            	myJSON.put("userEmail", rs.getString("userEmail"));
            	myJSON.put("restaurantName", rs.getString("restaurantName"));
            	myJSON.put("latitude", rs.getString("latitude"));
            	myJSON.put("longitude", rs.getString("longitude"));

            	jsArray.put(myJSON);
            }
        	
        	//json.write(response.getWriter());
        	
        	resp.setContentType("text/plain");
        	//resp.getWriter().println(query);
    		resp.getWriter().println(jsArray.toString());
	        
	    } catch(Exception e) {
	    	
	    }
		
		
		//resp.setContentType("text/plain");
		//resp.getWriter().
		//println("Hello, world " + url + " " + s);
	}
	    
	
	
	/*
	public void demoSQL() {
		//hardcoded for now
		String projectID = "smooth-kiln-569";
		String instanceName = "smooth-kiln-569.appspot.com"; //smooth-kiln-569:finder1
		String databaseName = "foodfinder";
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			  // Load the class that provides the new "jdbc:google:mysql://" prefix.
			  Class.forName("com.mysql.jdbc.GoogleDriver");
			  url = "jdbc:google:mysql://" + projectID + ":" + instanceName + "/" + databaseName + "?user=root";
			} else {
			  // Local MySQL instance to use during development.
			  Class.forName("com.mysql.jdbc.Driver");
			  url = "jdbc:mysql://127.0.0.1:3306/<your-database-name>?user=root";
			}
	}
	*/
	
	
/*
table 1:
create table checkins (rowNumber INT NOT NULL AUTO_INCREMENT, userEmail VARCHAR(255), restaurantName VARCHAR(255), latitude DECIMAL(10,7), longitude DECIMAL(10,7), PRIMARY KEY (rowNumber));

INSERT INTO checkins (userEmail, restaurantName, latitude, longitude) values ("ethan@ethan.com", "Cafe Taj Mahal", "21.2892699", "-157.8114305"); 

Table 2:

http://stackoverflow.com/questions/6472233/can-i-store-images-in-mysql

create table photos (userEmail VARCHAR(255), restaurantName VARCHAR(255), photoDirectory VARCHAR(255));

INSERT INTO photos (userEmail, restaurantName, photoDirectory) values ("ethan@ethan.com", "Cafe Taj Mahal", "gs://smooth-kiln-569.appspot.com/foodFinderPhotos/Mattar Paneer 2.JPG");

Setup SQL in Eclipse
https://developers.google.com/eclipse/docs/cloudsql-createapp
he application has been deployed), then use com.mysql.jdbc.GoogleDriver to connect to your Cloud SQL instance.

Cloud Storage (not datastore since pictures wont change)

gsutil ls
gs://smooth-kiln-569.appspot.com/
gsutil cp "gs://smooth-kiln-569.appspot.com/foodFinderPhotos/Mattar Paneer 2.JPG" ./
*/
	//"jdbc:google:mysql://" + projectID + ":" + 	instanceName + "/" + databaseName,
	//userName, password);
//url = "jdbc:google:mysql://" + projectID + ":" + instanceName + "/guestbook?user=root";
	
	
}
