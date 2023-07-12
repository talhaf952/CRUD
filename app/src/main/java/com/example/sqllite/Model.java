package com.example.sqllite;

public class Model {

    private String name;
    private int rollnumber;
    private boolean isEnroll;
    public String  toString(){
        return "Student name is "+name+
                "rollnumber is "+rollnumber+
                "is enrolled "+isEnroll+
                "}";
    }

    public Model(String name, int rollnumber, boolean isEnroll) {
        this.name = name;
        this.rollnumber = rollnumber;
        this.isEnroll = isEnroll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollnumber() {
        return rollnumber;
    }

    public void setRollnumber(int rollnumber) {
        this.rollnumber = rollnumber;
    }

    public boolean isEnroll() {
        return isEnroll;
    }

    public void setEnroll(boolean enroll) {
        isEnroll = enroll;
    }
}
