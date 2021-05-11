USE backend;

INSERT INTO visa_status(visa_type, is_active, date_modified, create_user) VALUES(
	"H-20",
    true,
    "2021-05-09",
    "Teddy"
);


INSERT INTO employee(person_id, title, manager_id, start_date, end_date, avatar, car, visa_status_id, visa_start_date, visa_end_date, driver_license, driver_license_exp_date) VALUES(
	1,
    "Title",
    10,
    "2021-05-05",
    "2021-05-10",
    null,
    "Honda_civic_white",
    1,
    "2020-05-10",
    "2021-01-10",
    "12345678",
    "2023-05-07"
);


INSERT INTO app_workflow(employee_id, date_created, date_modified, status, comment, type) VALUES(
	2,
    "2021-05-09",
    "2021-05-10",
    1,
    "Please submit your documents",
    "I-20"
);