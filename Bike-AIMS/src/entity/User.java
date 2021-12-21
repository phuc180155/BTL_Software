package entity;

import java.util.List;

public class User {

    private int userId;
    private String userName;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
//    @Override
//    public String toString() {
//        return "User{" +
//                "userId=" + this.userId +
//                ", name='" + this.name + '\'' +
//                '}';
//    }
}
