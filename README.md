food-finder
===========

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

INSERT INTO checkins (userEmail, restaurantName, latitude, longitude) values ("ethan@ethan.com", "McDonald's", "21.309641", "-157.809559");

INSERT INTO checkins (userEmail, restaurantName, latitude, longitude) values ("ethan@ethan.com", "Bankok Chef", "21.310534", "-157.808706");

21.309641,-157.809559

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



