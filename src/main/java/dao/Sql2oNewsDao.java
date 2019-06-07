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
    public void add(News news){
        String sql = "INSERT INTO news(writtenby, content,departmentId) VALUES(writtenby, content,departmentId)";

        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql,true)
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
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM newss")
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public  List<News> getAllNewssByDepartment(int departmentId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM newss WHERE departmentId = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(News.class);
        }
    }


//    @Override
//    public List<News> getAllNewssByDepartmentSortedNewestToOldest(int departmentId) {
//        List<News> unsortedNewss = getAllNewssByDepartment(departmentId);
//        List<News> sortedNewss = new ArrayList<>();
//        int i = 1;
//        for (News news : unsortedNewss){
//            int comparisonResult;
//            if (i == unsortedNewss.size()) { //we need to do some funky business here to avoid an arrayindex exception and handle the last element properly
//                if (news.compareTo(unsortedNewss.get(i-1)) == -1){
//                    sortedNewss.add(0, unsortedNewss.get(i-1));
//                }
//                break;
//            }
//
//            else {
//                if (news.compareTo(unsortedNewss.get(i)) == -1) { //first object was made earlier than second object
//                    sortedNewss.add(0, unsortedNewss.get(i));
//                    i++;
//                } else if (news.compareTo(unsortedNewss.get(i)) == 0) {//probably should have a tie breaker here as they are the same.
//                    sortedNewss.add(0, unsortedNewss.get(i));
//                    i++;
//                } else {
//                    sortedNewss.add(0, unsortedNewss.get(i)); //push the first object to the list as it is newer than the second object.
//                    i++;
//                }
//            }
//        }
//        return sortedNewss;
//    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from newss WHERE id=:id";
        try (Connection con = sql2o.open()) {
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
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

}
