package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry implements Serializable{

    private String name;
    private String content;
    private Date dateWritten;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");


    public Entry(String name, String content){
        this.name = name;
        this.content = content;
        dateWritten = new Date();

    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateWritten() {
        return df.format(dateWritten);
    }





}
