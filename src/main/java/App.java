import com.google.gson.Gson;
import dao.DB;
import dao.Sql2oDepartmentDao;
import dao.Sql2oEmployeeDao;
import dao.Sql2oNewsDao;
import exceptions.ApiException;
import models.Department;
import models.Employee;
import models.News;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        Sql2oEmployeeDao employeeDao;
        Sql2oDepartmentDao departmentDao;
        Sql2oNewsDao newsDao;
        Connection conn;
        Gson gson = new Gson();

        staticFileLocation("/public");
        departmentDao = new Sql2oDepartmentDao(DB.sql2o);
        employeeDao = new Sql2oEmployeeDao(DB.sql2o);
        newsDao = new Sql2oNewsDao(DB.sql2o);
        conn = DB.sql2o.open();


        ProcessBuilder process=new ProcessBuilder();
        Integer port;

        if(process.environment().get("PORT")!=null){
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);




        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
//            List<Department> allDepartments = departmentDao.getAll();
//            model.put("departments", allDepartments);
//            List<Employee> employees = employeeDao.getAll();
//            model.put("employees", employees);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //CREATE
        post("/departments/:departmentId/employee/:employeeId", "application/json", (req, res) -> {

            int departmentId = Integer.parseInt(req.params("departmentId"));
            int employeeId = Integer.parseInt(req.params("employeeId"));
            Department department = departmentDao.findById(departmentId);
            Employee employee = employeeDao.findById(employeeId);


            if (department != null && employee != null) {
                //both exist and can be associated
                employeeDao.addEmployeeToDepartment(employee, department);
                res.status(201);
                return gson.toJson(String.format("Employee'%s' and Department'%s' have been associated", employee.getName(), department.getName()));
            } else {
                throw new ApiException(404, String.format("Department or Employee does not exist"));
            }
        });

        get("/departments/:id/employees", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            } else if (departmentDao.getAllEmployeesByDepartment(departmentId).size() == 0) {
                return "{\"message\":\"I'm sorry, but no employees are listed for this department.\"}";
            } else {
                return gson.toJson(departmentDao.getAllEmployeesByDepartment(departmentId));
            }
        });

        get("/employees/:id/departments", "application/json", (req, res) -> {
            int employeeId = Integer.parseInt(req.params("id"));
            Employee employeeToFind = employeeDao.findById(employeeId);
            if (employeeToFind == null) {
                throw new ApiException(404, String.format("No employee with the id: \"%s\" exists", req.params("id")));
            } else if (employeeDao.getAllDepartmentsForAEmployee(employeeId).size() == 0) {
                return "{\"message\":\"I'm sorry, but no departments are listed for this employee.\"}";
            } else {
                return gson.toJson(employeeDao.getAllDepartmentsForAEmployee(employeeId));
            }
        });


        post("/departments/:departmentId/newss/new", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("departmentId"));
            News news = gson.fromJson(req.body(), News.class);

            news.setDepartmentId(departmentId);
            newsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });

        post("/employees/new", "application/json", (req, res) -> {
            Employee employee = gson.fromJson(req.body(), Employee.class);
            employeeDao.add(employee);
            res.status(201);
            return gson.toJson(employee);
        });

        post("/newss/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });

        //READ
        get("/departments", "application/json", (req, res) -> {
            System.out.println(departmentDao.getAll());

            if (departmentDao.getAll().size() > 0) {
                return gson.toJson(departmentDao.getAll());
            } else {
                return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
            }

        });

        get("/newws", "application/json", (req, res) -> {
            System.out.println(newsDao.getAll());

            if (newsDao.getAll().size() > 0) {
                return gson.toJson(newsDao.getAll());
            } else {
                return "{\"message\":\"I'm sorry, but no newss are currently listed in the database.\"}";
            }

        });

        get("/departments/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(departmentToFind);
        });

        get("/departments/:id/newss", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));

            Department departmentToFind = departmentDao.findById(departmentId);
            List<News> allNewss;

            if (departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
            }

            allNewss = newsDao.getAllNewssByDepartment(departmentId);

            return gson.toJson(allNewss);
        });

        get("/employees", "application/json", (req, res) -> {
            return gson.toJson(employeeDao.getAll());
        });


        //CREATE
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);
            return gson.toJson(department);
        });

        //FILTERS
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

    }
}

