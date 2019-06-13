package dao;

import dao.DB;
import dao.Sql2oDepartmentDao;
import dao.Sql2oEmployeeDao;
import dao.Sql2oNewsDao;
import models.Department;
import models.Employee;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oEmployeeDaoTest {

    private static Connection conn;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oEmployeeDao employeeDao;
    private static Sql2oNewsDao newsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/staff_news";  //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "Daudi", "mwaniki321"); //changed employee and pass to null
        departmentDao = new Sql2oDepartmentDao(DB.sql2o);
        employeeDao = new Sql2oEmployeeDao(DB.sql2o);
        newsDao = new Sql2oNewsDao(DB.sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        departmentDao.clearAll();
        newsDao.clearAll();
        employeeDao.clearAll();
        System.out.println("clearing database");
    }

    @AfterClass
    public static void shutDown() throws Exception{ //changed to static
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void addingFoodSetsId() throws Exception {
        Employee testEmployee = setupNewEmployee();
        int originalEmployeeId = testEmployee.getId();
        employeeDao.add(testEmployee);
        assertNotEquals(originalEmployeeId,testEmployee.getId());
    }

    @Test
    public void addedEmployeesAreReturnedFromGetAll() throws Exception {
        Employee testemployee = setupNewEmployee();
        employeeDao.add(testemployee);
        assertEquals(1, employeeDao.getAll().size());
    }

    @Test
    public void noEmployeesReturnsEmptyList() throws Exception {
        assertEquals(0, employeeDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectEmployee() throws Exception {
        Employee employee = setupNewEmployee();
        employeeDao.add(employee);
        employeeDao.deleteById(employee.getId());
        assertEquals(0, employeeDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Employee testEmployee = setupNewEmployee();
        Employee otherEmployee = setupNewEmployee();
        employeeDao.clearAll();
        assertEquals(0, employeeDao.getAll().size());
    }



    @Test
    public void deletingDepartmentAlsoUpdatesJoinTable() throws Exception {
        Employee testEmployee  = new Employee("Seafood","Ek200","technician");
        employeeDao.add(testEmployee);

        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);

        Department altDepartment = setupAltDepartment();
        departmentDao.add(altDepartment);

        departmentDao.addDepartmentToEmployee(testDepartment,testEmployee);
        departmentDao.addDepartmentToEmployee(altDepartment, testEmployee);

        departmentDao.deleteById(testDepartment.getId());
        assertEquals(0, departmentDao.getAllEmployeesByDepartment(testDepartment.getId()).size());
    }

    @Test
    public void deletingEmployeeAlsoUpdatesJoinTable() throws Exception {

        Department testDepartment = setupDepartment();

        departmentDao.add(testDepartment);

        Employee testEmployee = setupNewEmployee();
        Employee otherEmployee = new Employee("Japanese", "EK0006", "engineer");

        employeeDao.add(testEmployee);
        employeeDao.add(otherEmployee);

        employeeDao.addEmployeeToDepartment(testEmployee, testDepartment);
        employeeDao.addEmployeeToDepartment(otherEmployee,testDepartment);

        employeeDao.deleteById(testDepartment.getId());
        assertEquals(0, employeeDao.getAllDepartmentsForAEmployee(testEmployee.getId()).size());
    }

    // helpers

    public Employee setupNewEmployee(){
        return new Employee("Sushi", "ek200", "engineer");
    }

    public Department setupDepartment (){
        return new Department("Regional operations", "for telecoms deployment");
    }

    public Department setupAltDepartment (){
        return new Department("Digital IT", "For it operations");
    }
}