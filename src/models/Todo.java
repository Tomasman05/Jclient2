package models;

public class Todo {
    private int userId;
    private String title;
    private boolean completed;
    private Integer id;
    public Todo(Integer userId, String title, boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }
    public Todo(Integer userId, String title, boolean completed, int id) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
}
