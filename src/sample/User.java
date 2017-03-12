package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    //ToDo encrypt password
    private int id;
    private String userName;
    //TODO decide if user class needs to know password
    private String passWord;
    private List<Entry> journal;

    public List<Entry> getJournal() {
        return journal;
    }

    public void setJournal(List<Entry> journal) {
        this.journal = journal;
    }

    public User(int id, String userName, String passWord){
        this.id = id;
        this.userName=userName;
        this.passWord=passWord;
        this.journal = new ArrayList<>();
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

    public void addEntry(Entry entry){
        journal.add(entry);
    }

    public void removeEntry(Entry entry){
        journal.remove(entry);
    }

}
