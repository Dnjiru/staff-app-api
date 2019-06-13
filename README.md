#STAFF NEWS API

 This is an API app where employee can add  departments, add staff and News. a employee should view only employees and news for a particular department and also can view all general news all the employees in the company.
## Behavior of the programm

 | Behavior                                       |  Input | Output    |
 | ---------------------------------------------- | ------ | --------- |
 |Adding new models.Department|  Click on the add models.Department tab   |  New record will be adde to the list of Engieers|
 |Adding a new models.Employee| click On add new employee tab  |  New form will be dispalayed to add more employee |
 |Displaying the list of News| click on home page |  all the news and thier details will be displayed|
 
## Setup/Installation Requirements

* create a new folder on your desired location in your local machine
* git init using your terminal
* git clone https://github.com/Dnjiru/staff-app-api.git
* make sure you have intellij installed in you laptop.
* launch intellij and go to files>open project.
* enjoy the code.
## Database installation instructions
Make sure you have postgres Db installed locally in your machine and follow below commands.

###### Creating a role and credentials

postgres=# create employee elvis with password 'elvis';


###### Creating database
postgres=# create database dept_news;


###### connecting into the created database
postgres=# \c dept_news;

###### Creating departments table.

dept_news=# create table departments (id serial PRIMARY KEY, name varchar, description varchar);

###### Creating employee table

dept_news=# create table employees (id serial PRIMARY KEY,name varchar,number varchar, designation varchar);

###### Creating newss table
dept_news=# create table newss(id serial PRIMARY KEY, content varchar, departmentid integer);

###### creating a join table between employees 
dept_news=# create table departments_employees (id serial PRIMARY KEY, employeesid INTEGER, departmentid INTEGER);


## List of APIs

###### 1. /departments/new - POST

- create a new department

###### 2. /employees/new - POST

- create a new employee/ employee.

###### 3. /departments/:departmentId/employee/:employeeId - POST

- create a new employee/ employee to specific department.

###### 4. /departments/:id/employees - GET

- list all the employees from a specific department

###### 5. /employees/:id/departments - GET

- list all employees and departments they can access the news

###### 6. /departments/:departmentId/newss/new - POST

-creating news for specific deparment

###### 7. /departments/:id - GET

- view details of a specific department

###### 8. /departments/:id/newss - GET

- getting all the newss for a specific department

###### 9. /employees - GET

- listing all the employees.


## Known Bugs

there are currently no known bugs experienced on the website but feedback on bugs encountered during viewing of the page will be highly appreciated and will go a long way into making the website better for the employees. The only reason that the website may fail to load is if the Internet connection is slow or disconnected which will require you to troubleshoot your Internet connection

## Technologies Used
* java
* JUnit
* Spark
* postgressql
* postman

**Main Languages used:**

* java for web application and spark as a framework


**Other Technologies:**

* none

## live link:

<https://staffapp.herokuapp.com/>

# clone into repository

* git clone https://github.com/Dnjiru/staff-app-api.git
* Open using your favorite editor and view the code or just open the index.html on the browser

## Feedback

Incase of any issues or feedback please add using any of below links.

* [Issues](https://github.com/Dnjiru/staff-app-api.git). To submit any issues.

* [email](mr.mwaniki@gmail.com) for any other feedback.

## Support and contact details

 Contact me on +254723 369 578 or on my github account <https://github.com/Dnjiru>


## License

This project is licensed under the MIT License

**_David Mwaniki Njiru_** Copyright (c) 2019