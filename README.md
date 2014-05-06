food-finder
===========
Food Finder is a webapp that runs in the cloud using Google App Engine tools. 
Food Finder helps you decide where to eat out by identifying nearby restaurants you have
never eaten at before. Good for getting to know your city better. Social elements will 
add the ability to see where your friends eat a lot to help you decide among your untried 
restaurants. You can also upload a photo of your meal when you check in, and search through
photos of other meals people have had at the restaurant to help you decide what looks good.


========================technical stuff===================================================
Cloud SQL: 
https://developers.google.com/cloud-sql/
mysql login: 
mysql --host=173.194.241.34 --user=root --password=food


SQl TO SETUP

create database foodfinder;
connect foodfinder;

table 1:
create table checkins (rowNumber INT NOT NULL AUTO_INCREMENT, userEmail VARCHAR(255), restaurantName VARCHAR(255), latitude DECIMAL(10,7), longitude DECIMAL(10,7), PRIMARY KEY (rowNumber));

INSERT INTO checkins (userEmail, restaurantName, latitude, longitude) values ("ethan@ethan.com", "Cafe Taj Mahal", "21.2892699", "-157.8114305"); 

INSERT INTO checkins (userEmail, restaurantName, latitude, longitude) values ("ethan@ethan.com", "Nishi Moncho Ramen", "21.309482", "-157.810528");

INSERT INTO checkins (userEmail, restaurantName, latitude, longitude) values ("ethan@ethan.com", "Bankok Chef", "21.310534", "-157.808706");

INSERT INTO checkins (userEmail, restaurantName, latitude, longitude) values ("friend@friend.com", "Manoa Sushi", "21.307785", "-157.810267");

21.307785,-157.810267

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


Linking
Postman



