package dao;


import models.Department;
import models.Employee;

import java.util.List;

public interface DepartmentDao {


    //create
    void add (Department department);
//    void addDepartmentToEmployee(Department department, Employee employee)

    //read
    List<Department> getAll();
    Department findById(int id);
//    List<Employee> getAllEmployeesByDepartment(int departmentId);

    //update
    void update(int id, String name, String description, String employee_no);

    //delete
    void deleteById(int id);
    void clearAll();


}
