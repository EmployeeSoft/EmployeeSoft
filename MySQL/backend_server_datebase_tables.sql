# Start of backend database table declaractions below 

DROP DATABASE IF EXISTS backend;
CREATE DATABASE backend;

USE backend;

# Reference on Foreign Key Constraints
# https://dev.mysql.com/doc/refman/8.0/en/create-table-foreign-keys.html

CREATE TABLE person(
	id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    email VARCHAR(254) UNIQUE NOT NULL,
    cell_phone VARCHAR(15) UNIQUE,
    alt_phone VARCHAR(15) UNIQUE,
    gender TINYINT,
    ssn VARCHAR(9) UNIQUE,
    dob DATE,
    user_id INT UNIQUE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES authentication.user(id) ON DELETE CASCADE
) engine=InnoDB;

CREATE TABLE visa_status(
	id INT NOT NULL AUTO_INCREMENT,
    visa_type VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL,
	date_modified DATE,
    create_user VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

# Reference on BLOB data type
# https://stackoverflow.com/questions/2475065/what-data-type-to-use-in-mysql-to-store-images/21137878

CREATE TABLE employee(
	id INT NOT NULL AUTO_INCREMENT,
    person_id INT UNIQUE NOT NULL,
    title VARCHAR(100),
    manager_id INT,
    start_date DATE,
    end_date DATE,
    avatar BLOB,
    car VARCHAR(100),
    visa_status_id INT UNIQUE,
    visa_start_date DATE,
    visa_end_date DATE,
    driver_license VARCHAR(8) UNIQUE,
    driver_license_exp_date DATE,
    # house_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE,
    FOREIGN KEY(visa_status_id) REFERENCES visa_status(id) ON DELETE SET NULL
) engine=InnoDB;

CREATE TABLE contact(
	id INT NOT NULL AUTO_INCREMENT,
    person_id INT NOT NULL,		# If unique, person only have one contact. Can a user have multiple?
    relationship VARCHAR(50),
    title VARCHAR(100),
    is_reference BOOLEAN NOT NULL,		# ?
    is_emergency BOOLEAN NOT NULL,
    is_landlord BOOLEAN NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE
) engine=InnoDB;

CREATE TABLE address(
	id INT NOT NULL AUTO_INCREMENT,
    address_line_1 VARCHAR(100) NOT NULL,
    address_line_2 VARCHAR(100),
    city VARCHAR(50) NOT NULL,
    zipcode VARCHAR(5) NOT NULL,
    state_name VARCHAR(50) NOT NULL,
    state_abbr VARCHAR(2) NOT NULL,
    person_id INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(person_id) REFERENCES person(id) ON DELETE CASCADE
) engine=InnoDB;

CREATE TABLE personal_doc(
	id INT NOT NULL AUTO_INCREMENT,
    employee_id INT NOT NULL,
    path VARCHAR(200) NOT NULL,
    title VARCHAR(100) NOT NULL,
    comment TEXT,
    date_created DATE,
    created_by VARCHAR(100) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(employee_id) REFERENCES employee(id) ON DELETE CASCADE
) engine=InnoDB;

CREATE TABLE app_workflow(
	id INT NOT NULL AUTO_INCREMENT,
    employee_id INT UNIQUE NOT NULL,
    date_created DATE,
    date_modified DATE,
    status TINYINT NOT NULL,
    comment TEXT,
    type VARCHAR(50) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(employee_id) REFERENCES employee(id) ON DELETE CASCADE
) engine=InnoDB;

# Table with no relationship in employee_soft database
CREATE TABLE digital_doc(
	id INT NOT NULL AUTO_INCREMENT,
    type VARCHAR(50) UNIQUE NOT NULL,
    required BOOLEAN NOT NULL,
    template_location VARCHAR(512) NOT NULL,
    description TEXT,
    PRIMARY KEY (id)
) engine=InnoDB;