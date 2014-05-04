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
	    String projectID = "smooth-kiln-569";
		String instanceName = "finder1"; //smooth-kiln-569.appspot.com"; //smooth-kiln-569:finder1
		String databaseName = "foodfinder";
		
	    String url = null;
	    try {
	      if (SystemProperty.environment.value() ==
	          SystemProperty.Environment.Value.Production) {
	        // Load the class that provides the new "jdbc:google:mysql://" prefix.
	        Class.forName("com.mysql.jdbc.GoogleDriver");
	        //url = "jdbc:google:mysql://" + projectID + ":" + instanceName + "/guestbook?user=root";
	        url = "jdbc:google:mysql://" + projectID + ":" + instanceName + "/" + databaseName + "?user=root";
	      } else {
	        // Local MySQL instance to use during development.
	        Class.forName("com.mysql.jdbc.Driver");
	        url = "jdbc:mysql://127.0.0.1:3306/guestbook?user=root";

	        // Alternatively, connect to a Google Cloud SQL instance using:
	        // jdbc:mysql://ip-address-of-google-cloud-sql-instance:3306/guestbook?user=root
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      return;
	    }

	    PrintWriter out = resp.getWriter();
	    try {
	      Connection conn = DriverManager.getConnection(url);
	      /*
	        Connection conn = DriverManager.getConnection(
    		"jdbc:google:mysql://your-project-id:your-instance-name/database",
    		"user", "password");
	       */
	      try {
	        String fname = req.getParameter("fname");
	        String content = req.getParameter("content");
	        if (fname == "" || content == "") {
	          out.println(
	              "<html><head></head><body>You are missing either a message or a name! Try again! " +
	              "Redirecting in 3 seconds...</body></html>");
	        } else {
	          String statement = "INSERT INTO entries (guestName, content) VALUES( ? , ? )";
	          PreparedStatement stmt = conn.prepareStatement(statement);
	          stmt.setString(1, fname);
	          stmt.setString(2, content);
	          int success = 2;
	          success = stmt.executeUpdate();
	          if (success == 1) {
	            out.println(
	                "<html><head></head><body>Success! Redirecting in 3 seconds...</body></html>");
	          } else if (success == 0) {
	            out.println(
	                "<html><head></head><body>Failure! Please try again! " +
	                "Redirecting in 3 seconds...</body></html>");
	          }
	        }
	        
	      } finally {
	        conn.close();
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    resp.setHeader("Refresh", "3; url=/guestbook.jsp");
	  }
	
	  
	  
	  
	//get the virgin and visited from database
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String projectID = "smooth-kiln-569";
		String instanceName = "finder1"; //smooth-kiln-569.appspot.com"; //smooth-kiln-569:finder1
		String databaseName = "foodfinder";
		String userName = "root";
		String password = "food";
		
	    String url = null;
	    
	    String s = null;
	    try {
	        Class.forName("com.mysql.jdbc.GoogleDriver");
	        url = "jdbc:google:mysql://" + projectID + ":" + instanceName + "/" + databaseName + "?user=root";
	        
	        //jdbc:google:mysql://<appID:instanceID>/<dbname>?user=root
	        
	        Connection conn = DriverManager.getConnection(url);
        
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(
                    "SELECT * " +
                    "FROM checkins");
            
            
            

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
    		resp.getWriter().println(
    				"(" + jsArray.toString() + ")"
    				);
	        
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
