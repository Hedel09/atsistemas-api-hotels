


--DROP TABLE hotels;
CREATE TABLE hotels (
	id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	"name" varchar NOT NULL,
	category numeric NOT NULL,
	CONSTRAINT hotels_pk PRIMARY KEY (id)
);

-- DROP TABLE availabilities;
CREATE TABLE availabilities (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"date" date NOT NULL,
	id_hotel int4 NOT NULL,
	rooms int4 NOT NULL,
	CONSTRAINT availabiliies_pk PRIMARY KEY (id),
	CONSTRAINT availabiliies_fk FOREIGN KEY (id_hotel) REFERENCES hotels(id)
);

-- DROP TABLE bookings;
CREATE TABLE bookings (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	id_hotel int4 NOT NULL,
	date_from date NOT NULL,
	date_to date NOT NULL,
	email varchar NOT NULL,
	CONSTRAINT bookings_pk PRIMARY KEY (id),
	CONSTRAINT bookings_fk FOREIGN KEY (id_hotel) REFERENCES hotels(id)
);