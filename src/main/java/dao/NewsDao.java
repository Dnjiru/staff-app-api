package dao;

import models.News;
import java.util.List;

public interface NewsDao {

    //create
    static void add(News news);

    //read
    List<News> getAll();
    List<News> getAllNewssByDepartment(int departmentId);
//    List<News> getAllNewssByDepartmentNewestToOldest(int departmentId);

    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAll();
}
