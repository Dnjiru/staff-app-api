//package dao;
//
//import org.junit.After;
//import org.junit.Before;
//import models.News;
//import models.Department;
//import org.junit.Test;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
//
//
//import static org.junit.Assert.*;
//
//public class Sql2oNewsDaoTest {
//    private Connection conn;
//    private Sql2oNewsDao newsDao;
//    private Sql2oDepartmentDao departmentDao;
//
//
//    @Before
//    public void setUp() throws Exception {
//        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString, "", "");
//        newsDao = new Sql2oNewsDao(sql2o);
//        departmentDao = new Sql2oDepartmentDao(sql2o);
//        conn = sql2o.open();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        conn.close();
//    }
//
//    @Test
//    public void getAll() throws Exception {
//        News news1 = setupNews();
//        News news2 = setupNews();
//        assertEquals(2, newsDao.getAll().size());
//    }
//
//    @Test
//    public void getAllNewssByDepartment() throws Exception {
//        Department testDepartment = setupDepartment();
//        Department otherDepartment = setupDepartment(); //add in some extra data to see if it interferes
//        News news1 = setupNewsForDepartment(testDepartment);
//        News news2 = setupNewsForDepartment(testDepartment);
//        News newsForOtherDepartment = setupNewsForDepartment(otherDepartment);
//        assertEquals(2, newsDao.getAllNewssByDepartment(testDepartment.getId()).size());
//    }
//
//    @Test
//    public void deleteById() throws Exception {
//        News testNews = setupNews();
//        News otherNews = setupNews();
//        assertEquals(2, newsDao.getAll().size());
//        newsDao.deleteById(testNews.getId());
//        assertEquals(1, newsDao.getAll().size());
//    }
//
//    @Test
//    public void clearAll() throws Exception {
//        News testNews = setupNews();
//        News otherNews = setupNews();
//        newsDao.clearAll();
//        assertEquals(0, newsDao.getAll().size());
//    }
//
//    //helpers
//
//    public News setupNews() {
//        News news = new News("Tips on Learning Code", "Mwaniki", 1);
//        newsDao.add(news);
//        return news;
//    }
//
//    public News setupNewsForDepartment(Department department) {
//        News news = new News("How to Thrive", "Daudi", 2);
//        newsDao.add(news);
//        return news;
//    }
//
//    public Department setupDepartment() {
//        Department department = new Department("Technology", "Heart of Safaricom Service Delivery", "845");
//        departmentDao.add(department);
//        return department;
//    }
//
//}