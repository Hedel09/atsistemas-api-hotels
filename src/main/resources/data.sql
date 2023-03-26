INSERT INTO public.hotels ("name",category) VALUES
	 ('Test Hotel ALFA',5),
	 ('Test Hotel BETA',4),
	 ('Test Hotel GAMMA',3),
	 ('Test Hotel DELTA',4),
	 ('Test Hotel EPSILON',3),
	 ('Test Hotel DSETA',1);



INSERT INTO public.availabilities ("date",id_hotel,rooms) VALUES
	 ('2023-06-12',0,1),
	 ('2023-07-20',5,16),
	 ('2023-08-07',4,2),
	 ('2023-08-07',5,20),
	 ('2023-08-07',3,4),
	 ('2023-12-03',2,5),
	 ('2025-03-10',3,40),
	 ('2025-03-10',1,19),
	 ('2025-03-10',2,10),
	 ('2025-03-11',2,10);
INSERT INTO public.availabilities ("date",id_hotel,rooms) VALUES
	 ('2025-03-11',1,19),
	 ('2025-03-11',3,40),
	 ('2025-03-12',3,40),
	 ('2025-03-12',2,10),
	 ('2025-03-12',1,29),
	 ('2025-03-13',2,30),
	 ('2025-03-13',1,29),
	 ('2025-03-14',2,10),
	 ('2025-03-14',1,29),
	 ('2025-03-14',3,38);
INSERT INTO public.availabilities ("date",id_hotel,rooms) VALUES
	 ('2025-03-15',1,9),
	 ('2025-03-15',3,18),
	 ('2025-03-16',1,9),
	 ('2025-03-16',3,18),
	 ('2025-03-17',1,10),
	 ('2025-03-18',1,10),
	 ('2026-04-10',1,20),
	 ('2026-04-11',1,20),
	 ('2026-04-12',1,20),
	 ('2026-04-13',1,20);
INSERT INTO public.availabilities ("date",id_hotel,rooms) VALUES
	 ('2026-04-14',1,20),
	 ('2026-04-15',1,20),
	 ('2026-04-16',1,20),
	 ('2026-04-17',1,20),
	 ('2026-04-18',1,20),
	 ('2026-04-19',1,20),
	 ('2026-04-20',1,40),
	 ('2030-10-01',3,95),
	 ('2030-10-02',3,95);

INSERT INTO public.bookings (id_hotel,date_from,date_to,email) VALUES
	 (0,'2023-07-07','2023-07-15','john@example.com'),
	 (0,'2023-05-01','2023-05-12','john@example.com'),
	 (0,'2024-08-01','2023-08-30','john@example.com'),
	 (2,'2023-09-15','2023-09-17','john@example.com'),
	 (1,'2023-06-02','2023-06-11','john@example.com'),
	 (5,'2023-11-10','2023-11-12','john@example.com'),
	 (3,'2025-03-14','2025-03-16','alfred@example.com'),
	 (3,'2025-03-14','2025-03-16','alfred@example.com'),
	 (3,'2030-10-01','2030-10-02','alfred@example.com'),
	 (3,'2030-10-01','2030-10-02','alfred@example.com');
INSERT INTO public.bookings (id_hotel,date_from,date_to,email) VALUES
	 (3,'2030-10-01','2030-10-02','alfred@example.com'),
	 (3,'2030-10-01','2030-10-02','alfred@example.com'),
	 (3,'2030-10-01','2030-10-02','alfred@example.com'),
	 (3,'2030-10-01','2030-10-02','alfred@example.com'),
	 (3,'2030-10-01','2030-10-02','alfred@example.com'),
	 (3,'2030-10-01','2030-10-02','alfred@example.com'),
	 (1,'2025-03-10','2025-03-16','alfredo@example.com');
