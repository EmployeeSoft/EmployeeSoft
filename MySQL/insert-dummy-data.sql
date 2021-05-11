USE authentication;

INSERT INTO user(username, email, pwd, date_created, date_modified) VALUES(
	"alice",
    "alicebob@gmail.com",
    "123",
    "2021-05-10",
    "2021-05-10"
);

INSERT INTO user(username, email, pwd, date_created, date_modified) VALUES(
	"henryl",
    "henrylu@yahoo.com",
    "321",
    "2017-01-17",
    "2021-04-01"
);

INSERT INTO user(username, email, pwd, date_created, date_modified) VALUES(
	"teddy",
    "teddyle@aoe.com",
    "12345",
    "2019-12-29",
    "2021-07-11"
);

USE backend;

INSERT INTO digital_doc(type, required, template_location, description) VALUES(
	"OPT EAD",
    true,
    "Path to OPT EAD",
    "This an OPT EAD Form"
);

INSERT INTO digital_doc(type, required, template_location, description) VALUES(
	"OPT EAD STEM",
    true,
    "Path to OPT EAD STEM",
    "This an OPT EAD STEM Form"
);

INSERT INTO digital_doc(type, required, template_location, description) VALUES(
	"I-983",
    true,
    "Path to I-983",
    "This an I-983 Form"
);


INSERT INTO digital_doc(type, required, template_location, description) VALUES(
	"I-20",
    true,
    "Path to I-20",
    "This an I-20 Form"
);

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

INSERT INTO person(first_name, last_name, middle_name, email, cell_phone, alt_phone, gender, ssn, dob, user_id) VALUES(
	"Henry",
    "Lu",
    "",
    "henrylu@yahoo.com",
    "1234567890",
    "1230987654",
    0,
    "987654321",
    "1994-09-11",
    2
);

INSERT INTO person(first_name, last_name, middle_name, email, cell_phone, alt_phone, gender, ssn, dob, user_id) VALUES(
	"Teddy",
    "Le",
    "",
    "teddyle@aoe.com",
    "3211092345",
    "3210981234",
    0,
    "543217890",
    "1996-04-05",
    3
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

INSERT INTO address(address_line_1, address_line_2, city, zipcode, state_name, state_abbr, person_id) VALUES(
	"78901 Marriot St",
    "",
	"International City",
    "59483",
    "MadeUp",
    "MU",
    1
);

INSERT INTO address(address_line_1, address_line_2, city, zipcode, state_name, state_abbr, person_id) VALUES(
	"85764 Something St",
    "",
	"Dark City",
    "98754",
    "DarkState",
    "DS",
    2
);

INSERT INTO address(address_line_1, address_line_2, city, zipcode, state_name, state_abbr, person_id) VALUES(
	"87678 Windy St",
    "",
	"Windy City",
    "12376",
    "WindyState",
    "WS",
    3
);

INSERT INTO contact(person_id, relationship, title, is_reference, is_emergency, is_landlord) VALUES(
	1,
    "friend",
    "Relationship Title",
    false,
    true,
    false
);

INSERT INTO contact(person_id, relationship, title, is_reference, is_emergency, is_landlord) VALUES(
	1,
    "Co-Worker",
    "Cco-worker title",
    true,
    false,
    false
);

INSERT INTO contact(person_id, relationship, title, is_reference, is_emergency, is_landlord) VALUES(
	1,
    "Friend",
    "Friend Relationship Title",
    false,
    true,
    false
);

INSERT INTO contact(person_id, relationship, title, is_reference, is_emergency, is_landlord) VALUES(
	2,
    "Landlord",
    "Landlord Relationship Title",
    true,
    false,
    true
);

INSERT INTO contact(person_id, relationship, title, is_reference, is_emergency, is_landlord) VALUES(
	3,
    "Unknown",
    "Unknown Relationship Title",
    false,
    false,
    false
);

INSERT INTO visa_status(visa_type, is_active, date_modified, create_user) VALUES(
	"H1-B",
    true,
    "2021-05-09",
    "Alice"
);

INSERT INTO visa_status(visa_type, is_active, date_modified, create_user) VALUES(
	"L2",
    false,
    "2021-04-10",
    "Henry"
);

INSERT INTO visa_status(visa_type, is_active, date_modified, create_user) VALUES(
	"H1-B",
    true,
    "2021-05-09",
    "Teddy"
);

INSERT INTO employee(person_id, title, manager_id, start_date, end_date, avatar, car, visa_status_id, visa_start_date, visa_end_date, driver_license, driver_license_exp_date) VALUES(
	1,
    "Title 1",
    10,
    "2021-05-05",
    "2021-05-10",
    null,
    "Honda_Civic_White",
    1,
    "2020-05-10",
    "2021-01-10",
    "12345678",
    "2023-05-07"
);

INSERT INTO employee(person_id, title, manager_id, start_date, end_date, avatar, car, visa_status_id, visa_start_date, visa_end_date, driver_license, driver_license_exp_date) VALUES(
	2,
    "Title 2",
    11,
    "2017-01-10",
    "2021-05-11",
    null,
    "Toyota_Camry_Black",
    2,
    "2016-12-29",
    "2021-05-10",
    "23456789",
    "2024-04-01"
);

INSERT INTO employee(person_id, title, manager_id, start_date, end_date, avatar, car, visa_status_id, visa_start_date, visa_end_date, driver_license, driver_license_exp_date) VALUES(
	3,
    "Title 3",
    10,
    "2019-05-01",
    "2021-05-11",
    null,
    "Honda_Accord_Blue",
    3,
    "2019-04-15",
    "2021-01-11",
    "34567890",
    "2022-01-01"
);


INSERT INTO app_workflow(employee_id, date_created, date_modified, status, comment, type) VALUES(
	1,
    "2021-05-09",
    "2021-05-10",
    1,
    "Please submit your documents",
    "I-20"
);

INSERT INTO app_workflow(employee_id, date_created, date_modified, status, comment, type) VALUES(
	2,
    "2019-03-11",
    "2021-05-10",
    0,
    "Please complete your document. And start your OPT EAD STEM form.",
    "OPT EAD"
);

INSERT INTO app_workflow(employee_id, date_created, date_modified, status, comment, type) VALUES(
	3,
    "2017-01-10",
    "2021-05-11",
    0,
    "Please upload your documents.",
    "I-20"
);