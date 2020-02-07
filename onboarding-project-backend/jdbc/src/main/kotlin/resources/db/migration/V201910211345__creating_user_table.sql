CREATE TABLE users (
	id varchar(50) NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	company varchar(255) NOT NULL,
	birth_date timestamp NOT NULL,
	document_id varchar(15) NOT NULL,
	type varchar(255) NOT NULL,
	CONSTRAINT pk_users PRIMARY KEY (id)
);
