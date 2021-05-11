# Start of authentication database table declarations below

DROP DATABASE IF EXISTS authentication;
CREATE DATABASE authentication;

USE authentication;

# Reference on TIME_STAMP 
# https://dev.mysql.com/doc/refman/8.0/en/timestamp-initialization.html

# Reference on Foreign Key Constraints
# https://dev.mysql.com/doc/refman/8.0/en/create-table-foreign-keys.html

CREATE TABLE user(
	id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(254) UNIQUE NOT NULL,
    pwd VARCHAR(256) NOT NULL,
    date_created DATE,
    date_modified DATE,
    PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE role(
	id INT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    date_created DATE,
    date_modified DATE,
    PRIMARY KEY (id)
) engine=InnoDB;

# Reference to ON DELETE 
# https://www.mysqltutorial.org/mysql-on-delete-cascade/

# When a role fron the role table gets deleted, we will set the user's role to null 
CREATE TABLE user_role(
	id INT NOT NULL AUTO_INCREMENT,
    user_id INT UNIQUE NOT NULL,
    role_id INT,
    active_flag BOOLEAN NOT NULL,
    date_created DATE,
    date_modified DATE,
    last_modified_user VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE SET NULL
) engine=InnoDB;

# Table with no relationship in authentication database
CREATE TABLE registration_token(
	id INT NOT NULL AUTO_INCREMENT,
    token VARCHAR(500) UNIQUE NOT NULL,
    start_time DATE NOT NULL,
    end_time DATE NOT NULL,
    email VARCHAR(254) UNIQUE NOT NULL,
	created_by VARCHAR(200) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(email) REFERENCES user(email)
) engine=InnoDB;

# End of authentication database table declarations 