USE authentication;

-- INSERT INTO user(username, email, pwd, date_created, date_modified) VALUES(
-- 	"alice",
--     "alicebob@gmail.com",
--     "123",
--     "2021-05-10",
--     "2021-05-10"
-- );

-- INSERT INTO user(username, email, pwd, date_created, date_modified) VALUES(
-- 	"henryl",
--     "henrylu@yahoo.com",
--     "321",
--     "2017-01-17",
--     "2021-04-01"
-- );

-- INSERT INTO user(username, email, pwd, date_created, date_modified) VALUES(
-- 	"teddy",
--     "teddyle@aoe.com",
--     "12345",
--     "2019-12-29",
--     "2021-07-11"
-- );

USE backend;

INSERT INTO digital_doc(type, required, template_location, description) VALUES(
	"OPT Receipt",
    true,
    "Path to OPT Receipt",
    "This an OPT Receipt"
);

INSERT INTO digital_doc(type, required, template_location, description) VALUES(
	"OPT EAD",
    true,
    "Path to OPT EAD",
    "This an OPT EAD Card"
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

INSERT INTO digital_doc(type, required, template_location, description) VALUES(
	"OPT EAD STEM Receipt",
    true,
    "Path to OPT EAD STEM Receipt",
    "This an OPT EAD STEM Receipt"
);

INSERT INTO digital_doc(type, required, template_location, description) VALUES(
	"OPT EAD STEM",
    true,
    "Path to OPT EAD STEM",
    "This an OPT EAD STEM Card"
);

INSERT INTO person(first_name, last_name, middle_name, prefer_name, email, cell_phone, alt_phone, gender, ssn, dob, user_id) VALUES(
	"Alice",
    "Bob",
    "",
    "Alice Bob",
    "alicebob@gmail.com",
    "7141234567",
    "7140987654",
    1,
    "123456789",
    "1995-12-01",
    1
);

INSERT INTO person(first_name, last_name, middle_name, prefer_name, email, cell_phone, alt_phone, gender, ssn, dob, user_id) VALUES(
	"Henry",
    "Lu",
    "",
    "Hairy Lu",
    "henrylu@yahoo.com",
    "1234567890",
    "1230987654",
    0,
    "987654321",
    "1994-09-11",
    2
);

INSERT INTO person(first_name, last_name, middle_name, prefer_name, email, cell_phone, alt_phone, gender, ssn, dob, user_id) VALUES(
	"Teddy",
    "Le",
    "",
    "Bear Le",
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

INSERT INTO contact(person_id, full_name, phone, relationship, title, address, is_reference, is_emergency, is_landlord) VALUES(
	1,
    "Bob Davis",
    "7145436789",
    "friend",
    "Doctor",
    "12345 Street St SomeCity, CS",
    false,
    true,
    false
);

INSERT INTO contact(person_id, full_name, phone, relationship, title, address, is_reference, is_emergency, is_landlord) VALUES(
	1,
    "Tina Thanh Nguyen",
    "7142139708",
    "co-worker",
    "SQL Engineer",
    "54321 City St. StreetCity, SC",
    true,
    true,
    false
);

INSERT INTO contact(person_id, full_name, phone, relationship, title, address, is_reference, is_emergency, is_landlord) VALUES(
	1,
    "Katie Clark",
    "6183246578",
    "friend",
    "Sales Associate",
    "123 ABC St. ABC City, HG",
    true,
    true,
    false
);

INSERT INTO contact(person_id, full_name, phone, relationship, title, address, is_reference, is_emergency, is_landlord) VALUES(
	2,
    "Kevin Garcia",
    "8185478172",
    "friend",
    "Engineer",
    "100 Main St. Apt # 10 Random City, RM",
    false,
    false,
    false
);

INSERT INTO contact(person_id, full_name, phone, relationship, title, address, is_reference, is_emergency, is_landlord) VALUES(
	2,
    "Jackson Vars Vars",
    "9260981874",
    "friend",
    "Land Owner",
    "10923 Dark St. Dark City, DV",
    false,
    false,
    true
);

INSERT INTO contact(person_id, full_name, phone, relationship, title, address, is_reference, is_emergency, is_landlord) VALUES(
	3,
    "Johnnie Cash Cow",
    "7148969811",
    "brother-in-law",
    "Thief",
    "19876 Criminal St. Criminal City, CM",
	false,
    true,
    false
);


INSERT INTO visa_status(visa_type, is_active, date_modified, create_user) VALUES(
	"Green Card",
    true,
    "2021-05-09",
    "Alice"
);

INSERT INTO visa_status(visa_type, is_active, date_modified, create_user) VALUES(
	"Citizen",
    true,
    "2021-04-10",
    "Henry"
);

INSERT INTO visa_status(visa_type, is_active, date_modified, create_user) VALUES(
	"F1-OPT",
    false,
    "2021-05-09",
    "Teddy"
);

INSERT INTO employee(person_id, title, manager_id, start_date, end_date, avatar, car, visa_status_id, visa_start_date, visa_end_date, driver_license, driver_license_exp_date) VALUES(
	1,
    "SQL Engineer",
    10,
    "2021-05-05",
    "2021-05-10",
    "ULR/LINK/TO/ALICE/AVATAR",
    "Honda_Civic_White",
    1,
    "2020-05-10",
    "2021-01-10",
    "12345678",
    "2023-05-07"
);

INSERT INTO employee(person_id, title, manager_id, start_date, end_date, avatar, car, visa_status_id, visa_start_date, visa_end_date, driver_license, driver_license_exp_date) VALUES(
	2,
    "Software Engineer",
    11,
    "2017-01-10",
    "2021-05-11",
    "URL/LINK/TO/HENRY/AVATAR",
    "Toyota_Camry_Black",
    2,
    "2016-12-29",
    "2021-05-10",
    "23456789",
    "2024-04-01"
);

INSERT INTO employee(person_id, title, manager_id, start_date, end_date, avatar, car, visa_status_id, visa_start_date, visa_end_date, driver_license, driver_license_exp_date) VALUES(
	3,
    "Security Administrator",
    10,
    "2019-05-01",
    "2021-05-11",
    "URL/LINK/TO/TEDDY/AVATAR",
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
    "Please wait for your OPT EAD Card",
    "OPT Receipt"
);

INSERT INTO app_workflow(employee_id, date_created, date_modified, status, comment, type) VALUES(
	2,
    "2019-03-11",
    "2021-05-10",
    0,
    "Please complete your document. Once you complete, you will receive the I-20 form",
    "I-983"
);

INSERT INTO app_workflow(employee_id, date_created, date_modified, status, comment, type) VALUES(
	3,
    "2017-01-10",
    "2021-05-11",
    0,
    "Please submit your form to the university to verify and get your OPT EAD Receipt.",
    "I-20"
);