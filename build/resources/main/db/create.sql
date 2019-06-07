SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS departments (
id int PRIMARY KEY auto_increment,
name VARCHAR,
description VARCHAR,
employee_no VARCHAR
);

CREATE TABLE IF NOT EXISTS employees (
id int PRIMARY KEY auto_increment,
name VARCHAR,
position VARCHAR,
role VARCHAR,
departmentid INTEGER
);

CREATE TABLE IF NOT EXISTS newss (
id int PRIMARY KEY auto_increment,
writtenby VARCHAR,
content VARCHAR,
departmentid INTEGER
);