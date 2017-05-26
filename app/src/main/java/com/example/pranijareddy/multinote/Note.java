package com.example.pranijareddy.multinote;

import java.io.Serializable;

/**
 * Created by Pranijareddy on 2/19/2017.
 */

public class Note implements Serializable {
    private int id=0;
    private String title;
    private String date;
    private String description;


    public Note(String title, String date, String description) {
        this.id=id++;
        this.title = title;
        this.date = date;
        this.description = description;
    }
    public Note(int id,String title, String date, String description) {
        this.id=id;
        this.title = title;
        this.date = date;
        this.description = description;
    }


    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return title +": "+ date + ": " + description;
    }
}
