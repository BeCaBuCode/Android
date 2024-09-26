package com.example.myapplication;

import java.io.Serializable;

public class Student implements Serializable {
    private String Name;
    private String MSSV;
    private String Birthday;
    private boolean isSelected;
    public Student(String name, String MSSV, String birthday) {
        Name = name;
        this.MSSV = MSSV;
        Birthday = birthday;
        isSelected=false;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getBirthday() {
        return Birthday;
    }

    public boolean IsSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }
}

