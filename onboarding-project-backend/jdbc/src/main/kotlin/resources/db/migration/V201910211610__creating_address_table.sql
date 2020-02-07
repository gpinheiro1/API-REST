CREATE TABLE address (
	id varchar(50) NOT NULL,
	country varchar(255) NOT NULL,
	state varchar(255) NOT NULL,
	city varchar(255) NOT NULL,
	zip_code varchar(255) NOT NULL,
	street varchar(255) NOT NULL,
	number integer NOT NULL,
	user_id varchar(50) REFERENCES users (id),
	CONSTRAINT pk_address PRIMARY KEY (id)
);