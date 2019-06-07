package dao;

import models.Department;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import models.Employee;

import java.util.List;


public class Sql2oEmployeeDao implements EmployeeDao {
    private final Sql2o sql2o;

    public Sql2oEmployeeDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Employee employee) {
        String sql = "INSERT INTO employees (name, position, role, departmentId ) = (:name, :position, :role, :departmentId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(employee)
                    .executeUpdate()
                    .getKey();
            employee.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Employee> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM employees")
                    .executeAndFetch(Employee.class);
        }
    }

//
//    @Override
//    public void update(int id, String newName, String newPosition, String newRole, String newDepartmentId) {
//        String sql = "UPDATE employees SET (name, position, role, departmentId ) = (:name, :position, :role, :departmentId) WHERE id=:id"; //CHECK!!!
//        try (Connection con = sql2o.open()) {
//            con.createQuery(sql)
//                    .addParameter("name", newName)
//                    .addParameter("position", newPosition)
//                    .addParameter("role", newRole)
//                    .addParameter("departmentId", newDepartmentId)
//                    .executeUpdate();
//        } catch (Sql2oException ex) {
//            System.out.println(ex);
//        }
//    }


        @Override
        public void deleteById ( int id){
            String sql = "DELETE from employees WHERE id=:id"; //raw sql
            try (Connection con = sql2o.open()) {
                con.createQuery(sql)
                        .addParameter("id", id)
                        .executeUpdate();
            } catch (Sql2oException ex) {
                System.out.println(ex);
            }
        }

        @Override
        public void clearAll () {
            String sql = "DELETE from employees";
            try (Connection con = sql2o.open()) {
                con.createQuery(sql).executeUpdate();
            } catch (Sql2oException ex) {
                System.out.println(ex);
            }
        }

    @Override
    public void update(int employeeToEditId, String newContent, int newDepartmentId) {

    }


}



