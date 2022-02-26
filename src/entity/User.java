package entity;

/**
 * @author mo7984130
 * @Classname User
 * @Description TODO
 * @Date 2021/10/17 10:19 上午
 */
public class User {

    private static final String database = "user";
    private int userId;
    private String userName;
    private String password;

    public String getDatabase() { return database; }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
