DROP TABLE IF EXISTS employees;

CREATE TABLE IF NOT EXISTS employees(
	employee_id INT(3) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	employee_name VARCHAR(100) NOT NULL,
	gender INT(1) NOT NULL,
	employee_phone VARCHAR(13) NOT NULL,
	employee_email VARCHAR(100) NOT NULL,
	password VARCHAR(8) NOT NULL,
	date_of_entry DATE NOT NULL,
	start_date DATE,
	end_date DATE,
	sales int(10),
	district_in_charge VARCHAR(100),
	customers int(100),
	updated_at DATE,
	department_id int(3)
);

DROP TABLE IF EXISTS departments;

CREATE TABLE IF NOT EXISTS departments(
	department_id int(2) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	department_name VARCHAR(100) NOT NULL
);