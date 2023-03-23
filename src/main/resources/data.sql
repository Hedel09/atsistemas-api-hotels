INSERT INTO hotels ("name",category) VALUES 
	('Test Hotel ALFA',5),
	('Test Hotel BETA',4.3),
	('Test Hotel GAMMA',3.5),
	('Test Hotel DELTA',4.7),
	('Test Hotel EPSILON',2.9),
	('Test Hotel DSETA',1.5);


INSERT INTO availabilities ("date",id_hotel,rooms) VALUES 
	('2023-08-07',5,20),
	('2023-07-20',5,16),
	('2023-12-03',2,5),
	('2023-06-12',0,1),
	('2023-08-07',3,4),
	('2023-08-07',4,2);

INSERT INTO bookings (id_hotel,date_from,date_to,email) VALUES 
	(0,'2023-07-07','2023-07-15','john@example.com'),
	(0,'2023-05-01','2023-05-12','john@example.com'),
	(0,'2024-08-01','2023-08-30','john@example.com'),
	(2,'2023-09-15','2023-09-17','john@example.com'),
	(1,'2023-06-02','2023-06-11','john@example.com'),
	(5,'2023-11-10','2023-11-12','john@example.com');
