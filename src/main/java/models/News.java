package models;

import java.util.Objects;

public class News {
    private String content;
    private int id;
    private int departmentId; //will be used to connect Department to News(One to many)

    public News(String content,  int departmentId) {
        this.content = content;
        this.departmentId = departmentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                departmentId == news.departmentId &&
                Objects.equals(content, news.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content,id, departmentId);
    }
}
