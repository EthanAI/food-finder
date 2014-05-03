food-finder
===========

Cloud SQL: 
https://developers.google.com/cloud-sql/
mysql login: mysql --host=173.194.241.34 --user=root --password


SQl TO SETUP

table 1:
create table checkins (rowNumber INT NOT NULL AUTO_INCREMENT, userEmail VARCHAR(255), restaurantName VARCHAR(255), latitude DECIMAL(10,7), longitude DECIMAL(10,7), PRIMARY KEY (rowNumber));

INSERT INTO checkins (userEmail, restaurantName, latitude, longitude) values ("ethan@ethan.com", "Cafe Taj Mahal", "21.2892699", "-157.8114305"); 

Table 2:

http://stackoverflow.com/questions/6472233/can-i-store-images-in-mysql
create table photos (userEmail VARCHAR(255), restaurantName VARCHAR(255), photoDirectory VARCHAR(255));

INSERT INTO photos (userEmail, restaurantName, photoDirectory) values ("ethan@ethan.com", "Cafe Taj Mahal", "/user/photos/1.jpg");
