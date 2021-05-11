USE authentication;

INSERT INTO user(username, email, pwd, date_created, date_modified) VALUES(
	"alice",
    "alicebob@gmail.com",
    "123",
    "2021-05-10",
    "2021-05-10"
);

USE backend;

INSERT INTO person(first_name, last_name, middle_name, email, cell_phone, alt_phone, gender, ssn, dob, user_id) VALUES(
	"Alice",
    "Bob",
    "",
    "alicebob@gmail.com",
    "7141234567",
    "7140987654",
    1,
    "123456789",
    "1995-12-01",
    1
);

INSERT INTO address(address_line_1, address_line_2, city, zipcode, state_name, state_abbr, person_id) VALUES(
	"12345 Street St",
    "",
	"CityName",
    "12345",
    "StateName",
    "SN",
    1
);

SELECT * FROM address;