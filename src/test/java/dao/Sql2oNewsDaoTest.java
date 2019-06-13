package dao;

import dao.DB;
import dao.NewsDao;
import dao.EmployeeDao;
import models.News;
import models.Department;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oNewsDaoTest {
    private static Connection conn; //these variables are now static.
    private static Sql2oDepartmentDao departmentDao; //these variables are now static.
    private static Sql2oEmployeeDao employeeDao; //these variables are now static.
    private static Sql2oNewsDao newsDao; //these variables are now static.

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/staff_news";  //connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "Daudi", "mwaniki321");
        departmentDao = new Sql2oDepartmentDao(DB.sql2o);
        employeeDao = new Sql2oEmployeeDao(DB.sql2o);
        newsDao = new Sql2oNewsDao(DB.sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        departmentDao.clearAll(); //clear all departments after every test
        employeeDao.clearAll(); //clear all departments after every test
        newsDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{ //changed to static
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void addingNewsSetsId() throws Exception {
        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);
        News testNews = new News("Captain Kirk", 3);
        int originalNewsId = testNews.getId();
        newsDao.add(testNews);
        assertNotEquals(originalNewsId,testNews.getId());
    }

    @Test
    public void getAll() throws Exception {
        News news1 = setupNews();
        News news2 = setupNews();
        assertEquals(2, newsDao.getAll().size());
    }

    @Test
    public void getAllNewssByDepartment() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupDepartment(); //add in some extra data to see if it interferes
        News news1 = setupNewsForDepartment(testDepartment);
        News news2 = setupNewsForDepartment(testDepartment);
        News newsForOtherDepartment = setupNewsForDepartment(otherDepartment);
        assertEquals(2, newsDao.getAllNewssByDepartment(testDepartment.getId()).size());
    }

    @Test
    public void deleteById() throws Exception {
        News testNews = setupNews();
        News otherNews = setupNews();
        assertEquals(2, newsDao.getAll().size());
        newsDao.deleteById(testNews.getId());
        assertEquals(1, newsDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        News testNews = setupNews();
        News otherNews = setupNews();
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }

    @Test
    public void timeStampIsReturnedCorrectly() throws Exception {
        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);
        News testNews = new News("Captain Kirk",testDepartment.getId());
        newsDao.add(testNews);

    }

    @Test
    public void newssAreReturnedInCorrectOrder() throws Exception {
        Department testDepartment = setupDepartment();
        departmentDao.add(testDepartment);
        News testNews = new News("Captain Kirk", testDepartment.getId());
        newsDao.add(testNews);
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }

        News testSecondNews = new News("Mr. Spock", testDepartment.getId());
        newsDao.add(testSecondNews);

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }

        News testThirdNews = new News("Scotty", testDepartment.getId());
        newsDao.add(testThirdNews);

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }

        News testFourthNews = new News("Mr. Sulu", testDepartment.getId());
        newsDao.add(testFourthNews);

        assertEquals(4, newsDao.getAllNewssByDepartment(testDepartment.getId()).size()); //it is important we verify that the list is the same size.

    }

    //helpers

    public News setupNews() {
        News news = new News("great", 4);
        newsDao.add(news);
        return news;
    }

    public News setupNewsForDepartment(Department department) {
        News news = new News("great", department.getId());
        newsDao.add(news);
        return news;
    }

    public Department setupDepartment() {
        Department department = new Department("Fish Witch", "214 NE Broadway");
        departmentDao.add(department);
        return department;
    }
}