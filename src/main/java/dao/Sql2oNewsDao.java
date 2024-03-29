package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;


public class Sql2oNewsDao implements NewsDao {
    private final Sql2o sql2o;
    public Sql2oNewsDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(News news) {
        String sql = "INSERT INTO newss (content, departmentid) VALUES (:content,:departmentId)"; //if you change your model, be sure to update here as well!
        try (Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<News> getAll() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM newss")
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public List<News> getAllNewssByDepartment(int departmentId) {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM newss WHERE departmentId = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from newss WHERE id=:id";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from newss";
        try (Connection con = DB.sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
