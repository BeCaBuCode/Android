package com.example.myapplication;

import java.util.ArrayList;

public class SClass {
    private String  id;
    private String className;
    private ArrayList<Student> myList;
    private  boolean isSelected;
    public ArrayList<Student> getMyList() {
        return myList;
    }

    public void setMyList(ArrayList<Student> myList) {
        this.myList = new ArrayList<>(myList);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean IsSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public SClass(String id, String className, ArrayList<Student> myList) {
        this.id = id;
        this.className = className;
        this.myList=new ArrayList<>(myList);
        this.isSelected=false;
    }
}