package dao;
import models.Department;
import models.Employee;
import java.util.List;

public interface EmployeeDao {

    //create;
    void add(Employee employee);
//    void addEmployeeToDepartment(Employee employee, Department department);

    //read
    List<Employee> getAll();
//    Department findById(int id);
//    List<Department> getAllDepartmentsForAEmployee(int id);

    //update
//    void update(int id, String name, String position, String role, String departmentId);


    //delete
    void deleteById(int id);
    void clearAll();

    void update(int employeeToEditId, String newContent, int newDepartmentId);
}
