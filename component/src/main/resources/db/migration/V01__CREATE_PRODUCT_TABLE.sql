CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

	CREATE TABLE product (
		id uuid default   uuid_generate_v4(),
		name VARCHAR NOT NULL,
		description VARCHAR NOT NULL,
		price DECIMAL NOT NULL,
	    CONSTRAINT product_id_pk PRIMARY KEY(id)
	);
	