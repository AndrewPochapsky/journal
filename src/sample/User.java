package sample;

import java.io.Serializable;

public class User implements Serializable {
    //ToDo encrypt password
    private int id;
    private String userName;
    //TODO decide if user class needs to know password
    private String passWord;


    public User(int id, String userName, String passWord){
        this.id = id;
        this.userName=userName;
        this.passWord=passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
