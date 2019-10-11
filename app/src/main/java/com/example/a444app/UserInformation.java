package com.example.a444app;

public class UserInformation {
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public boolean isBook() {
        return book;
    }

    public void setBook(boolean book) {
        this.book = book;
    }

    public String uname;
    public String uemail;
    public String uid;
    public String upassword;
    public String uphone;
    public boolean book;


    public UserInformation() {
    }

    public UserInformation( boolean book,String uname, String uemail, String uid, String upassword, String uphone) {
        this.uname = uname;
        this.uemail = uemail;
        this.uid = uid;
        this.upassword = upassword;
        this.uphone = uphone;
        this.book=book;
    }
}
