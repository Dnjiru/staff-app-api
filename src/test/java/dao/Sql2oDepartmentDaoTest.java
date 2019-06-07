//package dao;
//
//import models.Department;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//public class Sql2oDepartmentDaoTest {
//    private Connection conn;
//    private Sql2oDepartmentDao departmentDao;
//    private Sql2oEmployeeDao employeeDao;
//    private Sql2oNewsDao newsDao;
//
//    @Before
//    public void setUp() throws Exception {
//
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        departmentDao = new Sql2oDepartmentDao(sql2o);
//        employeeDao = new Sql2oEmployeeDao(sql2o);
//        newsDao = new Sql2oNewsDao(sql2o);
//        conn = sql2o.open();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        conn.close();
//    }
//
//
//    @Test
//    public void addingFoodSetsId() throws Exception {
//        Department testDepartment = setupDepartment();
//        assertNotEquals(0, testDepartment.getId());
//    }
//
//    @Test
//    public void addedDepartmentsAreReturnedFromGetAll() throws Exception {
//        Department testDepartment = setupDepartment();
//        assertEquals(1, departmentDao.getAll().size());
//    }
//
//    @Test
//    public void noDepartmentsReturnsEmptyList() throws Exception {
//        assertEquals(0, departmentDao.getAll().size());
//    }
//
//    @Test
//    public void findByIdReturnsCorrectDepartment() throws Exception {
//        Department testDepartment = setupDepartment();
//        Department otherDepartment = setupDepartment();
//        assertEquals(testDepartment, departmentDao.findById(testDepartment.getId()));
//    }
//
//    @Test
//    public void updateCorrectlyUpdatesAllFields() throws Exception {
//        Department testDepartment = setupDepartment();
//        departmentDao.update(testDepartment.getId(), "a", "b", "c", "d", "e", "f");
//        Department foundDepartment = departmentDao.findById(testDepartment.getId());
//        assertEquals("a", foundDepartment.getName());
//        assertEquals("b", foundDepartment.getAddress());
//        assertEquals("c", foundDepartment.getZipcode());
//        assertEquals("d", foundDepartment.getPhone());
//        assertEquals("e", foundDepartment.getWebsite());
//        assertEquals("f", foundDepartment.getEmail());
//    }
//
//    @Test
//    public void deleteByIdDeletesCorrectDepartment() throws Exception {
//        Department testDepartment = setupDepartment();
//        Department otherDepartment = setupDepartment();
//        departmentDao.deleteById(testDepartment.getId());
//        assertEquals(1, departmentDao.getAll().size());
//    }
//
//    @Test
//    public void clearAll() throws Exception {
//        Department testDepartment = setupDepartment();
//        Department otherDepartment = setupDepartment();
//        departmentDao.clearAll();
//        assertEquals(0, departmentDao.getAll().size());
//    }
//
//    //helpers
//
//    public Department setupDepartment (){
//        Department department = new Department("Marketing", "Build the brand", "232");
//        departmentDao.add(department);
//        return department;
//    }
//
//    public Department setupAltDepartment (){
//        Department department = new Department("Corporate Affairs", "Keep the business safe", "98");
//        departmentDao.add(department);
//        return department;
//    }
//
//}