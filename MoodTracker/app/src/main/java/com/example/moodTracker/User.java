package com.example.moodTracker;

public class User {
    String dob;
    String phone_no;

    User(){

    }

    public User(String dob, String phone_no) {
        this.dob = dob;
        this.phone_no = phone_no;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
